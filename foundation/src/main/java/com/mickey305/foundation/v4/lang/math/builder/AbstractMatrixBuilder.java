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

package com.mickey305.foundation.v4.lang.math.builder;

import com.mickey305.foundation.v4.lang.math.AbstractNumberTable;
import com.mickey305.foundation.v4.lang.math.Matrix;
import com.mickey305.foundation.v4.lang.math.context.IMatrixContext;
import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import com.mickey305.foundation.v4.lang.math.operator.IOperationFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMatrixBuilder<M extends Matrix<T>, T extends Number> {
  private CookBook<T> cookBook;
  private IElementInitializer<T> initializer;
  private final Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> Operators;
  private final Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> ROperators;
  
  public AbstractMatrixBuilder() {
    Operators = new HashMap<>();
    ROperators = new HashMap<>();
  }
  
  public AbstractMatrixBuilder<M, T> context(IMatrixContext<T> context) {
    return this
        .initializer(context.getElementInitializer())
        .operator(context.getOperationFactory());
  }
  
  public AbstractMatrixBuilder<M, T> initializer(IElementInitializer<T> initializer) {
    this.initializer = initializer;
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> operator(final IOperationFactory<T> operator) {
    return this
        .opeAdd(operator.add())
        .opeSub(operator.sub())
        .opeMlt(operator.multi())
        .opeDiv(operator.div())
        .opeMax(operator.max())
        .opeMin(operator.min())
        .opeEQ(operator.eq())
        .opeNE(operator.ne())
        .opeLT(operator.lt())
        .opeLE(operator.le())
        .opeGT(operator.gt())
        .opeGE(operator.ge());
  }
  
  public AbstractMatrixBuilder<M, T> opeAdd(final AbstractNumberOperation<T, T> operator) {
    Operators.put(AbstractNumberTable.Operator.Add, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeSub(final AbstractNumberOperation<T, T> operator) {
    Operators.put(AbstractNumberTable.Operator.Sub, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeMlt(final AbstractNumberOperation<T, T> operator) {
    Operators.put(AbstractNumberTable.Operator.Multi, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeDiv(final AbstractNumberOperation<T, T> operator) {
    Operators.put(AbstractNumberTable.Operator.Div, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeMax(final AbstractNumberOperation<T, T> operator) {
    Operators.put(AbstractNumberTable.Operator.Max, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeMin(final AbstractNumberOperation<T, T> operator) {
    Operators.put(AbstractNumberTable.Operator.Min, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeEQ(final AbstractNumberOperation<T, Boolean> operator) {
    ROperators.put(AbstractNumberTable.RelationalOperator.Eq, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeNE(final AbstractNumberOperation<T, Boolean> operator) {
    ROperators.put(AbstractNumberTable.RelationalOperator.NE, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeLT(final AbstractNumberOperation<T, Boolean> operator) {
    ROperators.put(AbstractNumberTable.RelationalOperator.LT, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeLE(final AbstractNumberOperation<T, Boolean> operator) {
    ROperators.put(AbstractNumberTable.RelationalOperator.LE, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeGT(final AbstractNumberOperation<T, Boolean> operator) {
    ROperators.put(AbstractNumberTable.RelationalOperator.GT, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> opeGE(final AbstractNumberOperation<T, Boolean> operator) {
    ROperators.put(AbstractNumberTable.RelationalOperator.GE, operator);
    return this;
  }
  
  public AbstractMatrixBuilder<M, T> cookbook(CookBook<T> cookBook) {
    this.cookBook = cookBook;
    return this;
  }
  
  public M build() {
    
    // create matrix
    final CookBook<T> template = cookBook;
    final M matrix = this.createMatrix(
        cookBook,
        initializer,
        Operators,
        ROperators
    );
    
    return matrix;
  }
  
  protected abstract M createMatrix(CookBook<T> cookBook,
                                    IElementInitializer<T> ini,
                                    Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> ope,
                                    Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> ROpe);
  
  public interface CookBook<U extends Number> {
    U[][] tableDef();
  }
}
