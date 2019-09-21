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
import org.apache.commons.math3.fraction.BigFraction;

import java.util.Objects;

public class OperationBigFractionFactory extends AbstractOperationFactory<BigFraction> {
  public OperationBigFractionFactory() {
  }
  
  private static final class OperationBigFractionFactoryHolder {
    private static OperationBigFractionFactory Instance = new OperationBigFractionFactory();
  }
  
  public static OperationBigFractionFactory getInstance() {
    return OperationBigFractionFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> add() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return l.add(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> sub() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return l.subtract(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> multi() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return l.multiply(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> div() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return l.divide(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> max() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return (l.compareTo(r) > 0) ? l : r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> min() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return (l.compareTo(r) < 0) ? l : r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigFraction, Boolean> eq() {
    return new AbstractNumberOperation<BigFraction, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigFraction l, BigFraction r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigFraction, Boolean> lt() {
    return new AbstractNumberOperation<BigFraction, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigFraction l, BigFraction r) {
        return l.compareTo(r) < 0;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigFraction, Boolean> gt() {
    return new AbstractNumberOperation<BigFraction, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigFraction l, BigFraction r) {
        return l.compareTo(r) > 0;
      }
    };
  }
}
