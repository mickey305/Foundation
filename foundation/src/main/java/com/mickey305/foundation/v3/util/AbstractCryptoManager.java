package com.mickey305.foundation.v3.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public abstract class AbstractCryptoManager {
  /**
   * crypto algorithm
   */
  private final String algorithm;
  
  /**
   * share key data
   */
  private final String shareKey;
  
  /**
   * share key buffer
   */
  private final StringBuilder shareKeyBuffer;
  
  /**
   * salt key data
   */
  private String saltKey;
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // initializer
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  {
    // data insertion
    algorithm = "BLOWFISH";
    shareKeyBuffer = new StringBuilder();
    // create salt-key
    this.generateSaltKey();
    // create share-key
    String key = null;
    try {
      final File shareKeyFile = ResFile.get("shareKey.dat");
      try (BufferedReader br = new BufferedReader(new FileReader(shareKeyFile))) {
        key = br.readLine();
      } catch (Exception e) {
        Log.d("shareKey file reading: " + e.getMessage());
      }
    } catch (Exception e) {
      Log.d("shareKey file finding: " + e.getMessage());
    } finally {
      shareKey = (StringUtils.isEmpty(key)) ? this.createShareKey() : key;
    }
    // key data log out(env debug mode only)
    if (IS_DEBUG_MODE) {
      Log.d("saltKey=[" + saltKey + "]");
      Log.d("shareKey=[" + shareKey + "]");
    }
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // methods
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * Saltキーを生成する
   *
   * @return instance of {@link AbstractCryptoManager}
   */
  public AbstractCryptoManager generateSaltKey() {
    try {
      saltKey = this.createSaltKey();
    } catch (Exception e) {
      Log.e(e.getMessage());
      throw new CryptException(e);
    }
    return this;
  }
  
  /**
   * 入力された平文を暗号化する
   *
   * @param stmt 平文
   * @return 暗号文
   */
  @Nonnull
  public String encrypt(@Nullable String stmt) {
    return this.encrypt(algorithm, saltKey, shareKey, stmt);
  }
  
  /**
   * 入力された暗号文を復号する
   *
   * @param stmt 暗号文
   * @return 平文
   */
  @Nonnull
  public String decrypt(@Nullable String stmt) {
    return this.decrypt(algorithm, saltKey, shareKey, stmt);
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /// private methods - crypto logic implementation
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Nonnull
  private String encrypt(String algorithm, @Nonnull String salt, @Nonnull String key, @Nullable String stmt) {
    if (StringUtils.isEmpty(stmt)) {
      return "";
    }
    
    // rebuild key
    key = HashGenerator.hash(salt + key);
    if (IS_DEBUG_MODE) {
      Log.d("key=[" + key + "]");
    }
    
    // create and initialize cipher instance
    final Cipher cipher = this.createCipher(Cipher.ENCRYPT_MODE, key, algorithm);
    
    // invoke cipher encryption
    byte[] encrypted;
    try {
      if (IS_DEBUG_MODE) {
        Log.d("input=" + Arrays.toString(stmt.getBytes()));
      }
      encrypted = cipher.doFinal(stmt.getBytes());
      if (IS_DEBUG_MODE) {
        Log.d("output=" + Arrays.toString(encrypted));
      }
    } catch (IllegalBlockSizeException | BadPaddingException e) {
      Log.e("encrypt: " + e.getMessage());
      throw new CryptException(e);
    }
    
    return Base64.encodeBase64String(encrypted);
  }
  
  @Nonnull
  private String decrypt(String algorithm, @Nonnull String salt, @Nonnull String key, @Nullable String stmt) {
    if (StringUtils.isEmpty(stmt)) {
      return "";
    }
    
    // rebuild key
    key = HashGenerator.hash(salt + key);
    if (IS_DEBUG_MODE) {
      Log.d("key=[" + key + "]");
    }
    
    // create cipher instance
    final Cipher cipher = this.createCipher(Cipher.DECRYPT_MODE, key, algorithm);
    
    // invoke cipher decryption
    final byte[] encrypted = Base64.decodeBase64(stmt);
    byte[] decrypted;
    try {
      if (IS_DEBUG_MODE) {
        Log.d("input=" + Arrays.toString(encrypted));
      }
      decrypted = cipher.doFinal(encrypted);
      if (IS_DEBUG_MODE) {
        Log.d("output=" + Arrays.toString(decrypted));
      }
    } catch (IllegalBlockSizeException | BadPaddingException e) {
      Log.e("decrypt: " + e.getMessage());
      throw new CryptException(e);
    }
    
    return new String(decrypted);
  }
  
  @Nonnull
  private Cipher createCipher(int mode, String key, String algorithm) {
    // get cipher instance
    final Cipher cipher = this.createCipher(algorithm);
    
    // invoke cipher initialization
    final SecretKeySpec secretKey = this.createSecretKey(key, algorithm);
    try {
      cipher.init(mode, secretKey);
    } catch (InvalidKeyException e) {
      Log.e(e.getMessage());
      throw new CryptException(e);
    }
    return cipher;
  }
  
  @Nonnull
  private Cipher createCipher(String algorithm) {
    Cipher cipher;
    try {
      cipher = Cipher.getInstance(algorithm);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      Log.e(e.getMessage());
      throw new CryptException(e);
    }
    return cipher;
  }
  
  @Nonnull
  private SecretKeySpec createSecretKey(String key, String algorithm) {
    final byte[] keyBytes = key.getBytes();
    return new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm);
  }
  
  @Nonnull
  private String createShareKey() {
    shareKeyBuffer.setLength(0);
    shareKeyBuffer.append(AbstractCryptoManager.class.getName());
    shareKeyBuffer.append(System.nanoTime());
    shareKeyBuffer.append(RandomStringUtils.randomAscii(10));
    return HashGenerator.hash(shareKeyBuffer.toString());
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
  public String shareKey() {
    return shareKey;
  }
  
  @Nonnull
  public String saltKey() {
    return saltKey;
  }
  
  public AbstractCryptoManager saltKey(String saltKey) {
    if (this.saltKey.length() > saltKey.length()) {
      throw new CryptException("Data shorter than the current salt-key can not be set. input=[" + saltKey + "]");
    }
    this.saltKey = saltKey;
    return this;
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // inner class
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public class CryptException extends UnsupportedOperationException {
    public CryptException(Throwable cause) {
      super(cause);
    }
    
    public CryptException(String msg) {
      super(msg);
    }
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
  protected abstract String createSaltKey();
}
