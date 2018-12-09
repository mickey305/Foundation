package com.mickey305.foundation.v3.util;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.io.File;

public class StringUtil {
  
  private StringUtil() {
  }
  
  /**
   *
   * @param path
   * @return
   */
  @Nonnull
  public static String[] separateWithPath(@Nonnull String path) {
    return StringUtils.split(path, File.separatorChar);
  }
}
