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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
  private static final Charset CHARSET = StandardCharsets.UTF_8;
  private static final String ALGORITHM = "SHA-256";
  private static final MessageDigest MESSAGE_DIGEST;
  
  static {
    try {
      MESSAGE_DIGEST = MessageDigest.getInstance(ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      Log.e(e.getMessage());
      throw new UnsupportedOperationException(e);
    }
  }
  
  @Nonnull
  public static byte[] hashByte(@Nullable byte[] src) {
    src = ArrayUtils.isEmpty(src) ? new byte[0] : src;
    return MESSAGE_DIGEST.digest(src);
  }
  
  @Nonnull
  public static byte[] hashByte(@Nullable String src, Charset charset) {
    src = StringUtils.isEmpty(src) ? "" : src;
    return hashByte(src.getBytes(charset));
  }
  
  @Nonnull
  public static byte[] hashByte(@Nullable String src) {
    return hashByte(src, CHARSET);
  }
  
  /**
   * ハッシュ値生成メソッド
   * <p>入力された文字列のハッシュ値を生成する
   * </p>
   * <p>暗号化処理にはこのメソッドは使用せずに、あくまでハッシュ値生成目的で使用する
   * </p>
   * <pre>{@code
   * final String hashedStmt = HashGenerator.hash(stmt, StandardCharsets.UTF_8);
   * }</pre>
   *
   * @param src     ハッシュ化対象の文字列
   * @param charset 文字コード
   * @return ハッシュ値(Base64 encoded)
   */
  @Nonnull
  public static String hash(@Nullable String src, Charset charset) {
    return Base64.encodeBase64String(hashByte(src, charset));
  }
  
  /**
   * ハッシュ値生成メソッド
   * <p>入力された文字列のハッシュ値を生成する
   * </p>
   * <p>暗号化処理にはこのメソッドは使用せずに、あくまでハッシュ値生成目的で使用する
   * </p>
   * <pre>{@code
   * final String hashedStmt = HashGenerator.hash(stmt);
   * }</pre>
   *
   * @param src ハッシュ化対象の文字列
   * @return ハッシュ値(Base64 encoded)
   */
  @Nonnull
  public static String hash(@Nullable String src) {
    return hash(src, CHARSET);
  }
}
