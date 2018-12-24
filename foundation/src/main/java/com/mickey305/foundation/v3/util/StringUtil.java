package com.mickey305.foundation.v3.util;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.io.File;

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
    return StringUtils.split(path, File.separatorChar);
  }
  
  /**
   * 空白文字・全角スペースを入力文字列の両サイドから除去した文字列を返却する
   *
   * @param value 入力文字列
   * @return 出力文字列
   */
  public static String trim(String value) {
    if (value == null || value.length() == 0) {
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
    return ((st > 0) || (len < value.length()))
        ? value.substring(st, len)
        : value;
  }
}
