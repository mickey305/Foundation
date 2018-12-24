package com.mickey305.foundation.v3.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LocalCryptoManagerTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    {
      final String testStmt = "test statement";
      final LocalCryptoManager crypto = LocalCryptoManager.getInstance();
      final String encryptedStmt = crypto.encrypt(testStmt);
      final String decryptedStmt = crypto.decrypt(encryptedStmt);
      
      Log.i("testStmt=[" + testStmt + "]");
      Log.i("encryptedStmt=[" + encryptedStmt + "]");
      Log.i("decryptedStmt=[" + decryptedStmt + "]");
      
      Assert.assertEquals(testStmt, decryptedStmt);
    }
    {
      final String testStmt = "test statement2";
      final LocalCryptoManager crypto = LocalCryptoManager.getInstance();
      final String encryptedStmt = crypto.encrypt(testStmt);
      final String decryptedStmt = crypto.decrypt(encryptedStmt);
      
      Log.i("testStmt=[" + testStmt + "]");
      Log.i("encryptedStmt=[" + encryptedStmt + "]");
      Log.i("decryptedStmt=[" + decryptedStmt + "]");
      
      Assert.assertEquals(testStmt, decryptedStmt);
    }
    {
      final String testStmt = "テスト文字列　３";
      final LocalCryptoManager crypto = LocalCryptoManager.getInstance();
      final String encryptedStmt = crypto.encrypt(testStmt);
      final String decryptedStmt = crypto.decrypt(encryptedStmt);
      
      Log.i("testStmt=[" + testStmt + "]");
      Log.i("encryptedStmt=[" + encryptedStmt + "]");
      Log.i("decryptedStmt=[" + decryptedStmt + "]");
      
      Assert.assertEquals(testStmt, decryptedStmt);
    }
    {
      final String testStmt = "テスト文字列　4 テスト文字列　4";
      final LocalCryptoManager crypto = LocalCryptoManager.getInstance();
      final String encryptedStmt = crypto.encrypt(testStmt);
      final String decryptedStmt = crypto.decrypt(encryptedStmt);
      
      Log.i("testStmt=[" + testStmt + "]");
      Log.i("encryptedStmt=[" + encryptedStmt + "]");
      Log.i("decryptedStmt=[" + decryptedStmt + "]");
      
      Assert.assertEquals(testStmt, decryptedStmt);
    }
  }
}
