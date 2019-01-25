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
}