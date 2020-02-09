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

import com.mickey305.foundation.v4.lang.math.operator.IOperationFactory;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;

public abstract class AbstractMatrixContext<T extends Number> implements IMatrixContext<T> {
  private final MatrixContextType matrixContextType;
  private final IOperationFactory<T> operationFactory;
  private final IElementInitializer<T> elementInitializer;
  
  public AbstractMatrixContext(MatrixContextType type) {
    this.matrixContextType = type;
    this.operationFactory = this.createOperationFactory(type);
    this.elementInitializer = this.createElementInitializer(type);
  }
  
  protected AbstractMatrixContext() {
    this(MatrixContextType.Default);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationFactory<T> getOperationFactory() {
    return operationFactory;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public IElementInitializer<T> getElementInitializer() {
    return elementInitializer;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public MatrixContextType getMatrixContextType() {
    return matrixContextType;
  }
  
  /**
   * 引数で指定された{@link MatrixContextType}のオペレーションファクトリーオブジェクトを生成／取得し、返却します。
   * @param type コンテキストの種類
   * @return オペレーションファクトリーオブジェクト
   */
  protected abstract IOperationFactory<T> createOperationFactory(MatrixContextType type);
  
  /**
   * 引数で指定された{@link MatrixContextType}の要素初期化ファクトリーオブジェクトを生成／取得し、返却します。
   * @param type コンテキストの種類
   * @return 要素初期化ファクトリーオブジェクト
   */
  protected abstract IElementInitializer<T> createElementInitializer(MatrixContextType type);
}
