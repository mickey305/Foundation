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

package com.mickey305.foundation.v4.lang.math.handler;

import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v4.lang.math.SquareMatrix;
import com.mickey305.foundation.v4.lang.math.builder.BuilderSquareMatrix;
import com.mickey305.foundation.v4.lang.math.context.MatrixContextFactory;
import com.mickey305.foundation.v4.lang.math.factory.ElementInitializerFactory;
import com.mickey305.foundation.v4.lang.math.factory.OperationBigDecimalFactory;
import com.mickey305.foundation.v4.lang.math.factory.OperationFactory;
import com.mickey305.foundation.v4.lang.math.operator.IOperationFactory;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HandlerSquareMatrixTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    SquareMatrix<BigFraction> matrixResult1;
    SquareMatrix<BigFraction> matrixSeed;
    SquareMatrix<BigFraction> matrixSeed1 = new BuilderSquareMatrix<BigFraction>()
        .context(MatrixContextFactory.getSharedFactory())
        .cookbook(() -> new BigFraction[][]{
            {new BigFraction(51, 10), new BigFraction(25, 10), new BigFraction(12, 10)},
            {new BigFraction(11, 10), new BigFraction(15, 10), new BigFraction(12, 10)},
            {new BigFraction(23, 10), new BigFraction(25, 10), new BigFraction(62, 10)}
        })
        .build();
    
    matrixSeed = matrixSeed1;
  
    matrixResult1 = matrixSeed.createInverseMatrix();
    
    HandlerSquareMatrix<BigFraction, BigFraction> handler1 = new HandlerSquareMatrix<>();
    handler1
        .setOperator(OperationFactory.getSharedFactory())
        .setInitializer(ElementInitializerFactory.getSharedFactory())
        .prepare(matrixSeed);
    handler1.execute(SquareMatrix::createInverseMatrix);
    SquareMatrix<BigFraction> matrixResult2 = handler1.finalizer();
  
    HandlerSquareMatrix<BigFraction, BigDecimal> handler2 = new HandlerSquareMatrix<>();
    IOperationFactory<BigDecimal> tmpFactory = new OperationBigDecimalFactory().scale(10);
    handler2
        .setOperator(tmpFactory)
        .setInitializer(ElementInitializerFactory.getSharedFactory())
        .prepare(matrixSeed);
    handler2.execute(SquareMatrix::createInverseMatrix);
    SquareMatrix<BigFraction> matrixResult3 = handler2.finalizer();
  
    HandlerSquareMatrix<BigFraction, Fraction> handler3 = new HandlerSquareMatrix<>();
    handler3
        .setOperator(OperationFactory.getSharedFactory())
        .setInitializer(ElementInitializerFactory.getSharedFactory())
        .prepare(matrixSeed);
    handler3.execute(SquareMatrix::createInverseMatrix);
    SquareMatrix<BigFraction> matrixResult4 = handler3.finalizer();
    
    Log.i("matrixResult1:" + Arrays.deepToString(matrixResult1.getTable()));
    Log.i("matrixResult2:" + Arrays.deepToString(matrixResult2.getTable()));
    Log.i("matrixResult3:" + Arrays.deepToString(matrixResult3.getTable()));
    Log.i("matrixResult4:" + Arrays.deepToString(matrixResult4.getTable()));
  
    Assert.assertArrayEquals(matrixResult1.getTable(), matrixResult2.getTable());
    Assert.assertArrayEquals(matrixResult1.getTable(), matrixResult4.getTable());
  }
  
  @Test
  public void testCase_01_02() throws Exception {
    SquareMatrix<BigDecimal> matrixResult1;
    SquareMatrix<BigDecimal> matrixSeed;
    SquareMatrix<BigDecimal> matrixSeed1 = new BuilderSquareMatrix<BigDecimal>()
        .operator(OperationFactory.getSharedFactory())
        .initializer(ElementInitializerFactory.getSharedFactory())
        .cookbook(() -> new BigDecimal[][]{
            {new BigDecimal("5.1"), new BigDecimal("2.5"), new BigDecimal("1.2")},
            {new BigDecimal("1.1"), new BigDecimal("1.5"), new BigDecimal("1.2")},
            {new BigDecimal("2.3"), new BigDecimal("2.5"), new BigDecimal("6.2")}
        })
        .build();
    
    matrixSeed = matrixSeed1;
    
    matrixResult1 = matrixSeed.createInverseMatrix();
    
    HandlerSquareMatrix<BigDecimal, BigFraction> handler1 = new HandlerSquareMatrix<>();
    handler1
        .setOperator(OperationFactory.getSharedFactory())
        .setInitializer(ElementInitializerFactory.getSharedFactory())
        .prepare(matrixSeed);
    handler1.execute(SquareMatrix::createInverseMatrix);
    SquareMatrix<BigDecimal> matrixResult2 = handler1.finalizer();
    
    HandlerSquareMatrix<BigDecimal, Double> handler2 = new HandlerSquareMatrix<>();
    handler2
        .setOperator(OperationFactory.getSharedFactory())
        .setInitializer(ElementInitializerFactory.getSharedFactory())
        .prepare(matrixSeed);
    handler2.execute(SquareMatrix::createInverseMatrix);
    SquareMatrix<BigDecimal> matrixResult3 = handler2.finalizer();
    
    HandlerSquareMatrix<BigDecimal, Fraction> handler3 = new HandlerSquareMatrix<>();
    handler3
        .setOperator(OperationFactory.getSharedFactory())
        .setInitializer(ElementInitializerFactory.getSharedFactory())
        .prepare(matrixSeed);
    handler3.execute(SquareMatrix::createInverseMatrix);
    SquareMatrix<BigDecimal> matrixResult4 = handler3.finalizer();
  
    HandlerSquareMatrix<BigDecimal, BigDecimal> handler4 = new HandlerSquareMatrix<>();
    handler4
        .setOperator(OperationFactory.getSharedFactory())
        .setInitializer(ElementInitializerFactory.getSharedFactory())
        .prepare(matrixSeed);
    handler4.execute(SquareMatrix::createInverseMatrix);
    SquareMatrix<BigDecimal> matrixResult5 = handler4.finalizer();
    
    Log.i("matrixResult1:" + Arrays.deepToString(matrixResult1.getTable()));
    Log.i("matrixResult2:" + Arrays.deepToString(matrixResult2.getTable()));
    Log.i("matrixResult3:" + Arrays.deepToString(matrixResult3.getTable()));
    Log.i("matrixResult4:" + Arrays.deepToString(matrixResult4.getTable()));
    Log.i("matrixResult5:" + Arrays.deepToString(matrixResult5.getTable()));
    
    Assert.assertArrayEquals(matrixResult1.getTable(), matrixResult5.getTable());
    Assert.assertArrayEquals(matrixResult2.getTable(), matrixResult4.getTable());
  }
}