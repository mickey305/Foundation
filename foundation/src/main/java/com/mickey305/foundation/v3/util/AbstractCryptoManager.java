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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public abstract class AbstractCryptoManager implements AutoCloseable {
  /**
   * crypto info
   */
  private final Info info;
  
  /**
   * share key data
   */
  private final byte[] shareKey;
  
  /**
   * share key loaded data flag
   */
  private final boolean IsLoadedShareKey;
  
  /**
   * share key buffer
   */
  private final StringBuilder shareKeyBuffer;
  
  /**
   * IV data
   */
  private final byte[] iv;
  
  /**
   * salt key data
   */
  private final byte[] saltKey;
  
  /**
   * cipher instance cache
   */
  private final Map<Integer, Cipher> cipherMap;
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // initializer
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  {
    // data insertion
    info = new Info();
    iv = info.algorithm.genIV();
    shareKeyBuffer = new StringBuilder();
    cipherMap = new HashMap<>();
    // create salt-key
    try {
      saltKey = this.createSaltKey();
    } catch (Exception e) {
      Log.e(e.getMessage());
      throw new CryptoException(e);
    }
    // create share-key
    byte[] loadedShareKey = null;
    final File propertyFile = ResFile.get("common.properties");
    final Properties properties = new Properties();
    try (InputStream is = new FileInputStream(propertyFile)) {
      properties.load(is);
    } catch (Exception e) {
      Log.e(e.getMessage());
    }
    try {
      if (!properties.isEmpty()) {
        final File shareKeyFile = ResFile.get(properties.getProperty("cryptoPasswordFilePath"));
        try (BufferedReader br = new BufferedReader(new FileReader(shareKeyFile))) {
          loadedShareKey = br.readLine().getBytes();
        } catch (Exception e) {
          Log.d("shareKey file reading: " + e.getMessage());
        }
      }
    } catch (Exception e) {
      Log.d("shareKey file finding: " + e.getMessage());
    } finally {
      IsLoadedShareKey = !ArrayUtils.isEmpty(loadedShareKey);
      shareKey = (IsLoadedShareKey) ? loadedShareKey : this.createShareKey();
    }
    // key data log out(env debug cryptMode only)
    if (IS_DEBUG_MODE) {
      Log.d("iv=" + Arrays.toString(iv));
      Log.d("saltKey=" + Arrays.toString(saltKey));
      Log.d("shareKey=" + Arrays.toString(shareKey));
    }
    // create cipher instance and cache
    {
      // build keyBytes
      final byte[] keyBytes = HashGenerator.hashByte(ArrayUtils.addAll(shareKey, saltKey));
      // create cipher instance
      Cipher cipher = cipherMap.get(Cipher.ENCRYPT_MODE);
      if (cipher == null) {
        cipher = this.createCipher(Cipher.ENCRYPT_MODE, keyBytes, iv, info);
        cipherMap.put(Cipher.ENCRYPT_MODE, cipher);
      }
    }
    {
      // build keyBytes
      final byte[] keyBytes = HashGenerator.hashByte(ArrayUtils.addAll(shareKey, saltKey));
      // create cipher instance
      Cipher cipher = cipherMap.get(Cipher.DECRYPT_MODE);
      if (cipher == null) {
        cipher = this.createCipher(Cipher.DECRYPT_MODE, keyBytes, iv, info);
        cipherMap.put(Cipher.DECRYPT_MODE, cipher);
      }
    }
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // methods
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * セキュリティ情報を消去する
   *
   * @return instance of {@link AbstractCryptoManager}
   */
  public AbstractCryptoManager clearSecureInfo() {
    shareKeyBuffer.delete(0, shareKeyBuffer.length());
    Arrays.fill(shareKey, (byte) 0x00);
    Arrays.fill(saltKey, (byte) 0x00);
    Arrays.fill(iv, (byte) 0x00);
    return this;
  }
  
  /**
   * 後処理
   */
  public void dispose() {
    cipherMap.clear();
  }
  
  /**
   * 入力された平文を暗号化する
   *
   * @param stmt 平文
   * @return 暗号文(Base64 encoded)
   */
  @Nonnull
  public String encrypt(@Nullable String stmt) {
    if (IS_DEBUG_MODE) {
      Log.d(ToStringBuilder.reflectionToString(info));
    }
  
    if (StringUtils.isEmpty(stmt)) {
      return "";
    }
    final byte[] encrypted = this.encrypt(stmt.getBytes());
    final String encodedStr = Base64.encodeBase64String(encrypted);
    Assert.requireNonNull(encodedStr);
    return encodedStr;
  }
  
  /**
   * 入力された暗号文を復号する
   *
   * @param stmt 暗号文(Base64 encoded)
   * @return 平文
   */
  @Nonnull
  public String decrypt(@Nullable String stmt) {
    if (IS_DEBUG_MODE) {
      Log.d(ToStringBuilder.reflectionToString(info));
    }
    
    if (StringUtils.isEmpty(stmt)) {
      return "";
    }
    final byte[] decrypted = this.decrypt(Base64.decodeBase64(stmt));
    final String decryptedStr = new String(decrypted);
    Assert.requireNonNull(decryptedStr);
    return decryptedStr;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    this.clearSecureInfo();
    this.dispose();
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /// private methods - crypto logic implementation
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Nonnull
  private byte[] encrypt(@Nonnull byte[] plain) {
    Assert.requireNonNull(plain);
    final byte[] encrypts = this.crypt(Cipher.ENCRYPT_MODE, plain);
    Assert.requireNonNull(encrypts);
    return encrypts;
  }
  
  @Nonnull
  private byte[] decrypt(@Nonnull byte[] encrypted) {
    Assert.requireNonNull(encrypted);
    final byte[] decrypts = this.crypt(Cipher.DECRYPT_MODE, encrypted);
    Assert.requireNonNull(decrypts);
    return decrypts;
  }
  
  @Nonnull
  private byte[] crypt(int mode, @Nonnull byte[] target) {
    Assert.requireNonNull(target);
    // get cipher instance
    final Cipher cipher = cipherMap.get(mode);
    if (cipher == null) {
      throw new CryptoException("cipher object is empty");
    }
    
    // invoke cipher
    byte[] result;
    try {
      if (IS_DEBUG_MODE) {
        Log.d("input=" + Arrays.toString(target));
      }
      result = cipher.doFinal(target);
      if (IS_DEBUG_MODE) {
        Log.d("output=" + Arrays.toString(result));
      }
    } catch (IllegalBlockSizeException | BadPaddingException e) {
      Log.e(e.getMessage());
      throw new CryptoException(e);
    }
    Assert.requireNonNull(result);
    return result;
  }
  
  @Nonnull
  private Cipher createCipher(int mode, byte[] key, byte[] iv, Info info) {
    // get cipher instance
    final Cipher cipher = this.createCipher(info);
    
    // invoke cipher initialization
    final SecretKeySpec secretKey = this.createSecretKey(key, info.algorithm.name);
    final AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
    try {
      if (info.cryptMode == CryptMode.CBC || info.cryptMode == CryptMode.OFB) {
        cipher.init(mode, secretKey, paramSpec);
      } else {
        cipher.init(mode, secretKey);
      }
    } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
      Log.e(e.getMessage());
      throw new CryptoException(e);
    }
    Assert.requireNonNull(cipher);
    return cipher;
  }
  
  @Nonnull
  private Cipher createCipher(Info info) {
    Cipher cipher;
    try {
      cipher = Cipher.getInstance(info.transFormation());
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      Log.e(e.getMessage());
      throw new CryptoException(e);
    }
    Assert.requireNonNull(cipher);
    return cipher;
  }
  
  @Nonnull
  private SecretKeySpec createSecretKey(byte[] key, String algorithm) {
    final SecretKeySpec keySpec = new SecretKeySpec(key, 0, key.length, algorithm);
    Arrays.fill(key, (byte) 0x00); // key data removing
    Assert.requireNonNull(keySpec);
    return keySpec;
  }
  
  @Nonnull
  private byte[] createShareKey() {
    shareKeyBuffer.setLength(0);
    shareKeyBuffer.append(AbstractCryptoManager.class.getName());
    shareKeyBuffer.append(System.nanoTime());
    shareKeyBuffer.append(RandomStringUtils.randomAscii(10));
    final byte[] hash = HashGenerator.hashByte(shareKeyBuffer.toString());
    Assert.requireNonNull(hash);
    return hash;
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // constructor
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  protected AbstractCryptoManager() {
    /* nop */
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // accessor
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Nonnull
  public byte[] shareKey() {
    Assert.requireNonNull(shareKey);
    return shareKey;
  }
  
  @Nonnull
  public byte[] saltKey() {
    Assert.requireNonNull(saltKey);
    return saltKey;
  }
  
  @Nullable
  public byte[] iv() {
    return iv;
  }
  
  @Nonnull
  public Info getInformation() {
    Assert.requireNonNull(info);
    return info;
  }
  
  public boolean isLoadedShareKey() {
    return IsLoadedShareKey;
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // inner class
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public final class CryptoException extends UnsupportedOperationException {
    private static final long serialVersionUID = -834877014326829758L;
  
    public CryptoException(Throwable cause) {
      super(cause);
    }
    
    public CryptoException(String msg) {
      super(msg);
    }
  }
  
  public static final class Info {
    private Algorithm algorithm;
    private CryptMode cryptMode;
    private Padding padding;
    
    // initialize - default information setting
    {
      algorithm = Algorithm.AES;
      cryptMode = CryptMode.CBC;
      padding = Padding.PKCS5Padding;
    }
    
    private Info algorithm(Algorithm algorithm) {
      this.algorithm = algorithm;
      return this;
    }
    
    private Info mode(CryptMode cryptMode) {
      this.cryptMode = cryptMode;
      return this;
    }
  
    private Info padding(Padding padding) {
      this.padding = padding;
      return this;
    }
  
    public Algorithm getAlgorithm() {
      return algorithm;
    }
  
    public CryptMode getCryptMode() {
      return cryptMode;
    }
  
    public Padding getPadding() {
      return padding;
    }
  
    private String transFormation() {
      return StringUtils.join(Arrays.asList(algorithm.name, cryptMode.name(), padding.name()), "/");
    }
  }
  
  public enum Algorithm {
    Blowfish("BLOWFISH", 64),
    AES("AES", 128);
    
    Algorithm(String name, int blockSize) {
      this.name = name;
      this.blockSize = blockSize;
    }
    
    private final String name;
    private final Integer blockSize;
    
    private byte[] genIV() {
      byte[] data = new byte[this.blockSize / 8];
      new SecureRandom().nextBytes(data);
      return data;
    }
  }
  
  public enum CryptMode {
    ECB, CBC, OFB, NOFB, CFB
  }
  
  public enum Padding {
    NoPadding, ZeroBytePadding, PKCS5Padding, RFC1423
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // abstract methods
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * Saltキー生成ロジックを実装するメソッド
   *
   * @return Saltキー
   */
  @Nonnull
  protected abstract byte[] createSaltKey();
  
}
