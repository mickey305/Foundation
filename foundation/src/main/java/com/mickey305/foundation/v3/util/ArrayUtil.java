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

import javax.annotation.Nullable;
import java.util.Arrays;

public class ArrayUtil {
  
  private ArrayUtil() {
  }
  
  /**
   * @param table
   * @param element
   * @param <E>
   * @return
   */
  @Nullable
  public static <E> E[][] fill(E[][] table, E element) {
    if (table == null) { return null; }
    for (final E[] rec : table) {
      fill(rec, element);
    }
    return table;
  }
  
  /**
   *
   * @param table
   * @param element
   * @return
   */
  @Nullable
  public static int[][] fill(int[][] table, int element) {
    if (table == null) { return null; }
    for (final int[] rec : table) {
      fill(rec, element);
    }
    return table;
  }
  
  /**
   *
   * @param table
   * @param element
   * @return
   */
  @Nullable
  public static long[][] fill(long[][] table, long element) {
    if (table == null) { return null; }
    for (final long[] rec : table) {
      fill(rec, element);
    }
    return table;
  }
  
  /**
   *
   * @param table
   * @param element
   * @return
   */
  @Nullable
  public static short[][] fill(short[][] table, short element) {
    if (table == null) { return null; }
    for (final short[] rec : table) {
      fill(rec, element);
    }
    return table;
  }
  
  /**
   *
   * @param table
   * @param element
   * @return
   */
  @Nullable
  public static byte[][] fill(byte[][] table, byte element) {
    if (table == null) { return null; }
    for (final byte[] rec : table) {
      fill(rec, element);
    }
    return table;
  }
  
  /**
   *
   * @param table
   * @param element
   * @return
   */
  @Nullable
  public static char[][] fill(char[][] table, char element) {
    if (table == null) { return null; }
    for (final char[] rec : table) {
      fill(rec, element);
    }
    return table;
  }
  
  /**
   *
   * @param table
   * @param element
   * @return
   */
  @Nullable
  public static float[][] fill(float[][] table, float element) {
    if (table == null) { return null; }
    for (final float[] rec : table) {
      fill(rec, element);
    }
    return table;
  }
  
  /**
   *
   * @param table
   * @param element
   * @return
   */
  @Nullable
  public static double[][] fill(double[][] table, double element) {
    if (table == null) { return null; }
    for (final double[] rec : table) {
      fill(rec, element);
    }
    return table;
  }
  
  /**
   *
   * @param table
   * @param element
   * @return
   */
  @Nullable
  public static boolean[][] fill(boolean[][] table, boolean element) {
    if (table == null) { return null; }
    for (final boolean[] rec : table) {
      fill(rec, element);
    }
    return table;
  }
  
  /**
   * @param array
   * @param element
   * @param <E>
   * @return
   */
  @Nullable
  public static <E> E[] fill(E[] array, E element) {
    if (array == null) { return null; }
    for (int i = 0; i < array.length; i++) {
      array[i] = element;
    }
    return array;
  }
  
  /**
   *
   * @param array
   * @param element
   * @return
   */
  @Nullable
  public static int[] fill(int[] array, int element) {
    if (array == null) { return null; }
    Arrays.fill(array, element);
    return array;
  }
  
  /**
   *
   * @param array
   * @param element
   * @return
   */
  @Nullable
  public static long[] fill(long[] array, long element) {
    if (array == null) { return null; }
    Arrays.fill(array, element);
    return array;
  }
  
  /**
   *
   * @param array
   * @param element
   * @return
   */
  @Nullable
  public static short[] fill(short[] array, short element) {
    if (array == null) { return null; }
    Arrays.fill(array, element);
    return array;
  }
  
  /**
   *
   * @param array
   * @param element
   * @return
   */
  @Nullable
  public static byte[] fill(byte[] array, byte element) {
    if (array == null) { return null; }
    Arrays.fill(array, element);
    return array;
  }
  
  /**
   *
   * @param array
   * @param element
   * @return
   */
  @Nullable
  public static char[] fill(char[] array, char element) {
    if (array == null) { return null; }
    Arrays.fill(array, element);
    return array;
  }
  
  /**
   *
   * @param array
   * @param element
   * @return
   */
  @Nullable
  public static float[] fill(float[] array, float element) {
    if (array == null) { return null; }
    Arrays.fill(array, element);
    return array;
  }
  
  /**
   *
   * @param array
   * @param element
   * @return
   */
  @Nullable
  public static double[] fill(double[] array, double element) {
    if (array == null) { return null; }
    Arrays.fill(array, element);
    return array;
  }
  
