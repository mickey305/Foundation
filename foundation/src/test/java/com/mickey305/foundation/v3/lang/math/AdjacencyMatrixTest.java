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

package com.mickey305.foundation.v3.lang.math;

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
        {0, 1, 0, 0, 1},
        {1, 0, 1, 0, 0},
        {0, 1, 0, 1, 0},
        {0, 0, 1, 0, 1},
        {1, 0, 0, 1, 0}
    };
    Integer[][] t2 = {
        {0, 1, 0, 0, 1},
        {1, 0, 1, 1, 0},
        {0, 1, 0, 0, 0},
        {0, 1, 0, 0, 0},
        {1, 0, 0, 0, 0}
    };
    Integer[][] t3 = {
        {0, 1, 0, 1, 0},
        {1, 0, 1, 0, 0},
        {0, 1, 0, 1, 1},
        {1, 0, 1, 0, 0},
        {0, 0, 1, 0, 0}
    };
    Integer[][] t4 = {
        {0, 1, 1, 0, 0},
        {1, 0, 1, 0, 0},
        {1, 1, 0, 1, 1},
        {0, 0, 1, 0, 1},
        {0, 0, 1, 1, 0}
    };
    AdjacencyMatrix matrix;

//        matrix = AdjacencyMatrix.of(t1);
//        Log.i(ToStringBuilder.reflectionToString(AdjacencyMatrix.isTree(matrix)));
//        matrix = AdjacencyMatrix.of(t2);
//        Log.i(ToStringBuilder.reflectionToString(AdjacencyMatrix.isTree(matrix)));
//        matrix = AdjacencyMatrix.of(t3);
//        Log.i(ToStringBuilder.reflectionToString(AdjacencyMatrix.isTree(matrix)));
//        matrix = AdjacencyMatrix.of(t4);
//        Log.i(ToStringBuilder.reflectionToString(AdjacencyMatrix.isTree(matrix)));
  }
}