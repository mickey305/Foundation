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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ArrayUtilTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() {
    char[][] tbl = new char[3][3];
    ArrayUtil.fill(tbl, '2');
    Log.i(Arrays.deepToString(tbl));
  }
  
  @Test
  public void testCase_02_01() {
    char[] ary1 = "test1".toCharArray();
    char[] ary2 = new char[ary1.length];
    boolean rt;
    rt = ArrayUtil.copy(10, ary1, 0, ary2, 0);
    //rt = ArrayUtil.copy(ary1, 0, ary2, 1);
    Assert.assertTrue(rt);
    Assert.assertEquals(Arrays.toString(ary1), Arrays.toString(ary2));
  }
}