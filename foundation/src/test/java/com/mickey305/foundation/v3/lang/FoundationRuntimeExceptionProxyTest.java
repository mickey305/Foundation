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

package com.mickey305.foundation.v3.lang;

import com.mickey305.foundation.v3.util.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;
import static com.mickey305.foundation.v3.lang.FoundationThrowingMessageConst.HeaderString;
import static com.mickey305.foundation.v3.lang.FoundationThrowingMessageConst.LocalizedHeaderString;

public class FoundationRuntimeExceptionProxyTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() {
    RuntimeException ex11 = new TestRuntimeException1("test message");
    RuntimeException ex21 = new TestRuntimeException2("test message", "テストメッセージ");
  
    try {
      throw ex11;
    } catch(Throwable th) {
      Assert.assertEquals("test message", th.getMessage());
      Assert.assertEquals("test message", th.getLocalizedMessage());
    
      if (IS_DEBUG_MODE) {
        Log.d(th.getMessage());
        Log.d(th.getLocalizedMessage());
        Log.d(th);
      }
    }
  
    try {
      throw ex21;
    } catch(Throwable th) {
      Assert.assertEquals("test message", th.getMessage());
      Assert.assertEquals("テストメッセージ", th.getLocalizedMessage());
  
      if (IS_DEBUG_MODE) {
        Log.d(th.getMessage());
        Log.d(th.getLocalizedMessage());
        Log.d(th);
      }
    }
    
    try {
      throw new FoundationRuntimeExceptionProxy(ex11);
    } catch(Throwable th) {
      Assert.assertEquals(HeaderString + "test message", th.getMessage());
      Assert.assertEquals(LocalizedHeaderString + "test message", th.getLocalizedMessage());
  
      if (IS_DEBUG_MODE) {
        Log.d(th.getMessage());
        Log.d(th.getLocalizedMessage());
        Log.d(th);
      }
    }
    
    try {
      throw new FoundationRuntimeExceptionProxy(ex21);
    } catch(Throwable th) {
      Assert.assertEquals(HeaderString + "test message", th.getMessage());
      Assert.assertEquals(LocalizedHeaderString + "テストメッセージ", th.getLocalizedMessage());
  
      if (IS_DEBUG_MODE) {
        Log.d(th.getMessage());
        Log.d(th.getLocalizedMessage());
        Log.d(th);
      }
    }
  }
  
  @Test
  public void testCase_02_01() {
    RuntimeException ex11 = new TestRuntimeException1("test message");
    
    try {
      throw new FoundationRuntimeExceptionProxy(ex11);
    } catch(FoundationRuntimeExceptionProxy th) {
      Assert.assertTrue(th.typeOfOriginalObjectEquals(TestRuntimeException1.class));
    }
    
    try {
      RuntimeException t = new RuntimeException("wrapped", ex11);
      throw new FoundationRuntimeExceptionProxy(t);
    } catch(FoundationRuntimeExceptionProxy th) {
      Assert.assertTrue(th.typeOfOriginalObjectEquals(RuntimeException.class));
      Assert.assertEquals(ex11, th.getCause());
    }
  }
  
  private static class TestRuntimeException1 extends RuntimeException {
    public TestRuntimeException1(String msg) {
      super(msg);
    }
    public TestRuntimeException1(Throwable th) {
      super(th);
    }
    public TestRuntimeException1(String msg, Throwable th) {
      super(msg, th);
    }
  }
  
  private static class TestRuntimeException2 extends RuntimeException {
    private final String localizedMsg;
    
    public TestRuntimeException2(String msg, String localizedMsg) {
      super(msg);
      this.localizedMsg = localizedMsg;
    }
    public TestRuntimeException2(String localizedMsg, Throwable th) {
      super(th);
      this.localizedMsg = localizedMsg;
    }
    public TestRuntimeException2(String msg, String localizedMsg, Throwable th) {
      super(msg, th);
      this.localizedMsg = localizedMsg;
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocalizedMessage() {
      return this.localizedMsg;
    }
  }
}