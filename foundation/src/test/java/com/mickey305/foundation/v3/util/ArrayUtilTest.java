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
    char[] seed = "test1".toCharArray();
    Character[] ary1 = new Character[seed.length];
    Character[] ary2 = new Character[ary1.length];
    int i = 0;
    for (Character c : seed) {
      ary1[i++] = c;
    }
    boolean rt;
    rt = ArrayUtil.copy(10, ary1, 0, ary2, 0);
    //rt = ArrayUtil.copy(ary1, 0, ary2, 1);
    Assert.assertTrue(rt);
    Assert.assertEquals(Arrays.toString(ary1), Arrays.toString(ary2));
  }
}