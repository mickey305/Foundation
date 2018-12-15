package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.util.bean.LightCsvDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LightCsvUtilTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    final LightCsvDto csv = new LightCsvDto();
    List<String> elements;
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("12,133.2", elements.get(3));
    LightCsvUtil.join(csv);
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("12,133.2", elements.get(3));
    Log.i(Arrays.toString(elements.toArray()));

    csv.setLine("\"12,133.2\n\",hoge, fuga ,piyo  ,");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12,133.2\n", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    LightCsvUtil.join(csv);
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12,133.2\n", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    Log.i(Arrays.toString(elements.toArray()));

    csv.setLine("");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(0, elements.size());
    LightCsvUtil.join(csv);
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(0, elements.size());
    Log.i(Arrays.toString(elements.toArray()));

    csv.setLine(null);
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(0, elements.size());
    LightCsvUtil.join(csv);
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(0, elements.size());
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,\"\"12,133.2\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,13\"3.2\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
  
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2\"\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    
    csv.setLine("hoge, fuga ,piyo  ,12,133.2\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    
    //////////////////////////////////////////////////////////////////////////
    // console output check //////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
  
    csv.setLine("hoge, fuga ,piyo  ,　\"12,133.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
  
    csv.setLine("hoge, fuga ,piyo  , \"12,133.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2\" ");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
  
    csv.setLine("hoge, fuga ,piyo  ,\"12,1\"\"33.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,\"\"\"12,133.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2\"\"\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
  
    csv.setLine("hoge, fuga ,piyo  ,\"12,1\"\"\"\"33.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine(" \"12,13 3.2\n\",hoge, fuga ,piyo  ,");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
  
    csv.setLine("\"12,\n133.2\" ,hoge, fuga ,piyo  ,");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Log.i(Arrays.toString(elements.toArray()));
  }
  
  @Test
  public void testCase_02_01() throws Exception {
    final LightCsvDto csv = new LightCsvDto();
    List<String> elements;
    
    elements = new ArrayList<>();
    elements.add("hoge");
    elements.add(" fuga");
    elements.add("pi　yo");
    elements.add("pi\"yo2");
    elements.add("12,13\"\"3.2");
    csv.setElements(elements);
    LightCsvUtil.join(csv);
    Assert.assertEquals("hoge,\" fuga\",pi　yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    LightCsvUtil.split(csv);
    LightCsvUtil.join(csv);
    Assert.assertEquals("hoge,\" fuga\",pi　yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    Log.i(csv.getLine());
  
    elements = new ArrayList<>();
    elements.add("hoge");
    elements.add("　fuga");
    elements.add("pi yo");
    elements.add("pi\"yo2");
    elements.add("12,13\"\"3.2");
    csv.setElements(elements);
    LightCsvUtil.join(csv);
    Assert.assertEquals("hoge,\"　fuga\",pi yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    LightCsvUtil.split(csv);
    LightCsvUtil.join(csv);
    Assert.assertEquals("hoge,\"　fuga\",pi yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    Log.i(csv.getLine());
  }
}