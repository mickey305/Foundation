package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.ansi.code.AnsiStringBuilder;
import com.mickey305.foundation.v3.ansi.code.Escape;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {
  
  private Log() {
  }
  
  /**
   * 現在日時を取得する
   *
   * @return 現在時刻（フォーマット「yyyy-MM-dd HH:mm:ss.SSS」）
   */
  private static String createHeader() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    return sdf.format(cal.getTime());
  }
  
  /**
   * ログ情報を出力する（標準出力・デバッグモード）
   *
   * @param element スタックトレース情報
   * @param msg     メッセージ
   */
  public synchronized static void d(StackTraceElement element, String msg) {
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
  
  public synchronized static void d(String msg) {
    final int traceTargetIndex = 2;
    StackTraceElement element = Thread.currentThread().getStackTrace()[traceTargetIndex];
    d(element, msg);
  }
  
  /**
   * ログ情報を出力する（標準出力）
   *
   * @param msg メッセージ
   */
  public synchronized static void i(String msg) {
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
  public synchronized static void e(String msg) {
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
  public synchronized static void update(String line) {
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
  
  /**
   * 改行する
   */
  public synchronized static void ln() {
    System.out.println();
  }
}