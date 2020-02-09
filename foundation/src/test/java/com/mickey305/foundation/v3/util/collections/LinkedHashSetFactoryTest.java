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

package com.mickey305.foundation.v3.util.collections;

import com.mickey305.foundation.v3.util.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class LinkedHashSetFactoryTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() {
    final LinkedHashSet<String> cache1 = LinkedHashSetFactory.newCache(CacheType.FIFO, 10);
    final LinkedHashSet<String> cache2 = LinkedHashSetFactory.newCache(CacheType.LRU, 10);
    
    Assert.assertTrue(cache1 instanceof FIFOSet);
    Assert.assertTrue(cache2 instanceof LRUSet);
    
    createTestData(cache1, 0, 9);
    createTestData(cache2, 0, 9);
    cache1.forEach(key -> Log.i("elm1[" + key + "]"));
    cache2.forEach(key -> Log.i("elm2[" + key + "]"));
    Assert.assertEquals(9, cache1.size());
    Assert.assertEquals(9, cache2.size());
    
    createTestData(cache1, 9, 10);
    createTestData(cache2, 9, 10);
    cache1.forEach(key -> Log.i("elm1[" + key + "]"));
    cache2.forEach(key -> Log.i("elm2[" + key + "]"));
    Assert.assertEquals(10, cache1.size());
    Assert.assertEquals(10, cache2.size());
    
    createTestData(cache1, 10, 17);
    createTestData(cache2, 10, 17);
    cache1.forEach(key -> Log.i("elm1[" + key + "]"));
    cache2.forEach(key -> Log.i("elm2[" + key + "]"));
    Assert.assertEquals(10, cache1.size());
    Assert.assertEquals(10, cache2.size());
  }
  
  private static void createTestData(Set<String> cache, int from, int to) {
    IntStream.range(from, to).forEach(i -> cache.add("test-key" + i));
    Log.i("create map size: " + cache.size());
  }
}