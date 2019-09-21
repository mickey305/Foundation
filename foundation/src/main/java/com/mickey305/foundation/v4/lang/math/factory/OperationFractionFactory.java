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
import org.apache.commons.math3.fraction.Fraction;

import java.util.Objects;

public class OperationFractionFactory extends AbstractOperationFactory<Fraction> {
  public OperationFractionFactory() {
  }
  
  private static final class OperationFractionFactoryHolder {
    private static OperationFractionFactory Instance = new OperationFractionFactory();
  }
  
  public static OperationFractionFactory getInstance() {
    return OperationFractionFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Fraction, Fraction> add() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return l.add(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Fraction, Fraction> sub() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return l.subtract(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Fraction, Fraction> multi() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return l.multiply(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Fraction, Fraction> div() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return l.divide(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Fraction, Fraction> max() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return (l.compareTo(r) > 0) ? l : r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Fraction, Fraction> min() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return (l.compareTo(r) < 0) ? l : r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Fraction, Boolean> eq() {
    return new AbstractNumberOperation<Fraction, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Fraction l, Fraction r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Fraction, Boolean> lt() {
    return new AbstractNumberOperation<Fraction, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Fraction l, Fraction r) {
        return l.compareTo(r) < 0;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Fraction, Boolean> gt() {
    return new AbstractNumberOperation<Fraction, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Fraction l, Fraction r) {
        return l.compareTo(r) > 0;
      }
    };
  }
}
