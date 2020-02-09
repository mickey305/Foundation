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

package com.mickey305.foundation.v4.lang.math.handler;

import com.mickey305.foundation.v4.lang.math.Matrix;
import com.mickey305.foundation.v4.lang.math.builder.AbstractMatrixBuilder;
import com.mickey305.foundation.v4.lang.math.builder.BuilderMatrix;
import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import com.mickey305.foundation.v4.lang.math.operator.IOperationFactory;

import java.util.Map;

/**
 * 行列の操作用クラス
 * @param <T> 演算対象行列{@link Matrix<T>}の要素の総称型
 * @param <U> 演算結果行列{@link Matrix<U>}の要素の総称型
 */
public class HandlerMatrix<T extends Number, U extends Number>
    extends AbstractMatrixHandler<Matrix<T>, T, Matrix<U>, U> {
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected Matrix<U> createTemporaryMatrix(final Matrix<T> orgMatrix, IOperationFactory<U> op, final IElementInitializer<U> ini) {
    
    Matrix<U> tmpMatrix = new BuilderMatrix<U>()
        .initializer(ini).operator(op).cookbook(new AbstractMatrixBuilder.CookBook<U>() {
          /**
           * {@inheritDoc}
           */
          @Override
          public U[][] tableDef() {
            U[][] table = ini.table(orgMatrix.getRowSize(), orgMatrix.getColumnSize());
            for (int i = 0; i < orgMatrix.getRowSize(); i++)
              for (int j = 0; j < orgMatrix.getColumnSize(); j++)
                table[i][j] = ini.convertFrom(orgMatrix.getCell(i, j));
            return table;
          }
        }).build();
    
    return tmpMatrix;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected Matrix<T> createResultMatrix(Matrix<T> orgMatrix, Matrix<U> resultMatrix) {
    
    final TmpMatrix<T> tmp = new TmpMatrix<>(orgMatrix);
    
    final Matrix<T> result = new Matrix<>(
        resultMatrix.getRowSize(), resultMatrix.getColumnSize(), tmp.ini(), tmp.op(), tmp.rop());
    
    for (int i = 0; i < result.getRowSize(); i++)
      for (int j = 0; j < result.getColumnSize(); j++)
        result.putCell(i, j, tmp.ini().convertFrom(resultMatrix.getCell(i, j)));
      
    return result;
  }
  
  // protected method backport accessible class
  private static class TmpMatrix<T extends Number> extends Matrix<T> {
    private static final long serialVersionUID = 7038945071320833750L;
  
    TmpMatrix(Matrix<T> matrix) {
      super(matrix);
    }
    
    IElementInitializer<T> ini() {
      return super.getInitializer();
    }
  
    Map<Operator, AbstractNumberOperation<T, T>> op() {
      return super.getOp();
    }
  
    Map<RelationalOperator, AbstractNumberOperation<T, Boolean>> rop() {
      return super.getRop();
    }
  }
}
