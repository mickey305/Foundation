package com.mickey305.foundation.v3.util;

public class ClassUtil {
  
  private ClassUtil() {
  }
  
  /**
   * 判定対象クラスがボクシングクラス（プリミティブ型のラッパークラス）かを判定する
   *
   * @param target 判定対象クラス
   * @return ボクシングクラスの場合、true
   */
  public static boolean isBoxing(Class<?> target) {
    return target.equals(Boolean.class)
        || target.equals(Character.class)
        || target.equals(Byte.class)
        || target.equals(Short.class)
        || target.equals(Integer.class)
        || target.equals(Long.class)
        || target.equals(Float.class)
        || target.equals(Double.class);
  }
}
