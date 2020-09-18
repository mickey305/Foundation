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

package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.AbstractOperationFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class OperationBigDecimalFactory extends AbstractOperationFactory<BigDecimal> {
  private static final int          DEFAULT_SCALE = 3;
  private static final RoundingMode DEFAULT_ROUND = RoundingMode.DOWN;
  private int scale;
  private RoundingMode roundingMode;
  
  public OperationBigDecimalFactory scale(int scale) {
    this.scale = scale;
    return this;
  }
  
  public OperationBigDecimalFactory round(RoundingMode roundingMode) {
    this.roundingMode = roundingMode;
    return this;
  }
  
  public OperationBigDecimalFactory() {
    this.scale        = DEFAULT_SCALE;
    this.roundingMode = DEFAULT_ROUND;
  }
  
  private static final class OperationBigDecimalFactoryHolder {
    private static OperationBigDecimalFactory Instance = new OperationBigDecimalFactory();
  }
  
  public static OperationBigDecimalFactory getInstance() {
    return OperationBigDecimalFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> add() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.add(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> sub() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.subtract(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> multi() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.multiply(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> div() {
    final int scale = this.scale;
    final RoundingMode roundingMode = this.roundingMode;
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.divide(r, scale, roundingMode);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> max() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.max(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> min() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.min(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, Boolean> eq() {
    return new AbstractNumberOperation<BigDecimal, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigDecimal l, BigDecimal r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, Boolean> lt() {
    return new AbstractNumberOperation<BigDecimal, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigDecimal l, BigDecimal r) {
        return l.compareTo(r) < 0;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, Boolean> gt() {
    return new AbstractNumberOperation<BigDecimal, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigDecimal l, BigDecimal r) {
        return l.compareTo(r) > 0;
      }
    };
  }
}
