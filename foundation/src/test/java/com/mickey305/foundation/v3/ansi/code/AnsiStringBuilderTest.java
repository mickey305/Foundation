/*
 * Copyright (c) 2017 - 2020 K.Misaki
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

package com.mickey305.foundation.v3.ansi.code;

import com.mickey305.foundation.v3.compat.exception.wrap.BeanValidationException;
import com.mickey305.foundation.v3.validation.ValidationInvoker;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class AnsiStringBuilderTest {
  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  /**
   * <p>CASE 1-1
   * target: {@link AnsiStringBuilder(String)}</>
   * <p>コンストラクタのバリデーションチェック</>
   *
   * @throws Exception 例外
   */
  @Test
  public void testCase_01_01() throws Exception {
    Constructor<AnsiStringBuilder> bean = AnsiStringBuilder.class.getConstructor(String.class);
    Escape[] escapeLst = Escape.values();
    for (Escape escape : escapeLst) {
      try {
        ValidationInvoker.validateConstructor(bean, new Object[]{escape.code()});
        Assert.assertTrue(escape.code(), false);
      } catch (BeanValidationException e) {
        Assert.assertTrue(true); // assertion step
      } catch (Exception e) {
        Assert.assertTrue(escape.code(), false);
      }
    }
    String[] stmt = new String[]{
        "apple", "orange", "#d1@asq)", "[]^&%$!\"", "*(){}?><ASKI~`\\|.,-+_", null, "", " ", "　", "日本語"};
    for (String str : stmt) {
      ValidationInvoker.validateConstructor(bean, new Object[]{str});
    }
  }

  /**
   * <p>CASE 2-1
   * target: {@link AnsiStringBuilder#append(String)}</>
   * <p>メソッドのバリデーションチェック</>
   *
   * @throws Exception 例外
   */
  @Test
  public void testCase_02_01() throws Exception {
    AnsiStringBuilder sb = new AnsiStringBuilder();
    String[] stmt = new String[]{
        "apple", "orange", "#d1@asq)", "[]^&%$!\"", "*(){}?><ASKI~`\\|.,-+_", "", " ", "　", "日本語"};
    for (String str : stmt) {
      Pair<Class<?>, Object> arg1 = Pair.of(str.getClass(), str);
      ValidationInvoker.validateMethod(sb, "append", arg1);
    }

    Escape[] escapes = Escape.values();
    for (Escape escape : escapes) {
      try {
        Pair<Class<?>, Object> arg1 = Pair.of(String.class, escape.code());
        ValidationInvoker.validateMethod(sb, "append", arg1);
        Assert.assertTrue(escape.code(), false);
      } catch (BeanValidationException e) {
        Assert.assertTrue(true); // assertion step
      } catch (Exception e) {
        Assert.assertTrue(escape.code(), false);
      }
    }
  }

  /**
   * <p>CASE 2-2
   * target: {@link AnsiStringBuilder#append(Escape)}</>
   * <p>メソッドのバリデーションチェック</>
   *
   * @throws Exception 例外
   */
  @Test
  public void testCase_02_02() throws Exception {
    AnsiStringBuilder sb = new AnsiStringBuilder();
    Escape[] escapes = Escape.values();
    for (Escape escape : escapes) {
      Pair<Class<?>, Object> arg1 = Pair.of(escape.getClass(), escape);
      ValidationInvoker.validateMethod(sb, "append", arg1);
    }
  }

  /**
   * <p>CASE 2-3
   * target: {@link AnsiStringBuilder#insert(int, Escape)}</>
   * <p>メソッドのバリデーションチェック</>
   *
   * @throws Exception 例外
   */
  @Test
  public void testCase_02_03() throws Exception {
    AnsiStringBuilder sb = new AnsiStringBuilder();
    Escape escape = Escape.Red;
    Pair<Class<?>, Object> arg1 = Pair.of(int.class, 0);
    Pair<Class<?>, Object> arg2 = Pair.of(escape.getClass(), escape);
    ValidationInvoker.validateMethod(sb, "insert", arg1, arg2);
  }

  /**
   * <p>CASE 2-4
   * target: {@link AnsiStringBuilder#insert(int, String)}</>
   * <p>メソッドのバリデーションチェック</>
   *
   * @throws Exception 例外
   */
  @Test
  public void testCase_02_04() throws Exception {
    AnsiStringBuilder sb = new AnsiStringBuilder();
    Escape escape = Escape.Red;
    Pair<Class<?>, Object> arg1 = Pair.of(int.class, 0);
    Pair<Class<?>, Object> arg2 = Pair.of(String.class, escape.code());
    try {
      ValidationInvoker.validateMethod(sb, "insert", arg1, arg2);
      Assert.assertTrue(escape.code(), false);
    } catch (BeanValidationException e) {
      Assert.assertTrue(true); // assertion step
    } catch (Exception e) {
      Assert.assertTrue(escape.code(), false);
    }
  }
}
