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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

public class JapanesePickerTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    String inputData;
    File testFile;
    Matcher matcher;

//    testFile = ResFile.get("JapanesePickerTestData001.html");
//    inputData = FileUtil.readSmallFileData(testFile);

//    testFile = ResFile.get("JapanesePickerTestData002.html");
//    inputData = FileUtil.readSmallFileData(testFile);

//    testFile = ResFile.get("JapanesePickerTestData001.json");
//    inputData = FileUtil.readSmallFileData(testFile);
    
    inputData = "<テスト>林檎　アップル Apple　ｱｯﾌﾟﾙ</テスト><TEST>林檎　アップル Apple　ｱｯﾌﾟﾙ</TEST>";
    
    matcher = JapanesePickerFactory.createPlain().build(inputData);
    Log.d("plain analyzer check");
    while (matcher.find()) {
      final MatchResult result = matcher.toMatchResult();
      Log.i("start:" + result.start() + ",end:" + result.end() + "★" + result.group() + "★");
    }
    
    matcher = JapanesePickerFactory.createDefault().build(inputData);
    Log.d("default analyzer check");
    while (matcher.find()) {
      final MatchResult result = matcher.toMatchResult();
      Log.i("start:" + result.start() + ",end:" + result.end() + "★" + result.group() + "★");
    }
    
    matcher = JapanesePickerFactory.createDefaultXmlStyle().build(inputData);
    Log.d("default-xml-style analyzer check");
    while (matcher.find()) {
      final MatchResult result = matcher.toMatchResult();
      Log.i("start:" + result.start() + ",end:" + result.end() + "★" + result.group() + "★");
    }
    
    matcher = JapanesePickerFactory.createDefaultJsonStyle().build(inputData);
    Log.d("default-json-style analyzer check");
    while (matcher.find()) {
      final MatchResult result = matcher.toMatchResult();
      Log.i("start:" + result.start() + ",end:" + result.end() + "★" + result.group() + "★");
    }
  }
}