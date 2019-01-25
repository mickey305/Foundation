package com.mickey305.foundation.v3.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ArrayUtilTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() {
    char[] ary1 = "test1".toCharArray();
    char[] ary2 = new char[ary1.length];
    boolean rt;
    rt = ArrayUtil.copy(10, ary1, 0, ary2, 0);
    //rt = ArrayUtil.copy(ary1, 0, ary2, 1);
    Assert.assertTrue(rt);
    Assert.assertEquals(Arrays.toString(ary1), Arrays.toString(ary2));
  }
}