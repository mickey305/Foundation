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

import com.mickey305.foundation.v4.lang.math.operator.AbstractElementInitializer;

public class ElementInitializerShortFactory extends AbstractElementInitializer<Short> {
  public ElementInitializerShortFactory() {
  }
  
  private static final class ElementInitializerShortFactoryHolder {
    private static ElementInitializerShortFactory Instance = new ElementInitializerShortFactory();
  }
  
  public static ElementInitializerShortFactory getInstance() {
    return ElementInitializerShortFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Short zero() {
    return (short) 0;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Short one() {
    return (short) 1;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Short minusOne() {
    return (short) -1;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Short convertFrom(Number n) throws RuntimeException {
    // unsafe
    return n.shortValue();
  }
}
