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

public class ElementInitializerFloatFactory extends AbstractElementInitializer<Float> {
  public ElementInitializerFloatFactory() {
  }
  
  private static final class ElementInitializerFloatFactoryHolder {
    private static ElementInitializerFloatFactory Instance = new ElementInitializerFloatFactory();
  }
  
  public static ElementInitializerFloatFactory getInstance() {
    return ElementInitializerFloatFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Float zero() {
    return 0.0f;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Float one() {
    return 1.0f;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Float minusOne() {
    return -1.0f;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Float convertFrom(Number n) throws RuntimeException {
    // unsafe
    return n.floatValue();
  }
}
