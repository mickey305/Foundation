package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.ansi.code.AnsiStringBuilder;
import com.mickey305.foundation.v3.ansi.code.Escape;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Log {
  private static final SimpleDateFormat SDF_PATTERN1;
  
  static {
    SDF_PATTERN1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  }
  
  private Log() {
  }
  
  /**
   * 現在日時を取得する
   *
   * @return 現在時刻（フォーマット「yyyy-MM-dd HH:mm:ss.SSS」）
   */
  private static String createHeader() {
    Calendar cal = Calendar.getInstance();
    return SDF_PATTERN1.format(cal.getTime());
  }
  
  /**
   * ログ情報を出力する（標準出力・デバッグモード）
   *
   * @param element スタックトレース情報
   * @param msg     メッセージ
   */
  public synchronized static void d(@Nonnull StackTraceElement element, @Nonnull String msg) {
    final int lineNumberWidth = 7;
    AnsiStringBuilder sb = new AnsiStringBuilder()
        .append(Escape.BkgYellow)
        .append(Escape.Black)
        .append("[")
        .append(element.getClassName())
        .append("#").append(element.getMethodName())
        .append("(line:")
        .append(String.format("%0" + lineNumberWidth + "d", element.getLineNumber()))
        .append(")]")
        .append(Escape.Reset)
        .append(" ")
        .append(msg);
    i(sb.toString());
  }
  
  /**
   *
   * @param msg
   */
  public synchronized static void d(@Nonnull String msg) {
    final int traceTargetIndex = 2;
    StackTraceElement element = Thread.currentThread().getStackTrace()[traceTargetIndex];
    d(element, msg);
  }
  
  /**
   * ログ情報を出力する（標準出力）
   *
   * @param msg メッセージ
   */
  public synchronized static void i(@Nonnull String msg) {
    AnsiStringBuilder sb = new AnsiStringBuilder()
        .append(Escape.Blue)
        .append(createHeader())
        .append(" I/D ")
        .append(Escape.Reset)
        .append(msg);
    System.out.println(sb.toString());
  }
  
  /**
   * ログ情報を出力する（エラー出力）
   *
   * @param msg メッセージ
   */
  public synchronized static void e(@Nonnull String msg) {
    StringBuilder sb = new StringBuilder()
        .append(createHeader())
        .append("  E  ")
        .append(msg);
    System.err.println(sb.toString());
  }
  
  /**
   * ログ情報を出力する（標準出力）
   * 改行コードをなくした状態で、現在のカーソル行を上書きする
   *
   * @param line
   */
  public synchronized static void update(@Nonnull String line) {
    line = line.replace("\n", "");
    AnsiStringBuilder sb = new AnsiStringBuilder()
        .append("\r")
        .append(Escape.Green)
        .append(createHeader())
        .append("  U  ")
        .append(Escape.Reset)
        .append(line);
    System.out.print(sb.toString());
  }
  
  //
  // object wrapper method
  //
  
  public static void d(Object o)                      { Log.d(Objects.toString(o));      }
  public static void d(StackTraceElement e, Object o) { Log.d(e, Objects.toString(o));   }
  public static void i(Object o)                      { Log.i(Objects.toString(o));      }
  public static void e(Object o)                      { Log.e(Objects.toString(o));      }
  public static void update(Object o)                 { Log.update(Objects.toString(o)); }
  
  /**
   * 改行する
   */
  public synchronized static void ln() {
    System.out.println();
  }
}