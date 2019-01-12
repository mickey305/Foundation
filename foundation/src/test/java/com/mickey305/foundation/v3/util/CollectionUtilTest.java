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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtilTest {
  private static final String TAG = CollectionUtilTest.class.getSimpleName();
  private Set<String> sample1;
  
  @Before
  public void setUp() throws Exception {
    sample1 = new HashSet<>();
    sample1.add("sample1");
    sample1.add("sample2");
    sample1.add("sample3");
    sample1.add("sample4");
    sample1.add("sample5");
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_02_01() throws Exception {
    Set<String> p1Sample1 = CollectionUtil.protectedSet(sample1);
    try {
      p1Sample1.iterator();
      Assert.fail();
    } catch (Throwable th) {
      Assert.assertTrue(true);
    }
  }
  
  @Test
  public void testCase_02_02() throws Exception {
    Log.i("big-data test start");
    long start = System.currentTimeMillis();
    for (int i = 0; i < 100_000; i++)
      CollectionUtil.protectedSet(sample1);
    long end = System.currentTimeMillis();
    long time = end - start;
    Log.i("big-data test end");
    Log.i("time: " + time + "(ms)");
    
    if (time > 500)
      Log.e("[" + TAG + "#testCase_02_02] > overhead-time too long warning message."
          + " please tuning this testcase-target-algorithm.");
  }
  
  @Test
  public void testCase_03_01() throws Exception {
    List<String> nonnullList = CollectionUtil.nonnullElementList(new ArrayList<String>());
    try {
      nonnullList.add("test1");
      nonnullList.add("test3");
    } catch (Exception ignore) { /* exception ignore */ }
    try {
      nonnullList.add(null);
      Assert.fail();
    } catch (Exception ignore) { /* exception ignore */ }
    try {
      nonnullList.add("test3");
    } catch (Exception ignore) { /* exception ignore */ }
  }
}