  /**
   *
   * @param array
   * @param element
   * @return
   */
  @Nullable
  public static boolean[] fill(boolean[] array, boolean element) {
    if (array == null) { return null; }
    Arrays.fill(array, element);
    return array;
  }
  
  /**
   *
   * @param array
   * @param index
   * @param <E>
   * @return
   */
  @Nullable
  public static <E> E get(E[] array, int index) {
    return (array != null && index >= 0 && index < array.length)
        ? array[index]
        : null;
  }
  
  /**
   *
   * @param target
   * @param idx1
   * @param idx2
   * @param <E>
   */
  public static <E> void swap(E[] target, int idx1, int idx2) {
    Assert.requireNonNull(get(target, idx1));
    Assert.requireNonNull(get(target, idx2));
    swapImpl(target, idx1, idx2);
  }
  
  /**
   *
   * @param target
   * @param idx1
   * @param idx2
   * @param <E>
   * @return
   */
  public static <E> boolean trySwap(E[] target, int idx1, int idx2) {
    final E elm1 = get(target, idx1);
    final E elm2 = get(target, idx2);
    if (elm1 == null || elm2 == null) {
      return false;
    }
    swapImpl(target, idx1, idx2);
    return true;
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param targetSize
   * @param <E>
   * @exception IndexOutOfBoundsException  if copying would cause access of data outside array bounds.
   * @exception NullPointerException if either {@code src} or {@code dest} is {@code null}.
   */
  public static <E> void typeSafeCopy(E[] src, int srcIndex, E[] dest, int destIndex, int targetSize) {
    System.arraycopy(src, srcIndex, dest, destIndex, targetSize);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param targetSize
   * @exception IndexOutOfBoundsException  if copying would cause access of data outside array bounds.
   * @exception NullPointerException if either {@code src} or {@code dest} is {@code null}.
   */
  public static void typeSafeCopy(int[] src, int srcIndex, int[] dest, int destIndex, int targetSize) {
    System.arraycopy(src, srcIndex, dest, destIndex, targetSize);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param targetSize
   * @exception IndexOutOfBoundsException  if copying would cause access of data outside array bounds.
   * @exception NullPointerException if either {@code src} or {@code dest} is {@code null}.
   */
  public static void typeSafeCopy(long[] src, int srcIndex, long[] dest, int destIndex, int targetSize) {
    System.arraycopy(src, srcIndex, dest, destIndex, targetSize);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param targetSize
   * @exception IndexOutOfBoundsException  if copying would cause access of data outside array bounds.
   * @exception NullPointerException if either {@code src} or {@code dest} is {@code null}.
   */
  public static void typeSafeCopy(short[] src, int srcIndex, short[] dest, int destIndex, int targetSize) {
    System.arraycopy(src, srcIndex, dest, destIndex, targetSize);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param targetSize
   * @exception IndexOutOfBoundsException  if copying would cause access of data outside array bounds.
   * @exception NullPointerException if either {@code src} or {@code dest} is {@code null}.
   */
  public static void typeSafeCopy(byte[] src, int srcIndex, byte[] dest, int destIndex, int targetSize) {
    System.arraycopy(src, srcIndex, dest, destIndex, targetSize);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param targetSize
   * @exception IndexOutOfBoundsException  if copying would cause access of data outside array bounds.
   * @exception NullPointerException if either {@code src} or {@code dest} is {@code null}.
   */
  public static void typeSafeCopy(char[] src, int srcIndex, char[] dest, int destIndex, int targetSize) {
    System.arraycopy(src, srcIndex, dest, destIndex, targetSize);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param targetSize
   * @exception IndexOutOfBoundsException  if copying would cause access of data outside array bounds.
   * @exception NullPointerException if either {@code src} or {@code dest} is {@code null}.
   */
  public static void typeSafeCopy(float[] src, int srcIndex, float[] dest, int destIndex, int targetSize) {
    System.arraycopy(src, srcIndex, dest, destIndex, targetSize);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param targetSize
   * @exception IndexOutOfBoundsException  if copying would cause access of data outside array bounds.
   * @exception NullPointerException if either {@code src} or {@code dest} is {@code null}.
   */
  public static void typeSafeCopy(double[] src, int srcIndex, double[] dest, int destIndex, int targetSize) {
    System.arraycopy(src, srcIndex, dest, destIndex, targetSize);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param targetSize
   * @exception IndexOutOfBoundsException  if copying would cause access of data outside array bounds.
   * @exception NullPointerException if either {@code src} or {@code dest} is {@code null}.
   */
  public static void typeSafeCopy(boolean[] src, int srcIndex, boolean[] dest, int destIndex, int targetSize) {
    System.arraycopy(src, srcIndex, dest, destIndex, targetSize);
  }
  
  /**
   * type safe and data check impl copy method.
   * wrapper of {@link System#arraycopy(Object, int, Object, int, int)}
   * @param copyLength 5th argument for {@link System#arraycopy}
   * @param src 1st argument for {@link System#arraycopy}
   * @param srcIndex 2nd argument for {@link System#arraycopy}
   * @param dest 3rd argument for {@link System#arraycopy}
   * @param destIndex 4th argument for {@link System#arraycopy}
   * @param <E> element type of target array({@code src} and {@code dest})
   * @return copy task result.
   */
  public static <E> boolean copy(int copyLength, E[] src, int srcIndex, E[] dest, int destIndex) {
    // failure pattern
    if (src == null || dest == null) { return false; }
    if (srcIndex  < 0 || srcIndex  >= src.length) { return false; } // src.length == 0 => always false
    if (destIndex < 0 || destIndex >= dest.length) { return false; } // dest.length == 0 => always false
    if (copyLength < 0) { return false; }
    final int targetSize = Math.min(src.length - srcIndex, copyLength);
    if (targetSize > dest.length - destIndex) { return false; }
    // non operation pattern
    if (copyLength == 0) { return true; }
    // copy
    typeSafeCopy(src, srcIndex, dest, destIndex, targetSize);
    return true;
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, int[] src, int srcIndex, int[] dest, int destIndex) {
    // failure pattern
    if (src == null || dest == null) { return false; }
    if (srcIndex  < 0 || srcIndex  >= src.length) { return false; } // src.length == 0 => always false
    if (destIndex < 0 || destIndex >= dest.length) { return false; } // dest.length == 0 => always false
    if (copyLength < 0) { return false; }
    final int targetSize = Math.min(src.length - srcIndex, copyLength);
    if (targetSize > dest.length - destIndex) { return false; }
    // non operation pattern
    if (copyLength == 0) { return true; }
    // copy
    typeSafeCopy(src, srcIndex, dest, destIndex, targetSize);
    return true;
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, long[] src, int srcIndex, long[] dest, int destIndex) {
    // failure pattern
    if (src == null || dest == null) { return false; }
    if (srcIndex  < 0 || srcIndex  >= src.length) { return false; } // src.length == 0 => always false
    if (destIndex < 0 || destIndex >= dest.length) { return false; } // dest.length == 0 => always false
    if (copyLength < 0) { return false; }
    final int targetSize = Math.min(src.length - srcIndex, copyLength);
    if (targetSize > dest.length - destIndex) { return false; }
    // non operation pattern
    if (copyLength == 0) { return true; }
    // copy
    typeSafeCopy(src, srcIndex, dest, destIndex, targetSize);
    return true;
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, short[] src, int srcIndex, short[] dest, int destIndex) {
    // failure pattern
    if (src == null || dest == null) { return false; }
    if (srcIndex  < 0 || srcIndex  >= src.length) { return false; } // src.length == 0 => always false
    if (destIndex < 0 || destIndex >= dest.length) { return false; } // dest.length == 0 => always false
    if (copyLength < 0) { return false; }
    final int targetSize = Math.min(src.length - srcIndex, copyLength);
    if (targetSize > dest.length - destIndex) { return false; }
    // non operation pattern
    if (copyLength == 0) { return true; }
    // copy
    typeSafeCopy(src, srcIndex, dest, destIndex, targetSize);
    return true;
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, byte[] src, int srcIndex, byte[] dest, int destIndex) {
    // failure pattern
    if (src == null || dest == null) { return false; }
    if (srcIndex  < 0 || srcIndex  >= src.length) { return false; } // src.length == 0 => always false
    if (destIndex < 0 || destIndex >= dest.length) { return false; } // dest.length == 0 => always false
    if (copyLength < 0) { return false; }
    final int targetSize = Math.min(src.length - srcIndex, copyLength);
    if (targetSize > dest.length - destIndex) { return false; }
    // non operation pattern
    if (copyLength == 0) { return true; }
    // copy
    typeSafeCopy(src, srcIndex, dest, destIndex, targetSize);
    return true;
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, char[] src, int srcIndex, char[] dest, int destIndex) {
    // failure pattern
    if (src == null || dest == null) { return false; }
    if (srcIndex  < 0 || srcIndex  >= src.length) { return false; } // src.length == 0 => always false
    if (destIndex < 0 || destIndex >= dest.length) { return false; } // dest.length == 0 => always false
    if (copyLength < 0) { return false; }
    final int targetSize = Math.min(src.length - srcIndex, copyLength);
    if (targetSize > dest.length - destIndex) { return false; }
    // non operation pattern
    if (copyLength == 0) { return true; }
    // copy
    typeSafeCopy(src, srcIndex, dest, destIndex, targetSize);
    return true;
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, float[] src, int srcIndex, float[] dest, int destIndex) {
    // failure pattern
    if (src == null || dest == null) { return false; }
    if (srcIndex  < 0 || srcIndex  >= src.length) { return false; } // src.length == 0 => always false
    if (destIndex < 0 || destIndex >= dest.length) { return false; } // dest.length == 0 => always false
    if (copyLength < 0) { return false; }
    final int targetSize = Math.min(src.length - srcIndex, copyLength);
    if (targetSize > dest.length - destIndex) { return false; }
    // non operation pattern
    if (copyLength == 0) { return true; }
    // copy
    typeSafeCopy(src, srcIndex, dest, destIndex, targetSize);
    return true;
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, double[] src, int srcIndex, double[] dest, int destIndex) {
    // failure pattern
    if (src == null || dest == null) { return false; }
    if (srcIndex  < 0 || srcIndex  >= src.length) { return false; } // src.length == 0 => always false
    if (destIndex < 0 || destIndex >= dest.length) { return false; } // dest.length == 0 => always false
    if (copyLength < 0) { return false; }
    final int targetSize = Math.min(src.length - srcIndex, copyLength);
    if (targetSize > dest.length - destIndex) { return false; }
    // non operation pattern
    if (copyLength == 0) { return true; }
    // copy
    typeSafeCopy(src, srcIndex, dest, destIndex, targetSize);
    return true;
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, boolean[] src, int srcIndex, boolean[] dest, int destIndex) {
    // failure pattern
    if (src == null || dest == null) { return false; }
    if (srcIndex  < 0 || srcIndex  >= src.length) { return false; } // src.length == 0 => always false
    if (destIndex < 0 || destIndex >= dest.length) { return false; } // dest.length == 0 => always false
    if (copyLength < 0) { return false; }
    final int targetSize = Math.min(src.length - srcIndex, copyLength);
    if (targetSize > dest.length - destIndex) { return false; }
    // non operation pattern
    if (copyLength == 0) { return true; }
    // copy
    typeSafeCopy(src, srcIndex, dest, destIndex, targetSize);
    return true;
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param destIndex
   * @param <E>
   * @return
   */
  public static <E> boolean copy(int copyLength, E[] src, E[] dest, int destIndex) {
    return copy(copyLength, src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, int[] src, int[] dest, int destIndex) {
    return copy(copyLength, src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, long[] src, long[] dest, int destIndex) {
    return copy(copyLength, src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, short[] src, short[] dest, int destIndex) {
    return copy(copyLength, src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, byte[] src, byte[] dest, int destIndex) {
    return copy(copyLength, src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, char[] src, char[] dest, int destIndex) {
    return copy(copyLength, src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, float[] src, float[] dest, int destIndex) {
    return copy(copyLength, src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, double[] src, double[] dest, int destIndex) {
    return copy(copyLength, src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int copyLength, boolean[] src, boolean[] dest, int destIndex) {
    return copy(copyLength, src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @param <E>
   * @return
   */
  public static <E> boolean copy(int copyLength, E[] src, int srcIndex, E[] dest) {
    return copy(copyLength, src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, int[] src, int srcIndex, int[] dest) {
    return copy(copyLength, src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, long[] src, int srcIndex, long[] dest) {
    return copy(copyLength, src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, short[] src, int srcIndex, short[] dest) {
    return copy(copyLength, src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, byte[] src, int srcIndex, byte[] dest) {
    return copy(copyLength, src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, char[] src, int srcIndex, char[] dest) {
    return copy(copyLength, src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, float[] src, int srcIndex, float[] dest) {
    return copy(copyLength, src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, double[] src, int srcIndex, double[] dest) {
    return copy(copyLength, src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, boolean[] src, int srcIndex, boolean[] dest) {
    return copy(copyLength, src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @param <E>
   * @return
   */
  public static <E> boolean copy(int copyLength, E[] src, E[] dest) {
    return copy(copyLength, src, 0, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, int[] src, int[] dest) {
    return copy(copyLength, src, 0, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, long[] src, long[] dest) {
    return copy(copyLength, src, 0, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, short[] src, short[] dest) {
    return copy(copyLength, src, 0, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, byte[] src, byte[] dest) {
    return copy(copyLength, src, 0, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, char[] src, char[] dest) {
    return copy(copyLength, src, 0, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, float[] src, float[] dest) {
    return copy(copyLength, src, 0, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, double[] src, double[] dest) {
    return copy(copyLength, src, 0, dest, 0);
  }
  
  /**
   *
   * @param copyLength
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(int copyLength, boolean[] src, boolean[] dest) {
    return copy(copyLength, src, 0, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @param <E>
   * @return
   */
  public static <E> boolean copy(E[] src, int srcIndex, E[] dest, int destIndex) {
    if (src == null) { return false; }
    return copy(src.length - srcIndex, src, srcIndex, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int[] src, int srcIndex, int[] dest, int destIndex) {
    if (src == null) { return false; }
    return copy(src.length - srcIndex, src, srcIndex, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(long[] src, int srcIndex, long[] dest, int destIndex) {
    if (src == null) { return false; }
    return copy(src.length - srcIndex, src, srcIndex, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(short[] src, int srcIndex, short[] dest, int destIndex) {
    if (src == null) { return false; }
    return copy(src.length - srcIndex, src, srcIndex, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(byte[] src, int srcIndex, byte[] dest, int destIndex) {
    if (src == null) { return false; }
    return copy(src.length - srcIndex, src, srcIndex, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(char[] src, int srcIndex, char[] dest, int destIndex) {
    if (src == null) { return false; }
    return copy(src.length - srcIndex, src, srcIndex, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(float[] src, int srcIndex, float[] dest, int destIndex) {
    if (src == null) { return false; }
    return copy(src.length - srcIndex, src, srcIndex, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(double[] src, int srcIndex, double[] dest, int destIndex) {
    if (src == null) { return false; }
    return copy(src.length - srcIndex, src, srcIndex, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(boolean[] src, int srcIndex, boolean[] dest, int destIndex) {
    if (src == null) { return false; }
    return copy(src.length - srcIndex, src, srcIndex, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param destIndex
   * @param <E>
   * @return
   */
  public static <E> boolean copy(E[] src, E[] dest, int destIndex) {
    return copy(src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(int[] src, int[] dest, int destIndex) {
    return copy(src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(long[] src, long[] dest, int destIndex) {
    return copy(src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(short[] src, short[] dest, int destIndex) {
    return copy(src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(byte[] src, byte[] dest, int destIndex) {
    return copy(src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(char[] src, char[] dest, int destIndex) {
    return copy(src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(float[] src, float[] dest, int destIndex) {
    return copy(src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(double[] src, double[] dest, int destIndex) {
    return copy(src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param destIndex
   * @return
   */
  public static boolean copy(boolean[] src, boolean[] dest, int destIndex) {
    return copy(src, 0, dest, destIndex);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @param <E>
   * @return
   */
  public static <E> boolean copy(E[] src, int srcIndex, E[] dest) {
    return copy(src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(int[] src, int srcIndex, int[] dest) {
    return copy(src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(long[] src, int srcIndex, long[] dest) {
    return copy(src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(short[] src, int srcIndex, short[] dest) {
    return copy(src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(byte[] src, int srcIndex, byte[] dest) {
    return copy(src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(char[] src, int srcIndex, char[] dest) {
    return copy(src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(float[] src, int srcIndex, float[] dest) {
    return copy(src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(double[] src, int srcIndex, double[] dest) {
    return copy(src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param srcIndex
   * @param dest
   * @return
   */
  public static boolean copy(boolean[] src, int srcIndex, boolean[] dest) {
    return copy(src, srcIndex, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @param <E>
   * @return
   */
  public static <E> boolean copy(E[] src, E[] dest) {
    return copy(src, 0, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(int[] src, int[] dest) {
    return copy(src, 0, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(long[] src, long[] dest) {
    return copy(src, 0, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(short[] src, short[] dest) {
    return copy(src, 0, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(byte[] src, byte[] dest) {
    return copy(src, 0, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(char[] src, char[] dest) {
    return copy(src, 0, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(float[] src, float[] dest) {
    return copy(src, 0, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(double[] src, double[] dest) {
    return copy(src, 0, dest, 0);
  }
  
  /**
   *
   * @param src
   * @param dest
   * @return
   */
  public static boolean copy(boolean[] src, boolean[] dest) {
    return copy(src, 0, dest, 0);
  }
  
  private static <E> void swapImpl(E[] ary, int idx1, int idx2) {
    final E wk = ary[idx1];
    ary[idx1] = ary[idx2];
    ary[idx2] = wk;
  }
}
