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

import java.util.Objects;

public class OperationFloatFactory extends AbstractOperationFactory<Float> {
  public OperationFloatFactory() {
  }
  
  private static final class OperationFloatFactoryHolder {
    private static OperationFloatFactory Instance = new OperationFloatFactory();
  }
  
  public static OperationFloatFactory getInstance() {
    return OperationFloatFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> add() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return l + r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> sub() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return l - r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> multi() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return l * r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> div() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return l / r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> max() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return Math.max(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> min() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return Math.min(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Boolean> eq() {
    return new AbstractNumberOperation<Float, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Float l, Float r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Boolean> lt() {
    return new AbstractNumberOperation<Float, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Float l, Float r) {
        return l < r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Boolean> gt() {
    return new AbstractNumberOperation<Float, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Float l, Float r) {
        return l > r;
      }
    };
  }
}
