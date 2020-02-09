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

package com.mickey305.foundation.v3.util.pattern;

import com.mickey305.foundation.v3.resource.test.AbstractCompositePatternTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LeafTest extends AbstractCompositePatternTest {

  @Before
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
  public void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void testCase_01_01() throws Exception {
    Composite<TestBean> cmp6 = cs1.get(6);
    Component<TestBean> target = cn1.get(5);

    Assert.assertEquals(3, cmp6.getChildren().size());
    Assert.assertEquals(cmp6, target.getParent());

    Assert.assertTrue(target.removeParent()); // Test target method

    Assert.assertEquals(2, cmp6.getChildren().size());
    Assert.assertEquals(null, target.getParent());
  }

  @Test
  public void testCase_01_02() throws Exception {
    Composite<TestBean> oldParent;
    Composite<TestBean> parent = cs1.get(6);
    Component<TestBean> target = cn1.get(5);

    Assert.assertEquals(3, parent.getChildren().size());
    Assert.assertEquals(parent, target.getParent());

    oldParent = parent;
    parent = cs1.get(1);

    Assert.assertEquals(5, parent.getChildren().size());

    Assert.assertTrue(target.addParent(parent)); // Test target method

    Assert.assertEquals(2, oldParent.getChildren().size());
    Assert.assertEquals(6, parent.getChildren().size());
    Assert.assertEquals(parent, target.getParent());
  }
}