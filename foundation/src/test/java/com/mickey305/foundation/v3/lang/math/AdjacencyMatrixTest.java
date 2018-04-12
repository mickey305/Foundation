package com.mickey305.foundation.v3.lang.math;

import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdjacencyMatrixTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCase_01_01() throws Exception {
        Integer[][] t1 = {
                {0,1,0,0,1},
                {1,0,1,0,0},
                {0,1,0,1,0},
                {0,0,1,0,1},
                {1,0,0,1,0}
        };
        Integer[][] t2 = {
                {0,1,0,0,1},
                {1,0,1,1,0},
                {0,1,0,0,0},
                {0,1,0,0,0},
                {1,0,0,0,0}
        };
        Integer[][] t3 = {
                {0,1,0,1,0},
                {1,0,1,0,0},
                {0,1,0,1,1},
                {1,0,1,0,0},
                {0,0,1,0,0}
        };
        Integer[][] t4 = {
                {0,1,1,0,0},
                {1,0,1,0,0},
                {1,1,0,1,1},
                {0,0,1,0,1},
                {0,0,1,1,0}
        };
        AdjacencyMatrix matrix;

        matrix = AdjacencyMatrix.of(t1);
        Log.i(ToStringBuilder.reflectionToString(AdjacencyMatrix.isTree(matrix)));
        matrix = AdjacencyMatrix.of(t2);
        Log.i(ToStringBuilder.reflectionToString(AdjacencyMatrix.isTree(matrix)));
        matrix = AdjacencyMatrix.of(t3);
        Log.i(ToStringBuilder.reflectionToString(AdjacencyMatrix.isTree(matrix)));
        matrix = AdjacencyMatrix.of(t4);
        Log.i(ToStringBuilder.reflectionToString(AdjacencyMatrix.isTree(matrix)));
    }
}