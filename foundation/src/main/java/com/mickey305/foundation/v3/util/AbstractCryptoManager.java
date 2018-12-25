package com.mickey305.foundation.v3.util;

import org.apache.commons.codec.binary.Base64;
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
import java.io.FileReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public abstract class AbstractCryptoManager {
  /**
   * crypto info
   */
  private final Info info;
  
  /**
   * share key data
   */
  private final String shareKey;
  
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
  private String saltKey;
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // initializer
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  {
    // data insertion
    info = new Info();
    iv = info.algorithm.genIV();
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
    // key data log out(env debug cryptMode only)
    if (IS_DEBUG_MODE) {
      Log.d("iv=" + Arrays.toString(iv));
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
      throw new CryptoException(e);
    }
    return this;
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
    return this.encrypt(info, saltKey, shareKey, iv, stmt);
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
    return this.decrypt(info, saltKey, shareKey, iv, stmt);
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /// private methods - crypto logic implementation
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Nonnull
  private String encrypt(Info info, @Nonnull String salt, @Nonnull String key, byte[] iv, @Nullable String stmt) {
    if (StringUtils.isEmpty(stmt)) {
      return "";
    }
    
    // rebuild key
    final byte[] keyBytes = HashGenerator.hashByte(salt + key);
    if (IS_DEBUG_MODE) {
      Log.d("keyBytes=" + Arrays.toString(keyBytes));
    }
    
    // create and initialize cipher instance
    final Cipher cipher = this.createCipher(Cipher.ENCRYPT_MODE, keyBytes, iv, info);
    
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
      throw new CryptoException(e);
    }
    
    return Base64.encodeBase64String(encrypted);
  }
  
  @Nonnull
  private String decrypt(Info info, @Nonnull String salt, @Nonnull String key, byte[] iv, @Nullable String stmt) {
    if (StringUtils.isEmpty(stmt)) {
      return "";
    }
  
    // rebuild key
    final byte[] keyBytes = HashGenerator.hashByte(salt + key);
    if (IS_DEBUG_MODE) {
      Log.d("keyBytes=" + Arrays.toString(keyBytes));
    }
    
    // create cipher instance
    final Cipher cipher = this.createCipher(Cipher.DECRYPT_MODE, keyBytes, iv, info);
    
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
      throw new CryptoException(e);
    }
    
    return new String(decrypted);
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
    return cipher;
  }
  
  @Nonnull
  private SecretKeySpec createSecretKey(byte[] key, String algorithm) {
    final SecretKeySpec keySpec = new SecretKeySpec(key, 0, key.length, algorithm);
    Arrays.fill(key, (byte) 0x00); // key data removing
    return keySpec;
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
  
  @Nullable
  public byte[] iv() {
    return iv;
  }
  
  @Nonnull
  public Info getInformation() {
    return info;
  }
  
  public AbstractCryptoManager saltKey(String saltKey) {
    if (this.saltKey.length() > saltKey.length()) {
      throw new CryptoException("Data shorter than the current salt-key can not be set. input=[" + saltKey + "]");
    }
    this.saltKey = saltKey;
    return this;
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // inner class
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public final class CryptoException extends UnsupportedOperationException {
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
  protected abstract String createSaltKey();
  
}
