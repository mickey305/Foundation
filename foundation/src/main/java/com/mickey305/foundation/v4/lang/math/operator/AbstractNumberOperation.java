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

package com.mickey305.foundation.v4.lang.math.operator;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

public abstract class AbstractNumberOperation<E extends Number, R> implements BinaryFunction<E, E, R> {
  public AbstractNumberOperation() {
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public R apply(E l, E r) {
    final R result = this.operationDefault(l, r);
    
    if (result != null) return result;
    
    // throwing exception
    throw new UnsupportedOperationException();
  }
  
  /**
   * デフォルト演算・実装メソッド
   *
   * @param l 演算項１
   * @param r 演算項２
   * @return 演算結果
   */
  protected abstract R operationDefault(E l, E r);
}
