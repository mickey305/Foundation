/*
 * Copyright (c) 2019. K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mickey305.foundation.v3.lang;

import com.mickey305.foundation.v3.compat.stream.Consumer;
import com.mickey305.foundation.v3.compat.stream.Supplier;
import com.mickey305.foundation.v3.util.Assert;
import com.mickey305.foundation.v3.util.Log;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

/**
 * Console output-data snatcher
 * <p>sample code for java 8</p>
 * <pre>{@code
 * public interface Bindable<T> {
 *   void bindTo(Consumer<T> consumer);
 * }
 * public interface ConsoleSnatchable<R> {
 *   default Bindable<R> snatchConsoleErr(final Function<Supplier<String>,R>function){
 *     Bindable<R> bindable = consumer -> consumer.accept(null);
 *     try(ConsoleSnatcher consoleSnatcher = ConsoleSnatcher.of(StdErr)){
 *       // system console log snatch
 *       consoleSnatcher.snatch();
 *       final Supplier<String> errInfo = consoleSnatcher::getOutput;
 *       // logic impl
 *       final R result = function.apply(errInfo);
 *       bindable = consumer -> consumer.accept(result);
 *     }catch(Exception e){
 *       // Serious error block
 *       throw new RuntimeException("serious error occurred.", e);
 *     }
 *     return bindable;
 *   }
 * }
 * // java entry point
 * public final class Main implements ConsoleSnatchable<Integer> {
 *   public static void main(final String[] args) {
 *     final Main main = new Main();
 *     main.snatchConsoleErr(errInfo -> {
 *       // main logic
 *       ...
 *       final String errStmt = errInfo.get();
 *       if (errStmt.contains(ErrTriggerStmt)) {
 *         // error logic
 *         ...
 *         return ResultErr;
 *       }
 *       return ResultOk;
 *     }).bindTo(System::exit);
 *   }
 * }}</pre>
 */
public final class ConsoleSnatcher implements AutoCloseable {
  // nonnull
  @Nonnull private final TmpStreamHolder tmp;
  @Nonnull private final Target targetSystem;
  @Nonnull private final AtomicBoolean autoClear;
  private boolean stealFlag;
  private boolean closingOrEmpty;
  
  // nullable
  @Nullable private PrintStream nativeOut;
  
  public enum Target {
    /**
     * 標準出力ラッパー
     */
    StdOut(new Consumer<PrintStream>() {
      @Override
      public void accept(PrintStream out) {
        System.setOut(out);
      }
    }, new Supplier<PrintStream>() {
      @Override
      public PrintStream get() {
        return System.out;
      }
    }),
  
    /**
     * 標準エラーラッパー
     */
    StdErr(new Consumer<PrintStream>() {
      @Override
      public void accept(PrintStream err) {
        System.setErr(err);
      }
    }, new Supplier<PrintStream>() {
      @Override
      public PrintStream get() {
        return System.err;
      }
    });
    
    Target(@Nonnull final Consumer<PrintStream> setStreamFunc,
           @Nonnull final Supplier<PrintStream> getStreamFunc) {
      this.Instance = new ConsoleSnatcher(this);
      this.setStreamFunc = setStreamFunc;
      this.getStreamFunc = getStreamFunc;
    }
  
    @Nonnull private final ConsoleSnatcher Instance;
  
    /**
     * システムコンソールストリーム設定I/F
     * <p>引数で入力したストリームをシステムに設定する</p>
     */
    @Nonnull private final Consumer<PrintStream> setStreamFunc;
  
    /**
     * システムコンソールストリーム取得I/F
     * <p>システムに現在設定されているストリームオブジェクトを取得する</p>
     */
    @Nonnull private final Supplier<PrintStream> getStreamFunc;
  }
  
  private ConsoleSnatcher(@Nonnull final Target targetSystem) {
    Assert.requireNonNull(targetSystem);
    tmp = new TmpStreamHolder();
    nativeOut = null;
    stealFlag = false;
    closingOrEmpty = true;
    autoClear = new AtomicBoolean(true);
    this.targetSystem = targetSystem;
  }
  
