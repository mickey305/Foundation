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
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ElementInitializerBigDecimalFactory extends AbstractElementInitializer<BigDecimal> {
  public ElementInitializerBigDecimalFactory() {
  }
  
  private static final class ElementInitializerBigDecimalFactoryHolder {
    private static ElementInitializerBigDecimalFactory Instance = new ElementInitializerBigDecimalFactory();
  }
  
  public static ElementInitializerBigDecimalFactory getInstance() {
    return ElementInitializerBigDecimalFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal zero() {
    return BigDecimal.ZERO;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal one() {
    return BigDecimal.ONE;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal minusOne() {
    return new BigDecimal("-1.0");
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal convertFrom(Number n) throws RuntimeException {
    if (n instanceof BigDecimal) return (BigDecimal) n;
    if (n instanceof Integer)    return new BigDecimal(String.valueOf(n.intValue()));
    if (n instanceof Long)       return new BigDecimal(String.valueOf(n.longValue()));
    if (n instanceof Short)      return new BigDecimal(String.valueOf(n.shortValue()));
    if (n instanceof Byte)       return new BigDecimal(String.valueOf(n.byteValue()));
    if (n instanceof Float)      return new BigDecimal(String.valueOf(n.floatValue()));
    if (n instanceof Double)     return new BigDecimal(String.valueOf(n.doubleValue()));
    if (n instanceof BigInteger) return new BigDecimal((BigInteger) n);
    
    // unsafe
    if (n instanceof Fraction)    return new BigDecimal(String.valueOf(n.doubleValue()));
    if (n instanceof BigFraction) return new BigDecimal(String.valueOf(n.doubleValue()));
    
    // throwing exception
    throw new UnsupportedOperationException("elementData-convert failed.");
  }
}
