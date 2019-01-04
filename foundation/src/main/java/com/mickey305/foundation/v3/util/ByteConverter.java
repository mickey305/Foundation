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

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Arrays;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public class ByteConverter {
  private static final char DEFAULT_SEPARATOR = ' '; // half-space
  private boolean separateMode;
  private char separator;
  
  public ByteConverter() {
    setSeparateMode(false);
    setSeparator('\0');
  }
  
  @Nonnull
  public ByteConverter setSeparateMode(boolean separateMode) {
    this.separateMode = separateMode;
    return this;
  }
  
  public boolean isSeparateMode() {
    return separateMode;
  }
  
  @Nonnull
  public ByteConverter setSeparator(char separator) {
    if (Character.isLetterOrDigit(separator)) {
      throw new IllegalArgumentException("separator character cannot use letter and digit. input: " + separator);
    }
    this.separator = separator;
    return this;
  }
  
  private char pickSeparator() {
    return (separator == '\0') ? DEFAULT_SEPARATOR : separator;
  }
  
  /**
   * 16進数文字配列→バイト配列変換
   * @param hex 16進数文字配列
   * @return バイト配列
   */
  @Nonnull
  public byte[] fromHexChars(@Nonnull final char[] hex) {
    try {
      return decodeHexChars(hex, (isSeparateMode()) ? pickSeparator() : null);
    } catch (DecoderException e) {
      Log.e(e.getMessage());
      throw new RuntimeException(e);
    }
  }
  
  /**
   * バイト配列変換→16進数文字配列
   * @param bytes バイト配列
   * @param lineBlockMax 最大ブロック長
   * @return 16進数文字配列
   */
  @Nonnull
  public char[] toHexChars(@Nonnull final byte[] bytes, final int lineBlockMax) {
    return encodeHexChars(bytes, lineBlockMax, (isSeparateMode()) ? pickSeparator() : null);
  }
  
  /**
   * バイト配列変換→16進数文字配列
   * @param bytes バイト配列
   * @return 16進数文字配列
   */
  @Nonnull
  public char[] toHexChars(@Nonnull final byte[] bytes) {
    return toHexChars(bytes, -1);
  }
  
  /**
   * 16進数文字列→バイト配列変換
   * @param hex 16進数文字列
   * @return バイト配列
   */
  @Nonnull
  public byte[] fromHexStr(@Nonnull final String hex) {
    try {
      return decodeHexChars(hex.toCharArray(), (isSeparateMode()) ? pickSeparator() : null);
    } catch (DecoderException e) {
      Log.e(e.getMessage());
      throw new RuntimeException(e);
    }
  }
  
  /**
   * バイト配列変換→16進数文字列
   * @param bytes バイト配列
   * @param lineBlockMax 最大ブロック長
   * @return 16進数文字列
   */
  @Nonnull
  public String toHexStr(@Nonnull final byte[] bytes, final int lineBlockMax) {
    return new String(encodeHexChars(bytes, lineBlockMax, (isSeparateMode()) ? pickSeparator() : null));
  }
  
  /**
   * バイト配列変換→16進数文字列
   * @param bytes バイト配列
   * @return 16進数文字列
   */
  @Nonnull
  public String toHexStr(@Nonnull final byte[] bytes) {
    return toHexStr(bytes, -1);
  }
  
  @Nonnull
  private static byte[] decodeHexChars(
      @Nonnull final char[] hex, @Nullable final Character separator) throws DecoderException {
    final int len = hex.length;
    
    if (len == 0) {
      return new byte[0];
    }
  
    int heap = 0;
    final char[] tmpHex = new char[len];
    for (int i = 0, j = 0; i < len && j < len; /* nop increment statement */) {
      char c = hex[j++];
      if ((separator != null && c == separator) || Character.isWhitespace(c) || Character.isSpaceChar(c)) {
        continue;
      }
      tmpHex[i++] = c;
      heap = i;
    }
    final char[] newHex = new char[heap];
    System.arraycopy(tmpHex, 0, newHex, 0, heap);
    if (IS_DEBUG_MODE) {
      Log.d("input " + Arrays.toString(tmpHex));
      Log.d("output" + Arrays.toString(newHex));
    }
    
    return Hex.decodeHex(newHex);
  }
  
  @Nonnull
  private static char[] encodeHexChars(
      @Nonnull final byte[] bytes, final int lineBlockMax, @Nullable final Character separator) {
    if (bytes.length == 0) {
      return new char[0];
    }
    
    final char[] tmpChars = Hex.encodeHex(bytes);
    
    if (separator == null) {
      return tmpChars;
    }
    
    final int len = tmpChars.length;
    if (len % 2 != 0) {
      throw new IllegalStateException("hex array data length error. length: " + len);
    }
    
    final int lineSize = lineBlockMax * 2;
    final int offset = (len / 2) + ((lineSize > 0) ? 0 : -1);
    final char[] chars = new char[len + offset];
    for (int i = 0, j = 0; i < chars.length && j < len; /* nop increment statement */) {
      // data transfer
      chars[i++] = tmpChars[j++];
      chars[i++] = tmpChars[j++];
      // separator and line-separator setting
      if (lineSize > 0) {
        chars[i++] = (j % lineSize == 0 || j == len) ? '\n' : separator;
      } else if (j != len) {
        chars[i++] = separator;
      }
      if (IS_DEBUG_MODE) {
        Log.d("created data" + Arrays.toString(chars));
      }
    }
    return chars;
  }
}
