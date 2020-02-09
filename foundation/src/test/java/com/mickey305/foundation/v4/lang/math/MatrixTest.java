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

package com.mickey305.foundation.v4.lang.math;

import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v4.lang.math.builder.BuilderSquareMatrix;
import com.mickey305.foundation.v4.lang.math.context.MatrixContextFactory;
import com.mickey305.foundation.v4.lang.math.factory.ElementInitializerFactory;
import com.mickey305.foundation.v4.lang.math.factory.OperationBigDecimalFactory;
import org.apache.commons.math3.fraction.BigFraction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class MatrixTest {
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
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
//        Matrix m1 = Matrix.of(t1);
//        Matrix m2 = Matrix.of(t2);
//        Matrix m3 = Matrix.of(t3);

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

//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfRow()));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageArrayOfRow()));
//        Log.i(ToStringBuilder.reflectionToString(m1.sumArrayOfColumn()));
//        Log.i(ToStringBuilder.reflectionToString(m1.averageOfColumn()));
//        Log.i(ToStringBuilder.reflectionToString(Matrix.add(m1, m1).getTable()));
//        Log.i(ToStringBuilder.reflectionToString(Matrix.add(m1, m1).isSquare()));
//        Log.i(ToStringBuilder.reflectionToString(Matrix.multi(m2, m3).getTable()));
//        Log.i(ToStringBuilder.reflectionToString(Matrix.multi(m2, m3).isSquare()));
//        Log.i(ToStringBuilder.reflectionToString(Matrix.multi(m2, m3).isScalar()));
//        Log.i(ToStringBuilder.reflectionToString(Matrix.of(8).getTable()));
//        Log.i(ToStringBuilder.reflectionToString(Matrix.horizontalBind(m1, m1).getTable()));
//        Log.i(ToStringBuilder.reflectionToString(m1.getMaxCell()));
//        Log.i(ToStringBuilder.reflectionToString(m1.getMinCell()));
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

//        IElementInitializer<Integer> initializer = ElementInitializerFactory.intIni();
//        Log.i(Arrays.deepToString(initializer.table(3, 4)));
    SquareMatrix<Double> matrix21 = new BuilderSquareMatrix<Double>()
        .context(MatrixContextFactory.getSharedFactory())
        .cookbook(() -> new Double[][]{
            {5.1d, 2.5d, 1.2d},
            {1.1d, 1.5d, 1.2d},
            {2.3d, 2.5d, 6.2d}
        })
        .build();
    SquareMatrix<Double> matrix22, matrix23;
    SquareMatrix<BigDecimal> matrix01 = new BuilderSquareMatrix<BigDecimal>()
        .operator(OperationBigDecimalFactory.getInstance().scale(3))
        .initializer(ElementInitializerFactory.getFactory())
        .cookbook(() -> {
          return new BigDecimal[][]{
              {new BigDecimal("5.1"), new BigDecimal("2.5"), new BigDecimal("1.2")},
              {new BigDecimal("1.1"), new BigDecimal("1.5"), new BigDecimal("1.2")},
              {new BigDecimal("2.3"), new BigDecimal("2.5"), new BigDecimal("6.2")}
          };
//                    IElementInitializer<BigDecimal> ini = ElementInitializerFactory.bigDcmlIni();
//                    BigDecimal[][] table = ini.table(4, 4);
//                    ArrayUtil.fill(table, ini.one());
//                    return table;
        })
        .build();
    Matrix<BigDecimal> matrix02, matrix03;
    SquareMatrix<BigFraction> matrix11 = new BuilderSquareMatrix<BigFraction>()
        .context(MatrixContextFactory.getFactory())
        .cookbook(() -> {
          return new BigFraction[][]{
              {new BigFraction(51, 10), new BigFraction(25, 10), new BigFraction(12, 10)},
              {new BigFraction(11, 10), new BigFraction(15, 10), new BigFraction(12, 10)},
              {new BigFraction(23, 10), new BigFraction(25, 10), new BigFraction(62, 10)}
          };
//                    IElementInitializer<BigDecimal> ini = ElementInitializerFactory.bigDcmlIni();
//                    BigDecimal[][] table = ini.table(4, 4);
//                    ArrayUtil.fill(table, ini.one());
//                    return table;
        })
        .build();
    Matrix<BigFraction> matrix12, matrix13;

//        matrix21  = matrix21.exp(2);
//        Log.i("matrix:" + Arrays.deepToString(matrix21.getTable()));
    matrix23 = matrix21.createInverseMatrix();
    Log.i("matrix23:" + Arrays.deepToString(matrix23.getTable()));

//        Log.i("matrix01:" + Arrays.deepToString(matrix01.getTable()));
//        matrix02 = matrix02.exp(2);
//        matrix02 = matrix01.multi(matrix01.exp(3));
//        Log.i("matrix02:" + Arrays.deepToString(matrix02.getTable()));
//    matrix03 = matrix01.createInverseMatrix(
//        OperationBigFractionFactory.getInstance(),
//        ElementInitializerFactory.bigFractionIni());
//    Log.i("matrix03:" + Arrays.deepToString(matrix03.getTable()));

//        Log.i("matrix11:" + Arrays.deepToString(matrix11.getTable()));
//        matrix12 = matrix12.exp(2);
//        matrix12 = matrix11.multi(matrix11.exp(3));
//        Log.i("matrix12:" + Arrays.deepToString(matrix12.getTable()));
    matrix13 = matrix11.createInverseMatrix();
    Log.i("matrix13:" + Arrays.deepToString(matrix13.getTable()));
  }
}