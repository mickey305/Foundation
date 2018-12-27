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

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

import java.util.Objects;

public class OperationLongFactory extends AbstractOperationFactory<Long> {
  private OperationLongFactory() {
  }
  
  private static final class OperationLongFactoryHolder {
    private static OperationLongFactory Instance = new OperationLongFactory();
  }
  
  public static OperationLongFactory getInstance() {
    return OperationLongFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Long, Long> add() {
    return new AbstractNumberOperation<Long, Long>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Long operationDefault(Long l, Long r) {
        return l + r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Long, Long> sub() {
    return new AbstractNumberOperation<Long, Long>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Long operationDefault(Long l, Long r) {
        return l - r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Long, Long> multi() {
    return new AbstractNumberOperation<Long, Long>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Long operationDefault(Long l, Long r) {
        return l * r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Long, Long> div() {
    return new AbstractNumberOperation<Long, Long>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Long operationDefault(Long l, Long r) {
        return l / r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Long, Long> max() {
    return new AbstractNumberOperation<Long, Long>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Long operationDefault(Long l, Long r) {
        return Math.max(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Long, Long> min() {
    return new AbstractNumberOperation<Long, Long>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Long operationDefault(Long l, Long r) {
        return Math.min(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Long, Boolean> eq() {
    return new AbstractNumberOperation<Long, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Long l, Long r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Long, Boolean> lt() {
    return new AbstractNumberOperation<Long, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Long l, Long r) {
        return l < r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Long, Boolean> gt() {
    return new AbstractNumberOperation<Long, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Long l, Long r) {
        return l > r;
      }
    };
  }
}
