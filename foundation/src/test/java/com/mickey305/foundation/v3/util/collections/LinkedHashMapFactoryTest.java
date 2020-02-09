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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LinkedHashMapFactoryTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() {
    final LinkedHashMap<String, String> cache1 = LinkedHashMapFactory.newCache(CacheType.FIFO, 10);
    final LinkedHashMap<String, String> cache2 = LinkedHashMapFactory.newCache(CacheType.LRU, 10);
    
    Assert.assertTrue(cache1 instanceof FIFOMap);
    Assert.assertTrue(cache2 instanceof LRUMap);
    
    createTestData(cache1, 0, 9);
    createTestData(cache2, 0, 9);
    cache1.forEach((key, value) -> Log.i("elm1[" + key + "]=" + value));
    cache2.forEach((key, value) -> Log.i("elm2[" + key + "]=" + value));
    Assert.assertEquals(9, cache1.size());
    Assert.assertEquals(9, cache2.size());
    
    createTestData(cache1, 9, 10);
    createTestData(cache2, 9, 10);
    cache1.forEach((key, value) -> Log.i("elm1[" + key + "]=" + value));
    cache2.forEach((key, value) -> Log.i("elm2[" + key + "]=" + value));
    cache1.get("test-key5");
    cache2.get("test-key5");
    cache1.forEach((key, value) -> Log.i("elm1[" + key + "]=" + value));
    cache2.forEach((key, value) -> Log.i("elm2[" + key + "]=" + value));
    Assert.assertEquals(10, cache1.size());
    Assert.assertEquals(10, cache2.size());
    
    createTestData(cache1, 10, 17);
    createTestData(cache2, 10, 17);
    cache1.forEach((key, value) -> Log.i("elm1[" + key + "]=" + value));
    cache2.forEach((key, value) -> Log.i("elm2[" + key + "]=" + value));
    Assert.assertEquals(10, cache1.size());
    Assert.assertEquals(10, cache2.size());
  }
  
  private static void createTestData(Map<String, String> cache, int from, int to) {
    IntStream.range(from, to).forEach(i -> cache.put("test-key" + i, "test-value" + i));
    Log.i("create map size: " + cache.size());
  }
}