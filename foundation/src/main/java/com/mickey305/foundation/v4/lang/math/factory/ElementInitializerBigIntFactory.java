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

public class ElementInitializerBigIntFactory extends AbstractElementInitializer<BigInteger> {
  public ElementInitializerBigIntFactory() {
  }
  
  private static final class ElementInitializerBigIntFactoryHolder {
    private static ElementInitializerBigIntFactory Instance = new ElementInitializerBigIntFactory();
  }
  
  public static ElementInitializerBigIntFactory getInstance() {
    return ElementInitializerBigIntFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigInteger zero() {
    return BigInteger.ZERO;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigInteger one() {
    return BigInteger.ONE;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigInteger minusOne() {
    return BigInteger.valueOf(-1L);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigInteger convertFrom(Number n) throws RuntimeException {
    if (n instanceof BigInteger) return (BigInteger) n;
    if (n instanceof Integer)    return new BigInteger(String.valueOf(n.intValue()));
    if (n instanceof Long)       return new BigInteger(String.valueOf(n.longValue()));
    if (n instanceof Short)      return new BigInteger(String.valueOf(n.shortValue()));
    if (n instanceof Byte)       return new BigInteger(String.valueOf(n.byteValue()));
    if (n instanceof Float)      return new BigInteger(String.valueOf(n.floatValue()));
    if (n instanceof Double)     return new BigInteger(String.valueOf(n.doubleValue()));
    
    // unsafe
    if (n instanceof BigDecimal)  return ((BigDecimal) n).toBigInteger();
    if (n instanceof Fraction)    return new BigInteger(String.valueOf(n.doubleValue()));
    if (n instanceof BigFraction) return new BigInteger(String.valueOf(n.doubleValue()));
    
    // throwing exception
    throw new UnsupportedOperationException("elementData-convert failed.");
  }
}
