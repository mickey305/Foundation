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

import java.util.Set;

public class FIFOSetTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    Set<String> cache = FIFOSet.of(3);
    
    cache.add("test1");
    cache.add("test2");
    cache.add("test3");
    
    for(String key : cache) {
      Log.i("elm[" + key + "]");
    }
    
    cache.add("test4");
  
    for(String key : cache) {
      Log.i("elm[" + key + "]");
    }
  }
}