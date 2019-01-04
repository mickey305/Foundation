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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ByteUtilTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    final String testStr = "Left Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.left(testData, -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.left(testData, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.left(testData, 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.left(testData, 3);
    Log.i(Arrays.toString(resultData));
  
    resultData = ByteUtil.left(testData, testData.length -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.left(testData, testData.length);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.left(testData, testData.length +1);
    Log.i(Arrays.toString(resultData));
  }
  
  @Test
  public void testCase_02_01() throws Exception {
    final String testStr = "Right Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.right(testData, -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.right(testData, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.right(testData, 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.right(testData, 3);
    Log.i(Arrays.toString(resultData));
  
    resultData = ByteUtil.right(testData, testData.length -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.right(testData, testData.length);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.right(testData, testData.length +1);
    Log.i(Arrays.toString(resultData));
  }
  
  @Test
  public void testCase_03_01() throws Exception {
    final String testStr = "Mid Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.mid(testData, -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 3);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length +1);
    Log.i(Arrays.toString(resultData));
  }
  
  @Test
  public void testCase_04_01() throws Exception {
    final String testStr = "Mid1 Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.mid(testData, -1, -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 0, -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 3, -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length -1, -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length, -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length +1, -1);
    Log.i(Arrays.toString(resultData));
  }
  
  @Test
  public void testCase_04_02() throws Exception {
    final String testStr = "Mid2 Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.mid(testData, -1, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 0, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 3, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length -1, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length +1, 0);
    Log.i(Arrays.toString(resultData));
  }
  
  @Test
  public void testCase_04_03() throws Exception {
    final String testStr = "Mid3 Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.mid(testData, -1, 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 0, 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 3, 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length -1, 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length, 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length +1, 1);
    Log.i(Arrays.toString(resultData));
  }
  
  @Test
  public void testCase_04_04() throws Exception {
    final String testStr = "Mid4 Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.mid(testData, -1, 3);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 0, 3);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 3, 3);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length -1, 3);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length, 3);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length +1, 3);
    Log.i(Arrays.toString(resultData));
  }
  
  @Test
  public void testCase_04_05() throws Exception {
    final String testStr = "Mid5 Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.mid(testData, -1, testData.length -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 0, testData.length -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 3, testData.length -4);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length -1, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length, -1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length +1, -2);
    Log.i(Arrays.toString(resultData));
  }
  
  @Test
  public void testCase_04_06() throws Exception {
    final String testStr = "Mid6 Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.mid(testData, -1, testData.length);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 0, testData.length);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 3, testData.length -3);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length -1, 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length, 0);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length +1, -1);
    Log.i(Arrays.toString(resultData));
  }
  
  @Test
  public void testCase_04_07() throws Exception {
    final String testStr = "Mid7 Test DATA!!";
    Log.d(testStr);
    byte[] testData = testStr.getBytes();
    byte[] resultData;
    
    Log.i(Arrays.toString(testData));
    
    resultData = ByteUtil.mid(testData, -1, testData.length +1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 0, testData.length + 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, 3, testData.length -2);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length -1, 2);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length, 1);
    Log.i(Arrays.toString(resultData));
    
    resultData = ByteUtil.mid(testData, testData.length +1, 0);
    Log.i(Arrays.toString(resultData));
  }
}