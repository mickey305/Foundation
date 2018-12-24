package com.mickey305.foundation.v3.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
  public void testCase_01_01() throws Exception {
    this.createData(new String[]{"D:", "path1", "path2", "path3"});
    Assert.assertEquals(testPathElements, StringUtil.separateWithPath(testPathData1));
    Assert.assertEquals(testPathElements.length, StringUtil.separateWithPath(testPathData1).length);
    Assert.assertEquals(testPathElements, StringUtil.separateWithPath(testPathData2));
    Assert.assertEquals(testPathElements.length, StringUtil.separateWithPath(testPathData2).length);
  }
  
  @Test
  public void testCase_02_01() throws Exception {
    List<Pair<String, String>> testData = new ArrayList<>();
    testData.add(Pair.of("test", "test"));
    testData.add(Pair.of(" test ", "test"));
    testData.add(Pair.of("test  ", "test"));
    testData.add(Pair.of("  test", "test"));
    testData.add(Pair.of("　test　", "test"));
    testData.add(Pair.of("test　　", "test"));
    testData.add(Pair.of("　　test", "test"));
    testData.add(Pair.of("test", "test"));
    testData.add(Pair.of("\ttest\t", "test"));
    testData.add(Pair.of("test\t\t", "test"));
    testData.add(Pair.of("\t\ttest", "test"));
    testData.add(Pair.of("\ntest\n", "test"));
    testData.add(Pair.of("test\n\n", "test"));
    testData.add(Pair.of("\n\ntest", "test"));
    testData.add(Pair.of("\rtest\r", "test"));
    testData.add(Pair.of("test\r\r", "test"));
    testData.add(Pair.of("\r\rtest", "test"));
    testData.add(Pair.of("\r\ntest\r\n", "test"));
    testData.add(Pair.of("test\r\n\r\n", "test"));
    testData.add(Pair.of("\r\n\r\ntest", "test"));
    testData.add(Pair.of("te st", "te st"));
    testData.add(Pair.of(" te st ", "te st"));
    testData.add(Pair.of("te st  ", "te st"));
    testData.add(Pair.of("  te st", "te st"));
    testData.add(Pair.of("　te st　", "te st"));
    testData.add(Pair.of("te st　　", "te st"));
    testData.add(Pair.of("　　te st", "te st"));
    testData.add(Pair.of("\tte st\t", "te st"));
    testData.add(Pair.of("te st\t\t", "te st"));
    testData.add(Pair.of("\t\tte st", "te st"));
    testData.add(Pair.of("\nte st\n", "te st"));
    testData.add(Pair.of("te st\n\n", "te st"));
    testData.add(Pair.of("\n\nte st", "te st"));
    testData.add(Pair.of("\rte st\r", "te st"));
    testData.add(Pair.of("te st\r\r", "te st"));
    testData.add(Pair.of("\r\rte st", "te st"));
    testData.add(Pair.of("\r\nte st\r\n", "te st"));
    testData.add(Pair.of("te st\r\n\r\n", "te st"));
    testData.add(Pair.of("\r\n\r\nte st", "te st"));
    testData.add(Pair.of("te　st", "te　st"));
    testData.add(Pair.of(" te　st ", "te　st"));
    testData.add(Pair.of("te　st  ", "te　st"));
    testData.add(Pair.of("  te　st", "te　st"));
    testData.add(Pair.of("　te　st　", "te　st"));
    testData.add(Pair.of("te　st　　", "te　st"));
    testData.add(Pair.of("　　te　st", "te　st"));
    testData.add(Pair.of("\tte　st\t", "te　st"));
    testData.add(Pair.of("te　st\t\t", "te　st"));
    testData.add(Pair.of("\t\tte　st", "te　st"));
    testData.add(Pair.of("\nte　st\n", "te　st"));
    testData.add(Pair.of("te　st\n\n", "te　st"));
    testData.add(Pair.of("\n\nte　st", "te　st"));
    testData.add(Pair.of("\rte　st\r", "te　st"));
    testData.add(Pair.of("te　st\r\r", "te　st"));
    testData.add(Pair.of("\r\rte　st", "te　st"));
    testData.add(Pair.of("\r\nte　st\r\n", "te　st"));
    testData.add(Pair.of("te　st\r\n\r\n", "te　st"));
    testData.add(Pair.of("\r\n\r\nte　st", "te　st"));
    
    // complex data
    testData.add(Pair.of("\n 　\n \t  \r\n\r\nt\t\r\ne　　st\n\r  　", "t\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検証\nt\t\r\ne　　st\n\r  　", "検証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r￡￦\nt\t\r\ne　　st\n\r  　", "￡￦\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検証︥﹄︸︩︹\nt\t\r\ne　　st\n\r  　", "検証︥﹄︸︩︹\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検⺀鿕証\nt\t\r\ne　　st\n\r  　", "検⺀鿕証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検㐀힣ힹ証\nt\t\r\ne　　st\n\r  　", "検㐀힣ힹ証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検豈舘証\nt\t\r\ne　　st\n\r  　", "検豈舘証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検ﻄﺶﺶﺶﺶﺶ証\nt\t\r\ne　　st\n\r  　", "検ﻄﺶﺶﺶﺶﺶ証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検🈓🈤🈤🈪証\nt\t\r\ne　　st\n\r  　", "検🈓🈤🈤🈪証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検😀😐😷😷😩証\nt\t\r\ne　　st\n\r  　", "検😀😐😷😷😩証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検🜒🜓🜙🜛🜷🜳証\nt\t\r\ne　　st\n\r  　", "検🜒🜓🜙🜛🜷🜳証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検🚄証\nt\t\r\ne　　st\n\r  　", "検🚄証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検🚱証\nt\t\r\ne　　st\n\r  　", "検🚱証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検🚲証\nt\t\r\ne　　st\n\r  　", "検🚲証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検🚰証\nt\t\r\ne　　st\n\r  　", "検🚰証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検🚳証\nt\t\r\ne　　st\n\r  　", "検🚳証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検🚴証\nt\t\r\ne　　st\n\r  　", "検🚴証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r検🚵証\nt\t\r\ne　　st\n\r  　", "検🚵証\nt\t\r\ne　　st"));
    testData.add(Pair.of("\n 　\n \t  \r\n\r🚶🚷\nt\t\r\ne　　st\n\r  　", "🚶🚷\nt\t\r\ne　　st"));
    
    int i = 0;
    for (Pair<String, String> data : testData) {
      final String result = StringUtil.trim(data.getLeft());
      Assert.assertEquals("stopped data[" + i + "]", data.getRight(), result);
      i++;
    }
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