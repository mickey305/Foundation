package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.AbstractNumberTable;
import com.mickey305.foundation.v4.lang.math.Matrix;
import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;

import java.util.Map;

public class BuilderMatrix<T extends Number> extends AbstractMatrixBuilder<Matrix<T>, T> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected Matrix<T> createMatrix(CookBook<T> cookBook,
                                   IElementInitializer<T> ini,
                                   Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> ope,
                                   Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> ROpe) {
    return new Matrix<>(
        cookBook.tableDef(),
        ini,
        ope,
        ROpe);
  }
}
