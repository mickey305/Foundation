package com.mickey305.foundation.v3.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class CollectionUtilTest {
    private static final String TAG = CollectionUtilTest.class.getSimpleName();
    private Set<String> sample1;

    @Before
    public void setUp() throws Exception {
        sample1 = new HashSet<>();
        sample1.add("sample1");
        sample1.add("sample2");
        sample1.add("sample3");
        sample1.add("sample4");
        sample1.add("sample5");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCase_02_01() throws Exception {
        Set<String> p1Sample1 = CollectionUtil.protectedSet(sample1);
        try {
            p1Sample1.iterator();
            Assert.fail();
        } catch (Throwable th) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testCase_02_02() throws Exception {
        Log.i("big-data test start");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++)
            CollectionUtil.protectedSet(sample1);
        long end = System.currentTimeMillis();
        long time = end - start;
        Log.i("big-data test end");
        Log.i("time: " + time + "(ms)");

        if (time > 500)
            Log.e("[" + TAG + "#testCase_02_02] > overhead-time too long warning message."
                    + " please tuning this testcase-target-algorithm.");
    }
}