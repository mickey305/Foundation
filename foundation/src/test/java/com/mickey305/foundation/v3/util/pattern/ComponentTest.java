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

public class ComponentTest extends AbstractCompositePatternTest {

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
    Component<TestBean> leaf6 = cn1.get(6);
    Log.i(ToStringBuilder.reflectionToString(leaf6.getParent().getObject()));
    Assert.assertEquals("cmp6", leaf6.getParent().getObject().getName());

    Component<TestBean> cmp1 = cs1.get(1);
    Assert.assertEquals(null, cmp1.getParent());
  }

  @Test
  public void testCase_01_02() throws Exception {
    Component<TestBean> leaf6 = cn1.get(6);
    Component<TestBean> cmp1 = cs1.get(1);
    Component<TestBean> root = leaf6.getRoot();
    Log.i(ToStringBuilder.reflectionToString(root.getObject()));
    Assert.assertEquals(cmp1, root);
    Assert.assertEquals(cmp1.getObject(), root.getObject());
    Assert.assertEquals(cmp1.getObject().getName(), root.getObject().getName());
    Assert.assertEquals(cmp1.getObject().getTimeStamp(), root.getObject().getTimeStamp());

    Assert.assertEquals(cmp1, cmp1.getRoot());

    cs1.get(0).add(cs1.get(4));

    Component<TestBean> cmp0 = cs1.get(0);
    root = leaf6.getRoot();
    Log.i(ToStringBuilder.reflectionToString(root.getObject()));
    Assert.assertEquals(cmp0, root);
    Assert.assertEquals(cmp0.getObject(), root.getObject());
    Assert.assertEquals(cmp0.getObject().getName(), root.getObject().getName());
    Assert.assertEquals(cmp0.getObject().getTimeStamp(), root.getObject().getTimeStamp());
  }

  @Test
  public void testCase_01_03() throws Exception {
    Component<AbstractCompositePatternTest.TestBean> cmp1 = cs1.get(1);
    Component<AbstractCompositePatternTest.TestBean> cmp3 = cs1.get(3);
    Component<AbstractCompositePatternTest.TestBean> cmp4 = cs1.get(4);
    Component<AbstractCompositePatternTest.TestBean> cmp5 = cs1.get(5);
    Component<AbstractCompositePatternTest.TestBean> leaf5 = cn1.get(5);
    Component<AbstractCompositePatternTest.TestBean> leaf7 = cn1.get(7);

    Assert.assertFalse(cmp5.belongsTo(cmp5));
    Assert.assertTrue(cmp5.belongsTo(cmp1));
    Assert.assertTrue(cmp5.belongsTo(cmp4));
    Assert.assertFalse(cmp5.belongsTo(cmp3));
    Assert.assertTrue(leaf5.belongsTo(cmp4));
    Assert.assertFalse(leaf7.belongsTo(cmp4));
  }
}