  /**
   * Singletonインスタンス取得メソッド
   * @param target キャプチャターゲット. {@link Target#StdOut} or {@link Target#StdErr}
   * @return {@link ConsoleSnatcher}インスタンス
   */
  public synchronized static ConsoleSnatcher of(Target target) {
    final ConsoleSnatcher instance = target.Instance;
    if (instance.closingOrEmpty) {
      instance.tmp.create(new ByteArrayOutputStream());
      instance.closingOrEmpty = false;
    }
    return instance;
  }
  
  /**
   * 一時ストリームの自動消去を設定するメソッド
   * <p>デフォルトは、自動消去ON</p>
   * @param status フラグ. 自動消去ON：{@code true}、自動消去OFF：{@code false}を設定する
   * @return {@link ConsoleSnatcher}インスタンス
   */
  public ConsoleSnatcher setAutoClearMode(boolean status) {
    autoClear.set(status);
    return this;
  }
  
  /**
   * 一時ストリームの自動消去モード判定メソッド
   * @return フラグ. 自動消去ON：{@code true}、自動消去OFF：{@code false}を返却する
   */
  public boolean isAutoClearMode() {
    return autoClear.get();
  }
  
  /**
   * 出力先をコンソールからこのクラスへ出力するように変更する.
   */
  public synchronized void snatch() {
    if (!stealFlag && !closingOrEmpty) {
      // 現在の出力先の保存 nativeOut(default stream) <- system out/err stream
      nativeOut = targetSystem.getStreamFunc.get();
      
      // 出力先変更 system out/err stream <- snatched stream(tmp stream)
      targetSystem.setStreamFunc.accept(tmp.getSnatchedPrintStream());
      
      stealFlag = true;
    }
  }
  
  /**
   * 出力のクリア.
   */
  public synchronized void clearOutput() {
    if (tmp.getSnatchedOut() != null) {
      tmp.getSnatchedOut().reset();
    }
  }
  
  /**
   * 出力情報を取得.
   *
   * @return 出力情報
   */
  public synchronized String getOutput() {
    if (tmp.getSnatchedOut() != null) {
      targetSystem.getStreamFunc.get().flush();
      return tmp.getSnatchedOut().toString();
    }
    return "";
  }
  
  public synchronized PrintStream getNativeOutputStream() {
    return nativeOut;
  }
  
  /**
   * 出力先を元に戻す.
   */
  public synchronized void release() {
    if (stealFlag) {
      if (autoClear.get()) {
        // 自動クリアがONの場合、一時ストリームの内容を消去する
        clearOutput();
      }
      
      // 出力先を元に戻す system out/err stream <- nativeOut(default stream)
      targetSystem.setStreamFunc.accept(nativeOut);
      
      stealFlag = false;
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized void close() {
    // ストリームのリセット
    this.release();
    
    // 一時リソースの開放
    tmp.close();
    closingOrEmpty = true;
  }
  
  /**
   * 一時リソース格納用クラス
   */
  private class TmpStreamHolder implements AutoCloseable {
    @Nullable private ByteArrayOutputStream snatchedOut;
    @Nullable private PrintStream snatchedPrintStream;
    
    TmpStreamHolder() {
      // nop
    }
  
    TmpStreamHolder create(ByteArrayOutputStream tmpStream) {
      if (snatchedPrintStream != null) {
        // clear
        snatchedPrintStream.close();
        if (IS_DEBUG_MODE) {
          Log.d("tmp stream closed.");
        }
      }
      // create
      this.snatchedOut = tmpStream;
      this.snatchedPrintStream = new PrintStream(new BufferedOutputStream(tmpStream));
      if (IS_DEBUG_MODE) {
        Log.d("tmp stream created.");
      }
      return this;
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
      if (snatchedPrintStream != null) {
        snatchedPrintStream.close();
        if (IS_DEBUG_MODE) {
          Log.d("tmp stream closed.");
        }
      }
    }
  
    @Nullable
    ByteArrayOutputStream getSnatchedOut() {
      return snatchedOut;
    }
  
    @Nullable
    PrintStream getSnatchedPrintStream() {
      return snatchedPrintStream;
    }
  }
}
