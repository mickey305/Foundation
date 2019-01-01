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
    
    csv.setLine("\"1213 32\" ,hoge, fuga ,piyo  ,");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("1213 32", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    LightCsvUtil.join(csv);
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("1213 32", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("\"12\n1332\" ,hoge, fuga ,piyo  ,");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12\n1332", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    LightCsvUtil.join(csv);
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12\n1332", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("\"12\r1332\" ,hoge, fuga ,piyo  ,");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12\r1332", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    LightCsvUtil.join(csv);
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12\r1332", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("\"12\r\n1332\" ,hoge, fuga ,piyo  ,");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12\r\n1332", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    LightCsvUtil.join(csv);
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12\r\n1332", elements.get(0));
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
    
    csv.setLine("hoge,fuga,piyo,\"12,133.2");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("hoge,fuga,piyo,12,133.2\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("hoge,fuga,piyo,\"12,133.\"2");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("hoge,fuga,piyo,12,13\"3.2\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("12\n1332 ,hoge, fuga ,piyo  ,");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("12\r1332 ,hoge, fuga ,piyo  ,");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    
    csv.setLine("12\r\n1332 ,hoge, fuga ,piyo  ,");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("12\"\"1332 ,hoge, fuga ,piyo  ,");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("hoge, fuga ,piyo  ,\"\"12,133.2\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,13\"3.2\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2\"\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    csv.setLine("hoge, fuga ,piyo  ,12,133.2\"");
    try {
      LightCsvUtil.split(csv);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Log.e(e.getMessage());
    }
    
    //////////////////////////////////////////////////////////////////////////
    // simple check //////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("12,133.2", elements.get(3));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,　\"12,133.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("12,133.2", elements.get(3));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  , \"12,133.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("12,133.2", elements.get(3));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2\" ");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("12,133.2", elements.get(3));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,1\"\"33.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("12,1\"33.2", elements.get(3));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,\"\"\"12,133.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("\"12,133.2", elements.get(3));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,133.2\"\"\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("12,133.2\"", elements.get(3));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("hoge, fuga ,piyo  ,\"12,1\"\"\"\"33.2\"");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(4, elements.size());
    Assert.assertEquals("hoge", elements.get(0));
    Assert.assertEquals("fuga", elements.get(1));
    Assert.assertEquals("piyo", elements.get(2));
    Assert.assertEquals("12,1\"\"33.2", elements.get(3));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine(" \"12,13 3.2\n\",hoge, fuga ,piyo  ,");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12,13 3.2\n", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
    Log.i(Arrays.toString(elements.toArray()));
    
    csv.setLine("\"12,\n133.2\" ,hoge, fuga ,piyo  ,");
    LightCsvUtil.split(csv);
    elements = csv.getElements();
    Assert.assertEquals(5, elements.size());
    Assert.assertEquals("12,\n133.2", elements.get(0));
    Assert.assertEquals("hoge", elements.get(1));
    Assert.assertEquals("fuga", elements.get(2));
    Assert.assertEquals("piyo", elements.get(3));
    Assert.assertEquals("", elements.get(4));
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
    
    elements = new ArrayList<>();
    elements.add(" ");
    elements.add("fu　ga");
    elements.add("pi yo");
    elements.add("pi\"yo2");
    elements.add("12,13\"\"3.2");
    csv.setElements(elements);
    LightCsvUtil.join(csv);
    Assert.assertEquals("\" \",fu　ga,pi yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    LightCsvUtil.split(csv);
    LightCsvUtil.join(csv);
    Assert.assertEquals("\" \",fu　ga,pi yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    Log.i(csv.getLine());
    
    elements = new ArrayList<>();
    elements.add("　");
    elements.add("fu　ga");
    elements.add("pi yo");
    elements.add("pi\"yo2");
    elements.add("12,13\"\"3.2");
    csv.setElements(elements);
    LightCsvUtil.join(csv);
    Assert.assertEquals("\"　\",fu　ga,pi yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    LightCsvUtil.split(csv);
    LightCsvUtil.join(csv);
    Assert.assertEquals("\"　\",fu　ga,pi yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    Log.i(csv.getLine());
    
    elements = new ArrayList<>();
    elements.add("\t");
    elements.add("fu　ga");
    elements.add("pi yo");
    elements.add("pi\"yo2");
    elements.add("12,13\"\"3.2");
    csv.setElements(elements);
    LightCsvUtil.join(csv);
    Assert.assertEquals("\"\t\",fu　ga,pi yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    LightCsvUtil.split(csv);
    LightCsvUtil.join(csv);
    Assert.assertEquals("\"\t\",fu　ga,pi yo,\"pi\"\"yo2\",\"12,13\"\"\"\"3.2\"", csv.getLine());
    Log.i(csv.getLine());
    
    elements = new ArrayList<>();
    elements.add(""); // non escape
    elements.add("'"); // non escape
    elements.add("，"); // non escape
    elements.add("″"); // non escape
    elements.add("テスト"); // non escape
    elements.add("ﾃｽﾄ"); // non escape
    elements.add("test"); // non escape
    elements.add("\t");
    elements.add("\n");
    elements.add("\r");
    elements.add("\r\n");
    elements.add(" ");
    elements.add("　");
    elements.add(",");
    elements.add("\"");
    elements.add("\ttest");
    elements.add("\ntest");
    elements.add("\rtest");
    elements.add("\r\ntest");
    elements.add(" test");
    elements.add("　test");
    elements.add(",test");
    elements.add("\"test");
    elements.add("test\ttest"); // non escape
    elements.add("test\ntest");
    elements.add("test\rtest");
    elements.add("test\r\ntest");
    elements.add("test test"); // non escape
    elements.add("test　test"); // non escape
    elements.add("test,test");
    elements.add("test\"test");
    elements.add("test\t");
    elements.add("test\n");
    elements.add("test\r");
    elements.add("test\r\n");
    elements.add("test ");
    elements.add("test　");
    elements.add("test,");
    elements.add("test\"");
    final int expSize = elements.size();
    csv.setElements(elements);
    LightCsvUtil.join(csv);
    Assert.assertEquals(",',，,″,テスト,ﾃｽﾄ,test,"
            + "\"\t\",\"\n\",\"\r\",\"\r\n\",\" \",\"　\",\",\",\"\"\"\","
            + "\"\ttest\",\"\ntest\",\"\rtest\",\"\r\ntest\",\" test\",\"　test\",\",test\",\"\"\"test\","
            + "test\ttest,\"test\ntest\",\"test\rtest\",\"test\r\ntest\",test test,test　test,\"test,test\",\"test\"\"test\","
            + "\"test\t\",\"test\n\",\"test\r\",\"test\r\n\",\"test \",\"test　\",\"test,\",\"test\"\"\"",
        csv.getLine());
    LightCsvUtil.split(csv);
    LightCsvUtil.join(csv);
    Assert.assertEquals(",',，,″,テスト,ﾃｽﾄ,test,"
            + "\"\t\",\"\n\",\"\r\",\"\r\n\",\" \",\"　\",\",\",\"\"\"\","
            + "\"\ttest\",\"\ntest\",\"\rtest\",\"\r\ntest\",\" test\",\"　test\",\",test\",\"\"\"test\","
            + "test\ttest,\"test\ntest\",\"test\rtest\",\"test\r\ntest\",test test,test　test,\"test,test\",\"test\"\"test\","
            + "\"test\t\",\"test\n\",\"test\r\",\"test\r\n\",\"test \",\"test　\",\"test,\",\"test\"\"\"",
        csv.getLine());
    Assert.assertEquals(expSize, csv.getElements().size());
    Log.i(csv.getLine());
  }
}