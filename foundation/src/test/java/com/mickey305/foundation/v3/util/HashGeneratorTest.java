package com.mickey305.foundation.v3.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashGeneratorTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    final String str = "test";
    final String result = HashGenerator.hash(str);
    Log.i("input -> " + str);
    Log.i("result -> " + result);
  }
}
