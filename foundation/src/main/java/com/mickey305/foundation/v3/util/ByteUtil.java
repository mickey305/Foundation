/*
 * Copyright (c) 2017 - 2020 K.Misaki
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

import com.mickey305.foundation.v3.util.constant.EmptyArrayConst;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;

public class ByteUtil {
  private static final byte[] EMPTY = EmptyArrayConst.Primitive.Byte;
  
  private ByteUtil() {
    // nop
  }
  
  /**
   * バイト配列の左側から指定されたバイト数の配列を取得する
   * @param target バイト配列
   * @param size バイト数
   * @return バイト配列
   */
  @Nonnull
  public static byte[] left(@Nonnull final byte[] target, final int size) {
    Assert.requireNonNull(target);
    final int fixedPickupSize = (size < 0)
        ? 0
        : (size > target.length) ? target.length : size;
    if (ArrayUtils.isEmpty(target) || fixedPickupSize <= 0) {
      return EMPTY;
    }
    if (fixedPickupSize >= target.length) {
      return target;
    }
    final byte[] result = new byte[fixedPickupSize];
    System.arraycopy(target, 0, result, 0, fixedPickupSize);
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * バイト配列の右側から指定されたバイト数の配列を取得する
   * @param target バイト配列
   * @param size バイト数
   * @return バイト配列
   */
  @Nonnull
  public static byte[] right(@Nonnull final byte[] target, final int size) {
    Assert.requireNonNull(target);
    final int fixedPickupSize = (size < 0)
        ? 0
        : (size > target.length) ? target.length : size;
    if (ArrayUtils.isEmpty(target) || fixedPickupSize <= 0) {
      return EMPTY;
    }
    if (fixedPickupSize >= target.length) {
      return target;
    }
    final byte[] result = new byte[fixedPickupSize];
    System.arraycopy(target, target.length - fixedPickupSize, result, 0, fixedPickupSize);
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * 指定されたインデックス番号の位置からバイト数分のバイト配列を取得する
   * @param target バイト配列
   * @param startIndex インデックス番号
   * @param size バイト数
   * @return バイト配列
   */
  @Nonnull
  public static byte[] mid(@Nonnull final byte[] target, final int startIndex, final int size) {
    Assert.requireNonNull(target);
    if (startIndex > target.length -1) {
      return EMPTY;
    }
    final int fixedStartIndex = (startIndex < 0)
        ? 0
        : (startIndex > target.length -1) ? target.length -1 : startIndex;
    final byte[] tmpLeftCutBytes = new byte[target.length - fixedStartIndex];
    System.arraycopy(target, fixedStartIndex, tmpLeftCutBytes, 0, tmpLeftCutBytes.length);
    final byte[] result = left(tmpLeftCutBytes, size);
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * 指定されたインデックス番号の位置から始まるバイト配列を取得する
   * @param target バイト配列
   * @param startIndex インデックス番号
   * @return バイト配列
   */
  @Nonnull
  public static byte[] mid(@Nonnull final byte[] target, final int startIndex) {
    Assert.requireNonNull(target);
    final byte[] result = mid(target, startIndex, target.length);
    Assert.requireNonNull(result);
    return result;
  }
}
