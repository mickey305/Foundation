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

public abstract class AbstractOperationFactory<E extends Number> implements IOperationFactory<E> {
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<E, Boolean> ne() {
    return new AbstractNumberOperation<E, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(E l, E r) {
        return !eq().apply(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<E, Boolean> le() {
    return new AbstractNumberOperation<E, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(E l, E r) {
        return eq().apply(l, r) || lt().apply(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<E, Boolean> ge() {
    return new AbstractNumberOperation<E, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(E l, E r) {
        return eq().apply(l, r) || gt().apply(l, r);
      }
    };
  }
}
