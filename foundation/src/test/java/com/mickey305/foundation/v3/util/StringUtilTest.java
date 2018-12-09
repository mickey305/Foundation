package com.mickey305.foundation.v3.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

public class StringUtilTest {
  private String testPathData1, testPathData2;
  private String[] testPathElements;
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void separateWithPath() throws Exception {
    this.createData(new String[]{"D:", "path1", "path2", "path3"});
    Assert.assertEquals(testPathElements, StringUtil.separateWithPath(testPathData1));
    Assert.assertEquals(testPathElements.length, StringUtil.separateWithPath(testPathData1).length);
    Assert.assertEquals(testPathElements, StringUtil.separateWithPath(testPathData2));
    Assert.assertEquals(testPathElements.length, StringUtil.separateWithPath(testPathData2).length);
  }
  
  private void createData(String[] seedData) {
    testPathElements = seedData;
    testPathData1 = StringUtils.join(testPathElements, File.separator) + File.separator;
    testPathData2 = StringUtils.join(testPathElements, File.separator);
    Log.i("testPathElements -> " + Arrays.toString(testPathElements));
    Log.i("testPathData1 -> " + testPathData1);
    Log.i("testPathData2 -> " + testPathData2);
  }
}