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

package com.mickey305.foundation.v3.util.pattern;

import com.mickey305.foundation.v3.resource.test.AbstractCompositePatternTest;
import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CompositeTest extends AbstractCompositePatternTest {

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();

    cs1.get(1).add(cs1.get(2));
    cs1.get(1).add(cs1.get(3));
    cs1.get(1).add(cs1.get(4));
    cs1.get(4).add(cs1.get(5));
    cs1.get(4).add(cs1.get(6));

    cs1.get(1).add(cn1.get(3));
    cs1.get(1).add(cn1.get(7));
    cs1.get(4).add(cn1.get(1));
    cs1.get(4).add(cn1.get(2));
    cs1.get(6).add(cn1.get(4));
    cs1.get(6).add(cn1.get(5));
    cs1.get(6).add(cn1.get(6));
  }

  @After
  @Override
  public void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void testCase_01_01() throws Exception {
    List<String> results = Arrays.asList("leaf4", "leaf5", "leaf6");
    Composite<TestBean> cmp6 = cs1.get(6);
    for (Component<TestBean> child : cmp6.getChildren()) {
      Log.i(ToStringBuilder.reflectionToString(child.getObject()));
      Assert.assertTrue(results.contains(child.getObject().getName()));
    }
  }

  @Test
  public void testCase_01_02() throws Exception {
    Composite<TestBean> cmp1 = cs1.get(1);
    Composite<TestBean> cmp4 = cs1.get(4);
    Component<TestBean> cmp5 = cs1.get(5);
    Composite<TestBean> cmp6 = cs1.get(6);
    Component<TestBean> leaf1 = cn1.get(1);
    Component<TestBean> leaf2 = cn1.get(2);
    Component<TestBean> leaf5 = cn1.get(5);
    Component<TestBean> leaf7 = cn1.get(7);

    Assert.assertFalse(cmp1.contains(cmp1));
    Assert.assertTrue(cmp1.contains(cmp5));
    Assert.assertTrue(cmp4.contains(leaf1));
    Assert.assertTrue(cmp4.contains(leaf5));
    Assert.assertFalse(cmp4.contains(leaf7));
    Assert.assertFalse(cmp6.contains(leaf2));
  }

  @Test
  public void testCase_01_03() throws Exception {
    Composite<TestBean> cmp1 = cs1.get(1);
    Component<TestBean> target = cs1.get(4);

    Assert.assertEquals(5, cmp1.getChildren().size());
    Assert.assertEquals(cmp1, target.getParent());

    Assert.assertTrue(target.removeParent()); // Test target method

    Assert.assertEquals(4, cmp1.getChildren().size());
    Assert.assertEquals(null, target.getParent());
  }

  @Test
  public void testCase_01_04() throws Exception {
    Composite<TestBean> oldParent;
    Composite<TestBean> parent = cs1.get(1);
    Component<TestBean> target = cs1.get(4);

    Assert.assertEquals(5, parent.getChildren().size());
    Assert.assertEquals(parent, target.getParent());

    oldParent = parent;
    parent = cs1.get(6);

    Assert.assertEquals(3, parent.getChildren().size());

    Assert.assertTrue(target.addParent(parent)); // Test target method

    Assert.assertEquals(4, oldParent.getChildren().size());
    Assert.assertEquals(4, parent.getChildren().size());
    Assert.assertEquals(parent, target.getParent());
  }

  @Test
  public void testCase_01_05() throws Exception {
    Composite<TestBean> target = cs1.get(6);
    Component<TestBean> cmp4 = cs1.get(4);
    Component<TestBean> leaf4 = cn1.get(4);

    Assert.assertEquals(cmp4, target.getParent());
    Assert.assertEquals(3, target.getChildren().size());
    Assert.assertTrue(target.contains(leaf4));

    Assert.assertTrue(target.add(leaf4)); // Test target method

    Assert.assertEquals(cmp4, target.getParent());
    Assert.assertEquals(3, target.getChildren().size());
    Assert.assertTrue(target.contains(leaf4));
  }

  @Test
  public void testCase_50_01() throws Exception {
    // BIG Collection Test
    Log.i("additional data creation start!");
    StringBuilder sb = new StringBuilder();
    final int offset = cs1.size() - 1;
    for (int i = 0; i < 1_000_000; i++) {
      cs1.add(new Composite<>(new TestBean(sb.append("added-bean-").append(i).toString())));
      cs1.get(i + offset).add(cs1.get(i + offset + 1));
      sb.setLength(0);
    }
    Log.i("additional data creation complete!");
    Component<TestBean> target = cs1.get(950_000);
    Composite<TestBean> cmp1 = cs1.get(1);
    Assert.assertTrue(cmp1.contains(target));
    Log.i("big collection data test complete!");
  }
}