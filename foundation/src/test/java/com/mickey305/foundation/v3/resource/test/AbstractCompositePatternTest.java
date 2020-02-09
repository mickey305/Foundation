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

package com.mickey305.foundation.v3.resource.test;

import com.mickey305.foundation.v3.compat.stream.Supplier;
import com.mickey305.foundation.v3.util.pattern.Component;
import com.mickey305.foundation.v3.util.pattern.Composite;
import com.mickey305.foundation.v3.util.pattern.Leaf;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public abstract class AbstractCompositePatternTest {
  protected List<Composite<TestBean>> cs1;
  protected List<Component<TestBean>> cn1;

  @Before
  public void setUp() throws Exception {
    cn1 = new ArrayList<>();
    cs1 = new ArrayList<>();
    Supplier<Collection<Component<TestBean>>> supplier = new Supplier<Collection<Component<TestBean>>>() {
      @Override
      public Collection<Component<TestBean>> get() {
        return new HashSet<>();
      }
    };

    // Create Test Data
    cs1.add(new Composite<>(new TestBean("cmp0")));
    cs1.add(new Composite<>(new TestBean("cmp1")));
    cs1.add(new Composite<>(new TestBean("cmp2")));
    cs1.add(new Composite<>(new TestBean("cmp3"), supplier));
    cs1.add(new Composite<>(new TestBean("cmp4"), supplier));
    cs1.add(new Composite<>(new TestBean("cmp5")));
    cs1.add(new Composite<>(new TestBean("cmp6"), supplier));

    cn1.add(new Composite<>(new TestBean("cmp0"), supplier));
    cn1.add(new Leaf<>(new TestBean("leaf1")));
    cn1.add(new Leaf<>(new TestBean("leaf2")));
    cn1.add(new Leaf<>(new TestBean("leaf3")));
    cn1.add(new Leaf<>(new TestBean("leaf4")));
    cn1.add(new Leaf<>(new TestBean("leaf5")));
    cn1.add(new Leaf<>(new TestBean("leaf6")));
    cn1.add(new Leaf<>(new TestBean("leaf7")));
    cn1.add(new Leaf<>(new TestBean("leaf8")));
  }

  @After
  public void tearDown() throws Exception {
  }

  protected class TestBean {
    private String name;
    private long timeStamp;

    public TestBean() {
    }

    public TestBean(String name) {
      this.setName(name);
      this.setTimeStamp(System.currentTimeMillis());
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public long getTimeStamp() {
      return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
      this.timeStamp = timeStamp;
    }
  }
}
