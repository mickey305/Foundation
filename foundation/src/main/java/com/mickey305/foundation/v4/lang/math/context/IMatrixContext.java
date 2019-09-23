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

package com.mickey305.foundation.v4.lang.math.context;

import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import com.mickey305.foundation.v4.lang.math.operator.IOperationFactory;

/**
 * 行列の内部コンテキスト情報にアクセスするためのインターフェースです。
 * @param <T> 行列の要素の総称型です。
 */
public interface IMatrixContext<T extends Number> {
  /**
   * コンテキストオブジェクトの種類を返却します。
   * @return コンテキストの種類
   */
  MatrixContextType getMatrixContextType();
  
  /**
   * オペレーションファクトリーオブジェクトを返却します。
   * @return オペレーションファクトリーオブジェクト
   */
  IOperationFactory<T> getOperationFactory();
  
  /**
   * 要素初期化ファクトリーオブジェクトを返却します。
   * @return 要素初期化ファクトリーオブジェクト
   */
  IElementInitializer<T> getElementInitializer();
  
}
