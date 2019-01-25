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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class HashGenerator {
  private static final Charset CHARSET = StandardCharsets.UTF_8;
  private static final Algorithm DEFAULT_ALGORITHM = Algorithm.SHA256;
  private static final Map<Algorithm, MessageDigest> DIGEST_CACHE;
  
  static {
    DIGEST_CACHE = new HashMap<>();
    try {
      {
        // create MD5 message digest
        MessageDigest md = MessageDigest.getInstance(Algorithm.MD5.name);
        DIGEST_CACHE.put(Algorithm.MD5, md);
      }
      {
        // create SHA-1 message digest
        MessageDigest md = MessageDigest.getInstance(Algorithm.SHA1.name);
        DIGEST_CACHE.put(Algorithm.SHA1, md);
      }
      {
        // create SHA-256 message digest
        MessageDigest md = MessageDigest.getInstance(Algorithm.SHA256.name);
        DIGEST_CACHE.put(Algorithm.SHA256, md);
      }
    } catch (NoSuchAlgorithmException e) {
      Log.e(e.getMessage());
      throw new UnsupportedOperationException(e);
    }
  }
  
  @Nonnull
  public static byte[] hashByte(@Nullable byte[] src) {
    final byte[] result = hashByte(src, DEFAULT_ALGORITHM);
    Assert.requireNonNull(result);
    return result;
  }
  
  @Nonnull
  public static byte[] hashByte(@Nullable byte[] src, @Nonnull Algorithm algorithm) {
    Assert.requireNonNull(algorithm);
    src = ArrayUtils.isEmpty(src) ? new byte[0] : src;
    final MessageDigest md = DIGEST_CACHE.get(algorithm);
    if (md == null) {
      throw new UnsupportedOperationException("target message digest not found");
    }
    final byte[] result = md.digest(src);
    Assert.requireNonNull(result);
    return result;
  }
  
  @Nonnull
  public static byte[] hashByte(@Nullable String src, Charset charset) {
    src = StringUtils.isEmpty(src) ? "" : src;
    final byte[] result = hashByte(src.getBytes(charset));
    Assert.requireNonNull(result);
    return result;
  }
  
  @Nonnull
  public static byte[] hashByte(@Nullable String src, Charset charset, @Nonnull Algorithm algorithm) {
    Assert.requireNonNull(algorithm);
    src = StringUtils.isEmpty(src) ? "" : src;
    final byte[] result = hashByte(src.getBytes(charset), algorithm);
    Assert.requireNonNull(result);
    return result;
  }
  
  @Nonnull
  public static byte[] hashByte(@Nullable String src) {
    final byte[] result = hashByte(src, CHARSET);
    Assert.requireNonNull(result);
    return result;
  }
  
  @Nonnull
  public static byte[] hashByte(@Nullable String src, @Nonnull Algorithm algorithm) {
    Assert.requireNonNull(algorithm);
    final byte[] result = hashByte(src, CHARSET, algorithm);
    Assert.requireNonNull(result);
    return result;
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
    final String result = Base64.encodeBase64String(hashByte(src, charset));
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * ハッシュ値生成メソッド
   * <p>入力された文字列のハッシュ値を生成する
   * </p>
   * <p>暗号化処理にはこのメソッドは使用せずに、あくまでハッシュ値生成目的で使用する
   * </p>
   * <pre>{@code
   * final String hashedStmt = HashGenerator.hash(stmt, StandardCharsets.UTF_8, Algorithm.SHA256);
   * }</pre>
   *
   * @param src     ハッシュ化対象の文字列
   * @param charset 文字コード
   * @param algorithm ハッシュ化アルゴリズム
   * @return ハッシュ値(Base64 encoded)
   */
  @Nonnull
  public static String hash(@Nullable String src, Charset charset, @Nonnull Algorithm algorithm) {
    Assert.requireNonNull(algorithm);
    final String result = Base64.encodeBase64String(hashByte(src, charset, algorithm));
    Assert.requireNonNull(result);
    return result;
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
    final String result = hash(src, CHARSET);
    Assert.requireNonNull(result);
    return result;
  }
  
  /**
   * ハッシュ値生成メソッド
   * <p>入力された文字列のハッシュ値を生成する
   * </p>
   * <p>暗号化処理にはこのメソッドは使用せずに、あくまでハッシュ値生成目的で使用する
   * </p>
   * <pre>{@code
   * final String hashedStmt = HashGenerator.hash(stmt, Algorithm.SHA256);
   * }</pre>
   *
   * @param src ハッシュ化対象の文字列
   * @param algorithm ハッシュ化アルゴリズム
   * @return ハッシュ値(Base64 encoded)
   */
  @Nonnull
  public static String hash(@Nullable String src, @Nonnull Algorithm algorithm) {
    Assert.requireNonNull(algorithm);
    final String result = hash(src, CHARSET, algorithm);
    Assert.requireNonNull(result);
    return result;
  }
  
  public enum Algorithm {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256");
    
    Algorithm(String name) {
      this.name = name;
    }
    
    private String name;
  }
}
