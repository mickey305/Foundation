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

import com.mickey305.foundation.v3.lang.builder.unsafe.EasilyAccessibleContainer;
import com.mickey305.foundation.v3.lang.builder.unsafe.EasilyContainer;
import com.mickey305.foundation.v3.lang.builder.unsafe.InjectionEventListener;
import com.mickey305.foundation.v3.lang.builder.unsafe.ObservableDownCastBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ListUtilTest {
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_02_01() throws Exception {
    List<Super> superList = new ArrayList<>();
    for (int i = 0; i < 1; i++)
      superList.add(new Super(i));
    List<Sub> subList = ListUtil.downCastElementTo(Sub.class, superList);
    List<Sub2> subList2 = ListUtil.downCastElementTo(Sub2.class, superList);
    for (Sub elm : subList) {
      Log.i("Sub:  " + elm.getParm1());
    }
    for (Sub2 elm : subList2) {
      Log.i("Sub2: " + elm.getParm1() + " " + elm.getName() + " " + elm.getAge());
    }
  }
  
  @Test
  public void testCase_02_02() throws Exception {
    List<Super> superList = new ArrayList<>();
    Super[] superArray = ListUtil.toArray(superList);
    for (Super elm : superArray) {
      Log.i("Super: " + elm.getParm1());
    }
    
    superList = new ArrayList<>();
    for (int i = 0; i < 100; i++)
      superList.add(null);
    superArray = ListUtil.toArray(superList);
    Assert.assertEquals(100, superArray.length);
    
    superList = new ArrayList<>();
    for (int i = 0; i < 100; i++)
      superList.add(new Super(i));
    superArray = ListUtil.toArray(superList);
    for (Super elm : superArray) {
      Log.i("Super: " + elm.getParm1());
    }
  }
  
  @Test
  public void testCase_02_03() throws Exception {
    Super[] superArray = new Super[100];
    for (int i = 0; i < superArray.length; i++)
      superArray[i] = new Super(i);
    List<Super> superList = ListUtil.fromArray(superArray);
    for (Super elm : superList) {
      Log.i("Super: " + elm.getParm1());
    }
  }
  
  @Test
  public void testCase_02_04() throws Exception {
    Collection<Super> superCollection = new ArrayList<>();
    for (int i = 0; i < 100; i++)
      superCollection.add(new Super(i));
    List<Super> superList = ListUtil.downCastFrom(superCollection);
    for (Super elm : superList) {
      Log.i("Super: " + elm.getParm1());
    }
  }
  
  private abstract class AbstractSuper {
    List<String> dataLst;
    
    public AbstractSuper() {
      this.setDataLst(new ArrayList<String>());
      List<String> list = this.getDataLst();
      list.add("apple");
      list.add("orange");
      list.add("grape");
      list.add("lemon");
      list.add("melon");
      list.add("cherry");
    }
    
    public List<String> getDataLst() {
      return dataLst;
    }
    
    public void setDataLst(List<String> dataLst) {
      this.dataLst = dataLst;
    }
  }
  
  private class Super extends AbstractSuper implements DownCastable {
    private int parm1;
    
    public Super(int parm1) {
      super();
      this.setParm1(parm1);
    }
    
    @Override
    public <T extends DownCastable> T downcastTo(Class<T> subClass) {
//            return DownCastBuilder.reflectionDownCast(subClass, this);
      return ObservableDownCastBuilder.reflectionDownCast(subClass, this, new InjectionEventListener() {
        @Override
        public void before(EasilyAccessibleContainer dest, EasilyContainer src) {
          Log.i("<< Before >>");
          for (Map.Entry<Class<?>, Map<String, Object>> entry1 : dest.getFieldDataMap().entrySet()) {
            Log.i("* dest class:" + entry1.getKey().getSimpleName());
            for (Map.Entry<String, Object> entry2 : entry1.getValue().entrySet()) {
              Log.i("  key:" + entry2.getKey() + ", value:" + entry2.getValue());
            }
          }
          
          for (Map.Entry<Class<?>, Map<String, Object>> entry1 : src.getFieldDataMap().entrySet()) {
            Log.i("* src class:" + entry1.getKey().getSimpleName());
            for (Map.Entry<String, Object> entry2 : entry1.getValue().entrySet()) {
              Log.i("  key:" + entry2.getKey() + ", value:" + entry2.getValue());
            }
          }
          dest.updateField(Sub2.class, "age", 99999999);
          Log.i("-------------------------------------------------------------------------------------");
        }
        
        @Override
        public void after(EasilyAccessibleContainer dest, EasilyContainer src) {
          Log.i("<< After >>");
          for (Map.Entry<Class<?>, Map<String, Object>> entry1 : dest.getFieldDataMap().entrySet()) {
            Log.i("* dest class:" + entry1.getKey().getSimpleName());
            for (Map.Entry<String, Object> entry2 : entry1.getValue().entrySet()) {
              Log.i("  key:" + entry2.getKey() + ", value:" + entry2.getValue());
            }
          }
          
          for (Map.Entry<Class<?>, Map<String, Object>> entry1 : src.getFieldDataMap().entrySet()) {
            Log.i("* src class:" + entry1.getKey().getSimpleName());
            for (Map.Entry<String, Object> entry2 : entry1.getValue().entrySet()) {
              Log.i("  key:" + entry2.getKey() + ", value:" + entry2.getValue());
            }
          }
          Log.i("-------------------------------------------------------------------------------------");
        }
      });
    }
    
    public int getParm1() {
      return parm1;
    }
    
    public void setParm1(int parm1) {
      this.parm1 = parm1;
    }
  }
  
  private class Sub extends Super {
    
    private Sub(int parm1) {
      super(parm1);
    }
  }
  
  private class Sub2 extends Sub {
    private String name;
    private int age;
    
    private Sub2(int age) {
      super(10);
      this.setAge(age);
      List<String> list = super.getDataLst();
      list.add("car");
      list.add("train");
      list.remove("apple");
    }
    
    private Sub2() {
      this(5);
    }
//        private Sub2(int parm1) {
//            super(parm1);
//        }
    
    public String getName() {
      return name;
    }
    
    public void setName(String name) {
      this.name = name;
    }
    
    public int getAge() {
      return age;
    }
    
    public void setAge(int age) {
      this.age = age;
    }
  }
}