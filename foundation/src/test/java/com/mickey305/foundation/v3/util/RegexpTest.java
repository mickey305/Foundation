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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegexpTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  /**
   * test target: {@link Regexp#HALF_KANA}
   *
   * @throws Exception
   */
  @Test
  public void testCase_01_01() throws Exception {
    String stmt;
    boolean result;
    
    stmt = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾜｦﾝｧｨｩｪｫｬｭｮﾞﾟｰ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(result);
    
    stmt = "ﾃｽﾄﾃﾞｰﾀ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(result);
    
    stmt = "ﾃｽﾄ ﾃﾞｰﾀ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "ﾃｽﾄあﾃﾞｰﾀ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "ﾃｽﾄ　ﾃﾞｰﾀ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "あﾃｽﾄﾃﾞｰﾀ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "ﾃｽﾄﾃﾞｰﾀあ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = " ﾃｽﾄﾃﾞｰﾀ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "ﾃｽﾄﾃﾞｰﾀ ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "　ﾃｽﾄﾃﾞｰﾀ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "ﾃｽﾄﾃﾞｰﾀ　";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "テストデータ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "  ";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
    stmt = "　　";
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Exact).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Partial).matcher(stmt).matches();
    Assert.assertTrue(!result);
    result = Regexp.pattern(Regexp.HALF_KANA, Regexp.CompileType.Plain).matcher(stmt).matches();
    Assert.assertTrue(!result);
    
  }
  
}