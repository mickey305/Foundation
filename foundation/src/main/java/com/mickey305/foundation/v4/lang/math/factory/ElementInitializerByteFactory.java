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

import com.mickey305.foundation.v4.lang.math.operator.AbstractElementInitializer;

public class ElementInitializerByteFactory extends AbstractElementInitializer<Byte> {
  public ElementInitializerByteFactory() {
  }
  
  private static final class ElementInitializerByteFactoryHolder {
    private static ElementInitializerByteFactory Instance = new ElementInitializerByteFactory();
  }
  
  public static ElementInitializerByteFactory getInstance() {
    return ElementInitializerByteFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Byte zero() {
    return (byte) 0;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Byte one() {
    return (byte) 1;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Byte minusOne() {
    return (byte) -1;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Byte convertFrom(Number n) throws RuntimeException {
    // unsafe
    return n.byteValue();
  }
}
