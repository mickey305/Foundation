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

public class QueueAdapterTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() {
    final IQueue<String> queue = new QueueAdapter<>(new ArrayDeque<>());
    queue.enqueue("test1");
    queue.enqueue("test2");
    queue.enqueue("test3");
    queue.enqueue("test4");
    queue.enqueue("test5");
    Assert.assertEquals(5, queue.size());
    Assert.assertEquals("test1", queue.peek());
    Assert.assertEquals("test1", queue.dequeue());
    Assert.assertEquals("test2", queue.dequeue());
    Assert.assertEquals(3, queue.size());
  }
}