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

public class ElementInitializerLongFactory extends AbstractElementInitializer<Long> {
  public ElementInitializerLongFactory() {
  }
  
  private static final class ElementInitializerLongFactoryHolder {
    private static ElementInitializerLongFactory Instance = new ElementInitializerLongFactory();
  }
  
  public static ElementInitializerLongFactory getInstance() {
    return ElementInitializerLongFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Long zero() {
    return 0L;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Long one() {
    return 1L;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Long minusOne() {
    return -1L;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Long convertFrom(Number n) throws RuntimeException {
    // unsafe
    return n.longValue();
  }
}