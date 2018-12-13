package com.mickey305.foundation.v3.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassPathUtilTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    final String pathSeparator = ClassPathUtil.PATH_SEPARATOR;
    final String[] paths = ClassPathUtil.PATHS;
    
    Log.i("pathSeparator: " + pathSeparator);
    Log.i("paths: ");
    for (String path : paths) {
      Log.i("-> " + path);
    }
  }
}