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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
 *     try(ConsoleSnatcher consoleSnatcher = ConsoleSnatcher.getInstance(StdErr)){
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
  private final ByteArrayOutputStream snatchedOut;
  private final Target target;
  private PrintStream nativeOut;
  private boolean stealFlag;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    this.release();
  }
  
  public enum Target {
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
    
    Target(final Consumer<PrintStream> setStreamFunc,
           final Supplier<PrintStream> getStreamFunc) {
      this.Instance = new ConsoleSnatcher(this);
      this.setStreamFunc = setStreamFunc;
      this.getStreamFunc = getStreamFunc;
    }
    
    private final ConsoleSnatcher Instance;
    private final Consumer<PrintStream> setStreamFunc;
    private final Supplier<PrintStream> getStreamFunc;
  }
  
  private ConsoleSnatcher(final Target target) {
    snatchedOut = new ByteArrayOutputStream();
    nativeOut = null;
    stealFlag = false;
    this.target = target;
  }
  
  public static ConsoleSnatcher getInstance(final Target target) {
    return target.Instance;
  }
  
  /**
   * 出力先をコンソールからこのクラスへ出力するように変更する.
   */
  public void snatch() {
    // 2回以上の変更禁止
    if (!stealFlag) {
      // 現在の出力先の保存
      nativeOut = this.target.getStreamFunc.get();
      
      // 出力先変更
      this.target.setStreamFunc.accept(new PrintStream(new BufferedOutputStream(snatchedOut)));
      
      // 変更済みフラグ設定
      stealFlag = true;
    }
  }
  
  /**
   * 出力のクリア.
   */
  public void clearOutput() {
    snatchedOut.reset();
  }
  
  /**
   * 出力情報を取得.
   *
   * @return 出力情報
   */
  public String getOutput() {
    this.target.getStreamFunc.get().flush();
    return snatchedOut.toString();
  }
  
  public PrintStream getNativeOutputStream() {
    return nativeOut;
  }
  
  /**
   * 出力先を元に戻す.
   */
  public void release() {
    // 出力先が変更されている場合のみ実施
    if (stealFlag) {
      this.clearOutput();
      
      // 出力先を元に戻す
      this.target.setStreamFunc.accept(nativeOut);
      
      stealFlag = false;
    }
  }
}
