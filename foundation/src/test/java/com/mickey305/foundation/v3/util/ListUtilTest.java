package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.lang.builder.DownCastBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        for (int i = 0; i < 100; i++)
            superList.add(new Super(i));
        List<Sub> subList = ListUtil.downCastElementTo(Sub.class, superList);
        List<Sub2> subList2 = ListUtil.downCastElementTo(Sub2.class, superList);
        for (Sub elm: subList) {
            Log.i("Sub:  "+elm.getParm1());
        }
        for (Sub2 elm: subList2) {
            Log.i("Sub2: "+elm.getParm1()+" "+elm.getName()+" "+elm.getAge());
        }
    }

    @Test
    public void testCase_02_02() throws Exception {
        List<Super> superList = new ArrayList<>();
        Super[] superArray = ListUtil.toArray(superList);
        for (Super elm: superArray) {
            Log.i("Super: "+elm.getParm1());
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
        for (Super elm: superArray) {
            Log.i("Super: "+elm.getParm1());
        }
    }

    @Test
    public void testCase_02_03() throws Exception {
        Super[] superArray = new Super[100];
        for (int i = 0; i < superArray.length; i++)
            superArray[i] = new Super(i);
        List<Super> superList = ListUtil.fromArray(superArray);
        for (Super elm: superList) {
            Log.i("Super: "+elm.getParm1());
        }
    }

    @Test
    public void testCase_02_04() throws Exception {
        Collection<Super> superCollection = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            superCollection.add(new Super(i));
        List<Super> superList = ListUtil.downCastFrom(superCollection);
        for (Super elm: superList) {
            Log.i("Super: "+elm.getParm1());
        }
    }

    private class Super implements DownCastable {
        private int parm1;

        public Super(int parm1) {
            this.setParm1(parm1);
        }

        @Override
        public <T extends DownCastable> T downcastTo(Class<T> subClass) {
            return DownCastBuilder.reflectionDownCast(subClass, this);
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