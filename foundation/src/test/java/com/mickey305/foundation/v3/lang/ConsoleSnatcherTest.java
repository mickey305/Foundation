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

package com.mickey305.foundation.v3.lang;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.mickey305.foundation.v3.lang.ConsoleSnatcher.Target.StdOut;

public class ConsoleSnatcherTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() {
    final StringBuilder builder = new StringBuilder();
    builder
        .append("test11").append(System.lineSeparator())
        .append("test12").append(System.lineSeparator())
        .append("test13").append(System.lineSeparator());
    
    System.out.println("test01");
    System.out.println("test02");
    try (ConsoleSnatcher snatcher = ConsoleSnatcher.getInstance(StdOut)) {
      snatcher.snatch();
      System.out.println("test11");
      System.out.println("test12");
      System.out.println("test13");
      Assert.assertEquals(builder.toString(), snatcher.getOutput());
    }
    System.out.println("test21");
    System.out.println("test22");
  }
  
  @Test
  public void testCase_01_02() {
    final StringBuilder builder1 = new StringBuilder();
    builder1
        .append("test11").append(System.lineSeparator())
        .append("test12").append(System.lineSeparator())
        .append("test13").append(System.lineSeparator());
    final StringBuilder builder2 = new StringBuilder();
    builder2
        .append("test21").append(System.lineSeparator())
        .append("test22").append(System.lineSeparator())
        .append("test23").append(System.lineSeparator());
    
    System.out.println("test01");
    System.out.println("test02");
    ConsoleSnatcher snatcher = ConsoleSnatcher.getInstance(StdOut);
    snatcher.snatch();
    System.out.println("test11");
    System.out.println("test12");
    System.out.println("test13");
    Assert.assertEquals(builder1.toString(), snatcher.getOutput());
    snatcher.release();
    snatcher = ConsoleSnatcher.getInstance(StdOut);
    snatcher.snatch();
    System.out.println("test21");
    System.out.println("test22");
    System.out.println("test23");
    Assert.assertEquals(builder2.toString(), snatcher.getOutput());
    snatcher.close();
    snatcher.clearOutput();
    snatcher.getOutput();
    snatcher.getNativeOutputStream();
    System.out.println("test31");
    System.out.println("test32");
  }
}