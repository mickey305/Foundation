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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NonNullElementListTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    List<String> list01 = new ArrayList<>();
    List<String> list02 = new ArrayList<>();
    List<String> list11 = new NonNullElementList<>(new ArrayList<String>());
    List<String> list12 = new NonNullElementList<>(new LinkedList<String>());
    
    inputData(list01);
    inputData(list02);
    try {
      inputData(list11);
    } catch (Exception e) {
      Log.d(e);
    }
    try {
      inputData(list12);
    } catch (Exception e) {
      Log.d(e);
    }
  }
  
  private void inputData(List<String> targetList) {
    targetList.add("test1");
    targetList.add("test2");
    targetList.add("test3");
    targetList.add("test4");
    targetList.add("test5");
    targetList.add(null);
    targetList.add("test6");
  }
}