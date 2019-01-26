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

package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.ansi.code.AnsiStringBuilder;
import com.mickey305.foundation.v3.ansi.code.Escape;
import com.mickey305.foundation.v3.lang.StackFinder;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Log {
  private static final SimpleDateFormat SDF_PATTERN1;
  private static final AnsiStringBuilder[] STD_BUILDERS;
  private static final int IDX_INFO = 0;
  private static final int IDX_DEBUG = 1;
  private static final int IDX_UPDATE = 2;
  private static final StringBuilder ERR_BUILDER;
  
  static {
    SDF_PATTERN1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    STD_BUILDERS = new AnsiStringBuilder[3];
    STD_BUILDERS[IDX_INFO] = new AnsiStringBuilder();
    STD_BUILDERS[IDX_DEBUG] = new AnsiStringBuilder();
    STD_BUILDERS[IDX_UPDATE] = new AnsiStringBuilder();
    ERR_BUILDER = new StringBuilder();
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
    Objects.requireNonNull(element);
    Objects.requireNonNull(msg);
    final int lineNumberWidth = 7;
    AnsiStringBuilder sb = STD_BUILDERS[IDX_DEBUG];
    sb.setLength(0);
    sb
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
   * @param msg
   */
  public synchronized static void d(@Nonnull String msg) {
    Objects.requireNonNull(msg);
    StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
    StackTraceElement element = StackFinder.tryGet(pos);
    assert element != null;
    d(element, msg);
  }
  
  /**
   * ログ情報を出力する（標準出力）
   *
   * @param msg メッセージ
   */
  public synchronized static void i(@Nonnull String msg) {
    Objects.requireNonNull(msg);
    AnsiStringBuilder sb = STD_BUILDERS[IDX_INFO];
    sb.setLength(0);
    sb
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
    Objects.requireNonNull(msg);
    StringBuilder sb = ERR_BUILDER;
    sb.setLength(0);
    sb
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
    Objects.requireNonNull(line);
    line = line.replace("\n", "");
    AnsiStringBuilder sb = STD_BUILDERS[IDX_UPDATE];
    sb.setLength(0);
    sb
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
  
  public static void d(Object o) {
    final String str = Objects.toString(o);
    StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
    StackTraceElement element = StackFinder.tryGet(pos);
    assert element != null;
    d(element, str);
  }
  
  public static void d(StackTraceElement e, Object o) {
    Log.d(e, Objects.toString(o));
  }
  
  public static void i(Object o) {
    Log.i(Objects.toString(o));
  }
  
  public static void e(Object o) {
    Log.e(Objects.toString(o));
  }
  
  public static void update(Object o) {
    Log.update(Objects.toString(o));
  }
  
  /**
   * 改行する
   */
  public synchronized static void ln() {
    System.out.println();
  }
}