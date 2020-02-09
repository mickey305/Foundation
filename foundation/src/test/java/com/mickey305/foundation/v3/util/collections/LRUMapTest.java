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
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

public class LRUMapTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    ILRUMap<String, String> cache = LRUMap.of(3);
    
    cache.put("test1", "value1");
    cache.put("test2", "value2");
    cache.put("test3", "value3");
    cache.forEach((key, value) -> Log.i("elm[" + key + "]=" + value));
  
    cache.get("test3");
    cache.get("test1");
    cache.put("test4", "value4");
    cache.forEach((key, value) -> Log.i("elm[" + key + "]=" + value));
  
    int i = 0;
    for (Map.Entry<String, String> entry : cache.entrySet()) {
      if (i == 0) {
        entry.getKey();
      }
      i++;
    }
    //cache.replace("test1", "value1-new1");
    cache.forEach((key, value) -> Log.i("elm[" + key + "]=" + value));
    
    cache.put("test5", "value5");
    cache.forEach((key, value) -> Log.i("elm[" + key + "]=" + value));
  
    {
      int j = 0;
      Iterator<String> it = cache.keySet().iterator();
      while (it.hasNext() && j == 0) {
        String str = it.next();
        j++;
      }
      cache.forEach((key, value) -> Log.i("elm[" + key + "]=" + value));
    }
  
    {
      int j = 0;
      Iterator<String> it = cache.values().iterator();
      while (it.hasNext() && j == 0) {
        String str = it.next();
        j++;
      }
      cache.forEach((key, value) -> Log.i("elm[" + key + "]=" + value));
    }
  }
}