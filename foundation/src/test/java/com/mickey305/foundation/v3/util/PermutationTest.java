package com.mickey305.foundation.v3.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PermutationTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void next() throws Exception {
        Permutation<Integer> list = new Permutation<>(new Integer[]{1, 2, 3, 4});
        int i = 0;
        do {
            Log.i(ToStringBuilder.reflectionToString(list.getElements()));
            i++;
        } while (list.next());
        Log.i("i=" + i);
    }

}