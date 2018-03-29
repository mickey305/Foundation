package com.mickey305.foundation.v3.lang.math;

import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SymmetricPermutationGroupTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCase_01_01() throws Exception {
        Integer[][] t1 = {
                {1, 2, 3},
                {2, 3, 1}
        };
//        Integer[][] t2 = {
//                {1, 2, 3},
//                {3, 2, 1}
//        };
        Integer[][] t2 = {
                {1, 3},
                {3, 1}
        };


        // 1 2 3
        // 1 3 2
        //
        // 3 1 2
        // 3 2 1
        //
        SymmetricPermutationGroup p1 = new SymmetricPermutationGroup(t1);
        SymmetricPermutationGroup p2 = new SymmetricPermutationGroup(t2);
        SymmetricPermutationGroup pr = SymmetricPermutationGroup.multi(p1, p2);

        Log.i(ToStringBuilder.reflectionToString(pr.getTable()));
    }
}