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

package com.mickey305.foundation.v4.lang.math.context;

import com.mickey305.foundation.v4.lang.math.factory.ElementInitializerFactory;
import com.mickey305.foundation.v4.lang.math.factory.OperationFactory;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import com.mickey305.foundation.v4.lang.math.operator.IOperationFactory;
import org.apache.commons.math3.fraction.BigFraction;

public class MatrixContextBigFraction extends AbstractMatrixContext<BigFraction> {
  
  public MatrixContextBigFraction(MatrixContextType type) {
    super(type);
  }
  
  public MatrixContextBigFraction() {
    super();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected IOperationFactory<BigFraction> createOperationFactory(MatrixContextType type) {
    if (type == MatrixContextType.Default) return OperationFactory.<BigFraction>getFactory();
    if (type == MatrixContextType.Shared)  return OperationFactory.<BigFraction>getSharedFactory();
  
    throw new UnsupportedOperationException("contextType-data analyze unreached.");
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected IElementInitializer<BigFraction> createElementInitializer(MatrixContextType type) {
    if (type == MatrixContextType.Default) return ElementInitializerFactory.<BigFraction>getFactory();
    if (type == MatrixContextType.Shared)  return ElementInitializerFactory.<BigFraction>getSharedFactory();
  
    throw new UnsupportedOperationException("contextType-data analyze unreached.");
  }
}
