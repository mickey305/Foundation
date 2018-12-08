package com.mickey305.foundation.v3.util;

public class ArrayUtil {
  
  public static <E> E[][] fill(E[][] table, E element) {
    for (final E[] rec : table) {
      for (int j = 0; j < rec.length; j++) {
        rec[j] = element;
      }
    }
    return table;
  }
  
  public static <E> E[] fill(E[] array, E element) {
    for (int i = 0; i < array.length; i++) {
      array[i] = element;
    }
    return array;
  }
}
