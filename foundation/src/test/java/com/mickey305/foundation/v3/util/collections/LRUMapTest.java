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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    Map<String, String> cache = LRUMap.of(3);
    
    cache.put("test1", "value1");
    cache.put("test2", "value2");
    cache.put("test3", "value3");
    
    for(Map.Entry<String, String> entry : cache.entrySet()) {
      Log.i("elm[" + entry.getKey() + "]=" + entry.getValue());
    }
    
    cache.get("test1");
    cache.get("test3");
    cache.put("test4", "value4");
    
    for(Map.Entry<String, String> entry : cache.entrySet()) {
      Log.i("elm[" + entry.getKey() + "]=" + entry.getValue());
    }
  }
}