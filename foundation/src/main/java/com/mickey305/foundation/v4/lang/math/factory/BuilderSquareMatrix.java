/*
 * Copyright (c) 2018. K.Misaki
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

package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.AbstractNumberTable;
import com.mickey305.foundation.v4.lang.math.SquareMatrix;
import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;

import java.util.Map;

public class BuilderSquareMatrix<T extends Number> extends AbstractMatrixBuilder<SquareMatrix<T>, T> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected SquareMatrix<T> createMatrix(CookBook<T> cookBook,
                                         IElementInitializer<T> ini,
                                         Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> ope,
                                         Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> ROpe) {
    return new SquareMatrix<>(
        cookBook.tableDef(),
        ini,
        ope,
        ROpe);
  }
}
