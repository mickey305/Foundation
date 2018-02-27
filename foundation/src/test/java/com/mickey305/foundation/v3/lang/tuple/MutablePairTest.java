package com.mickey305.foundation.v3.lang.tuple;

import com.mickey305.foundation.v3.util.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MutablePairTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCase_01_01() throws Exception {
        Swappable<Integer, String> swappable = Pair.of(1, "test");
        Pair<String, Integer> pair;

        pair = swappable.swap(); // test target logic
        Log.i("left: "+pair.getLeft());
        Log.i("right: "+pair.getRight());
        Assert.assertEquals("test", pair.getLeft());
        Assert.assertEquals(Integer.valueOf(1), pair.getRight());
        Assert.assertEquals("test", pair.getKey());
        Assert.assertEquals(Integer.valueOf(1), pair.getValue());

        pair = swappable.swap().swap().swap().swap().swap();
        Log.i("left: "+pair.getLeft());
        Log.i("right: "+pair.getRight()); // test target logic
        Assert.assertEquals("test", pair.getLeft());
        Assert.assertEquals(Integer.valueOf(1), pair.getRight());
        Assert.assertEquals("test", pair.getKey());
        Assert.assertEquals(Integer.valueOf(1), pair.getValue());
    }
}