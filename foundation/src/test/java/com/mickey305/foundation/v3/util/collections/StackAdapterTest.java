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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;

public class StackAdapterTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() {
    final IStack<String> stack = new StackAdapter<>(new ArrayDeque<>());
    stack.push("test1");
    stack.push("test2");
    stack.push("test3");
    stack.push("test4");
    stack.push("test5");
    Assert.assertEquals(5, stack.size());
    Assert.assertEquals("test5", stack.peek());
    Assert.assertEquals("test5", stack.pop());
    Assert.assertEquals("test4", stack.pop());
    Assert.assertEquals(3, stack.size());
  }
}