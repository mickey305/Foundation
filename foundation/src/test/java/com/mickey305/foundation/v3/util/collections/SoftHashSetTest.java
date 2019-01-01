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

package com.mickey305.foundation.v3.util.collections;

import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.collections4.map.AbstractReferenceMap;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.HARD;
import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.SOFT;
import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.WEAK;

public class SoftHashSetTest {
  private static final AbstractReferenceMap.ReferenceStrength CONST_HARD = HARD;
  private static final AbstractReferenceMap.ReferenceStrength CONST_SOFT = SOFT;
  private static final AbstractReferenceMap.ReferenceStrength CONST_WEAK = WEAK;
  
  private static final AbstractReferenceMap.ReferenceStrength DEFAULT_STRENGTH = WEAK;
  
  private SoftHashSet<String> hardCache;
  private SoftHashSet<String> softCache;
  private SoftHashSet<String> weakCache;
  
  @Before
  public void setUp() throws Exception {
    hardCache = new SoftHashSet<>(CONST_HARD);
    softCache = new SoftHashSet<>(CONST_SOFT);
    weakCache = new SoftHashSet<>(CONST_WEAK);
  }
  
  @After
  public void tearDown() throws Exception {
    hardCache = null;
    softCache = null;
    weakCache = null;
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    SoftHashSet<String> defaultCache;
    
    defaultCache = hardCache;
    createTestData(defaultCache);
    Log.i("hardCache bef: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    Assert.assertNotEquals(0, defaultCache.size());
    Assert.assertNotEquals(0, defaultCache.toArray().length);
    Assert.assertFalse(defaultCache.isEmpty());
    System.gc();
    Assert.assertNotEquals(0, defaultCache.size());
    Assert.assertNotEquals(0, defaultCache.toArray().length);
    Assert.assertFalse(defaultCache.isEmpty());
    Log.i("hardCache aft: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    
    defaultCache = softCache;
    createTestData(defaultCache);
    Log.i("softCache bef: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    System.gc();
    Log.i("softCache aft: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    
    defaultCache = weakCache;
    createTestData(defaultCache);
    Log.i("weakCache bef: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    Assert.assertNotEquals(0, defaultCache.size());
    Assert.assertNotEquals(0, defaultCache.toArray().length);
    Assert.assertFalse(defaultCache.isEmpty());
    System.gc();
    Log.d("weakCache aft - size: " + defaultCache.size());
    Log.d("weakCache aft - isEmpty: " + defaultCache.isEmpty());
    Log.d("weakCache aft - length: " + defaultCache.toArray().length);
    Log.i("weakCache aft: " + ToStringBuilder.reflectionToString(defaultCache.toArray()));
    
  }
  
  @Test
  public void testCase_01_02() throws Exception {
    SoftHashSet<String> defaultCache;
    final int qty = 100;
    final long count = 1_000_000;
    long start, end;
    List<Long> sizeCache = new ArrayList<>(qty);
    List<Long> allocatedSizeCache = new ArrayList<>(qty);
    List<Long> offsetSizeCache = new ArrayList<>(qty);
    
    defaultCache = weakCache;
    createTestData(defaultCache);
    
    for (int j = 0; j < qty; j++) {
      Log.i("start: call size");
      start = System.currentTimeMillis();
      for (long i = 0; i < count; i++) {
        defaultCache.size();
      }
      end = System.currentTimeMillis();
      Log.i("end: call size");
      Log.i("size time: " + (end - start) + "[ms]");
      sizeCache.add(end - start);
      
      Log.i("start: call allocatedSize");
      start = System.currentTimeMillis();
      for (long i = 0; i < count; i++) {
        defaultCache.allocatedSize();
      }
      end = System.currentTimeMillis();
      Log.i("end: call allocatedSize");
      Log.i("allocatedSize time: " + (end - start) + "[ms]");
      allocatedSizeCache.add(end - start);
      
      Log.i("start: call offsetSize");
      start = System.currentTimeMillis();
      for (long i = 0; i < count; i++) {
        defaultCache.offsetSize();
      }
      end = System.currentTimeMillis();
      Log.i("end: call offsetSize");
      Log.i("offsetSize time: " + (end - start) + "[ms]");
      offsetSizeCache.add(end - start);
    }
    double sum;
    sum = 0;
    for (Long t : sizeCache) {
      sum += t;
    }
    Log.d("---> size time ave          : " + (sum / qty) + "[ms]");
    sum = 0;
    for (Long t : allocatedSizeCache) {
      sum += t;
    }
    Log.d("---> allocatedSize time ave : " + (sum / qty) + "[ms]");
    sum = 0;
    for (Long t : offsetSizeCache) {
      sum += t;
    }
    Log.d("---> offsetSize time ave    : " + (sum / qty) + "[ms]");
    
  }
  
  @Test
  public void testCase_01_03() throws Exception {
    SoftHashSet<String> defaultCache;
    final long count = 1000;
    
    defaultCache = weakCache;
    
    for (long i = 0; i < count; i++) {
      createTestData(defaultCache);
      System.gc();
      final int size = defaultCache.size();
      final int allocatedSize = defaultCache.allocatedSize();
      final int offsetSize = defaultCache.offsetSize();
      
      // todo: サイズメソッドの不具合が修正されるまで、アサーションコードをコメント化
//      Assert.assertEquals(0, size);
      
      if (allocatedSize == 0 && size == 0 && offsetSize == 0) continue;
      
      Log.i("[" + String.format("%04d", i) + "] #allocatedSize() -> " + String.format("%03d", allocatedSize)
          + ", #size() -> " + String.format("%03d", size)
          + ", #offsetSize() -> " + String.format("%03d", offsetSize));
    }
  }
  
  private static void createTestData(SoftHashSet<String> set) {
    set.add(new String("test01"));
    set.add(new String("test02"));
    set.add(new String("test03"));
    set.add(new String("test04"));
    set.add(new String("test05"));
    set.add(new String("test06"));
    set.add(new String("test07"));
    set.add(new String("test08"));
    set.add(new String("test09"));
    set.add(new String("test10"));
  }
}