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
   * @param path
   * @return
   */
  @Nonnull
  public static String[] separateWithPath(@Nonnull String path) {
    return StringUtils.split(path, File.separatorChar);
  }
  
  /**
   * @param value
   * @return
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
