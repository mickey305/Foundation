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
import org.apache.commons.lang3.StringUtils;
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
      crypto.clearSecureInfo();
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
      Log.i("base64 testStmt=[" + StringUtils.join(Base64.encodeBase64(testStmt.getBytes()), ' ') + "]");
      Log.i("base64 decryptedStmt=[" + StringUtils.join(Base64.encodeBase64(decryptedStmt.getBytes()), ' ') + "]");
      
      Assert.assertEquals(testStmt, decryptedStmt);
    }
    {
      final String testStmt = "テスト文字列　4 テスト文字列　4";
      final LocalCryptoManager crypto = LocalCryptoManager.getInstance();
      final String encryptedStmt = crypto.encrypt(testStmt);
      final String decryptedStmt = crypto.decrypt(encryptedStmt);
      crypto.dispose();
  
      Log.i("testStmt=[" + testStmt + "]");
      Log.i("encryptedStmt=[" + encryptedStmt + "]");
      Log.i("decryptedStmt=[" + decryptedStmt + "]");
      Log.i("base64 testStmt=[" + StringUtils.join(Base64.encodeBase64(testStmt.getBytes()), ' ') + "]");
      Log.i("base64 decryptedStmt=[" + StringUtils.join(Base64.encodeBase64(decryptedStmt.getBytes()), ' ') + "]");
      
      Assert.assertEquals(testStmt, decryptedStmt);
    }
  }
}
