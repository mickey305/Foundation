/*
 * Copyright (c) 2018. K.Misaki
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

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Objects;

public class StringUtil {
  private static final char HALF_WIDTH_SPACE = ' ';
  private static final char FULL_WIDTH_SPACE = '　';
  
  private StringUtil() {
  }
  
  /**
   * 入力パス情報をファイルシステム区切り文字で分解し、結果を配列で返却する
   *
   * @param path パス文字列
   * @return パス情報
   */
  @Nonnull
  public static String[] separateWithPath(@Nonnull String path) {
    Assert.requireNonNull(path);
    final String[] result = StringUtils.split(path, File.separatorChar);
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * 空白文字・全角スペースを入力文字列の両サイドから除去した文字列を返却する
   *
   * @param value 入力文字列
   * @return 出力文字列
   */
  @Nonnull
  public static String trim(@Nonnull String value) {
    Assert.requireNonNull(value);
    if (value.length() == 0) {
      return value;
    }
    int st = 0;
    int len = value.length();
    char[] val = value.toCharArray();
    while ((st < len) && ((val[st] <= HALF_WIDTH_SPACE) || (val[st] == FULL_WIDTH_SPACE))) {
      st++;
    }
    while ((st < len) && ((val[len - 1] <= HALF_WIDTH_SPACE) || (val[len - 1] == FULL_WIDTH_SPACE))) {
      len--;
    }
    final String result = ((st > 0) || (len < value.length()))
        ? value.substring(st, len)
        : value;
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * 文字列の左側から指定された文字数の文字列を取得する
   * @param target 文字列
   * @param size 文字数
   * @return 文字列
   */
  @Nonnull
  public static String left(@Nonnull String target, int size) {
    Assert.requireNonNull(target);
    final String result = StringUtils.left(target, size);
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * 文字列の右側から指定された文字数の文字列を取得する
   * @param target 文字列
   * @param size 文字数
   * @return 文字列
   */
  @Nonnull
  public static String right(@Nonnull String target, int size) {
    Assert.requireNonNull(target);
    final String result = StringUtils.right(target, size);
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * 指定されたインデックス番号の位置から文字数分の文字列を取得する
   * @param target 文字列
   * @param startIndex インデックス番号
   * @param size 文字数
   * @return 文字列
   */
  @Nonnull
  public static String mid(@Nonnull String target, int startIndex, int size) {
    Assert.requireNonNull(target);
    final String result = StringUtils.mid(target, startIndex, size);
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * 指定されたインデックス番号の位置から始まる文字列を取得する
   * @param target 文字列
   * @param startIndex インデックス番号
   * @return 文字列
   */
  @Nonnull
  public static String mid(@Nonnull String target, int startIndex) {
    Assert.requireNonNull(target);
    final String result = mid(target, startIndex, target.length());
    Assert.requireNonNull(result);
    return result;
  }
}
