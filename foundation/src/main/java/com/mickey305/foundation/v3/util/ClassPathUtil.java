package com.mickey305.foundation.v3.util;

import java.util.Objects;

public class ClassPathUtil {
  /**
   *
   */
  public static final String PATH_SEPARATOR;
  
  /**
   *
   */
  public static final String[] PATHS;
  
  static {
    PATH_SEPARATOR = System.getProperty("path.separator");
    Objects.requireNonNull(PATH_SEPARATOR);
    PATHS = System.getProperty("java.class.path").split(PATH_SEPARATOR);
    Objects.requireNonNull(PATHS);
  }
}
