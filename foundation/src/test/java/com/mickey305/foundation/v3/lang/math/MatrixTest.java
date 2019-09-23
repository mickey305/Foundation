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

import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testCase_01_01() throws Exception {
    Float[][] t1 = {
        {1.2f, 1.1f, 1.3f, 1.0f, 1.4f},
        {1.2f, 1.1f, 1.3f, 1.0f, 1.4f},
        {1.2f, 1.1f, 1.3f, 1.0f, 1.4f},
        {1.2f, 1.1f, 1.3f, 1.0f, 1.4f},
        {1.2f, 1.1f, 1.3f, 1.0f, 1.3f}
    };
//        Integer[][] t1 = {
//                {2, 1, 3, -10, 4},
//                {2, 1, 3, 0, 4},
//                {2, 1, 3, 0, 4},
//                {2, 1, 3, 0, 4},
//                {2, 1, 3, 1, 3}
//        };
    Integer[][] t2 = {
        {3, -2, 1}
    };
    Integer[][] t3 = {
        {5},
        {3},
        {-4}
    };
    Matrix m1 = Matrix.of(t1);
    Matrix m2 = Matrix.of(t2);
    Matrix m3 = Matrix.of(t3);

//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfRowWith(Integer.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfRowWith(Long.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfRowWith(Double.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfRowWith(Float.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfRowWith(Short.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfRowWith(Byte.class)));
//        Log.ln();
//        Log.i(ToStringBuilder.reflectionToString(m1.averageArrayOfRowWith(Integer.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageArrayOfRowWith(Long.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageArrayOfRowWith(Double.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageArrayOfRowWith(Float.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageArrayOfRowWith(Short.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageArrayOfRowWith(Byte.class)));
//        Log.ln();
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfColumnWith(Integer.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfColumnWith(Long.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfColumnWith(Double.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfColumnWith(Float.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfColumnWith(Short.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfColumnWith(Byte.class)));
//        Log.ln();
//        Log.i(ToStringBuilder.reflectionToString(m1.averageOfColumnWith(Integer.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageOfColumnWith(Long.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageOfColumnWith(Double.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageOfColumnWith(Float.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageOfColumnWith(Short.class)));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageOfColumnWith(Byte.class)));
    Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfRow()));
    Log.i(ToStringBuilder.reflectionToString(m1.averageArrayOfRow()));
    Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfColumn()));
    Log.i(ToStringBuilder.reflectionToString(m1.averageOfColumn()));
    Log.i(ToStringBuilder.reflectionToString(Matrix.add(m1, m1).getTable()));
    Log.i(ToStringBuilder.reflectionToString(Matrix.add(m1, m1).isSquare()));
    Log.i(ToStringBuilder.reflectionToString(Matrix.multi(m2, m3).getTable()));
    Log.i(ToStringBuilder.reflectionToString(Matrix.multi(m2, m3).isSquare()));
    Log.i(ToStringBuilder.reflectionToString(Matrix.multi(m2, m3).isScalar()));
    Log.i(ToStringBuilder.reflectionToString(Matrix.of(8).getTable()));
    Log.i(ToStringBuilder.reflectionToString(Matrix.horizontalBind(m1, m1).getTable()));
    Log.i(ToStringBuilder.reflectionToString(m1.getMaxCell()));
    Log.i(ToStringBuilder.reflectionToString(m1.getMinCell()));
  }

  @Test
  public void testCase_01_02() throws Exception {
//        Integer[][] t1 = {
//                { 1,  1, -1},
//                {-2,  0,  1},
//                { 0,  2,  1}
//        };
//        Matrix m1 = Matrix.of(t1);
//
//        Log.i(ToStringBuilder.reflectionToString(m1.determinant()));
  }

  @Test
  public void testCase_01_03() throws Exception {
//        Integer[][] t1 = {
//                { 1,  1, -1},
//                {-2,  0,  1},
//                { 0,  2,  1}
//        };
//        Integer[][] t2 = {
//                { 2,  1,  4,  1},
//                { 1,  2,  2, -1},
//                { 3,  1,  4,  2},
//                {-4,  2, -3,  1},
//        };
//        Matrix m = Matrix.of(t2);
//
//        for (Number[] rec: m.createInverseMatrix().getTable())
//            Log.i(ToStringBuilder.reflectionToString(rec));
  }
}