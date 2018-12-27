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

public class OperationByteFactory extends AbstractOperationFactory<Byte> {
  private OperationByteFactory() {
  }
  
  private static final class OperationByteFactoryHolder {
    private static OperationByteFactory Instance = new OperationByteFactory();
  }
  
  public static OperationByteFactory getInstance() {
    return OperationByteFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> add() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) (l + r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> sub() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) (l - r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> multi() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) (l * r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> div() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) (l / r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> max() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) Math.max(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> min() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) Math.min(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Boolean> eq() {
    return new AbstractNumberOperation<Byte, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Byte l, Byte r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Boolean> lt() {
    return new AbstractNumberOperation<Byte, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Byte l, Byte r) {
        return l < r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Boolean> gt() {
    return new AbstractNumberOperation<Byte, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Byte l, Byte r) {
        return l > r;
      }
    };
  }
}
