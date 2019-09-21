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

package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.AbstractOperationFactory;

import java.util.Objects;

public class OperationDoubleFactory extends AbstractOperationFactory<Double> {
  public OperationDoubleFactory() {
  }
  
  private static final class OperationDoubleFactoryHolder {
    private static OperationDoubleFactory Instance = new OperationDoubleFactory();
  }
  
  public static OperationDoubleFactory getInstance() {
    return OperationDoubleFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Double, Double> add() {
    return new AbstractNumberOperation<Double, Double>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Double operationDefault(Double l, Double r) {
        return l + r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Double, Double> sub() {
    return new AbstractNumberOperation<Double, Double>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Double operationDefault(Double l, Double r) {
        return l - r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Double, Double> multi() {
    return new AbstractNumberOperation<Double, Double>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Double operationDefault(Double l, Double r) {
        return l * r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Double, Double> div() {
    return new AbstractNumberOperation<Double, Double>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Double operationDefault(Double l, Double r) {
        return l / r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Double, Double> max() {
    return new AbstractNumberOperation<Double, Double>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Double operationDefault(Double l, Double r) {
        return Math.max(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Double, Double> min() {
    return new AbstractNumberOperation<Double, Double>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Double operationDefault(Double l, Double r) {
        return Math.min(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Double, Boolean> eq() {
    return new AbstractNumberOperation<Double, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Double l, Double r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Double, Boolean> lt() {
    return new AbstractNumberOperation<Double, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Double l, Double r) {
        return l < r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Double, Boolean> gt() {
    return new AbstractNumberOperation<Double, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Double l, Double r) {
        return l > r;
      }
    };
  }
}
