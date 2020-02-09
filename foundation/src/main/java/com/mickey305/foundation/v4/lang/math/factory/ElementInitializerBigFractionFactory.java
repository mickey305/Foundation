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

public class ElementInitializerBigFractionFactory extends AbstractElementInitializer<BigFraction> {
  public ElementInitializerBigFractionFactory() {
  }
  
  private static final class ElementInitializerBigFractionFactoryHolder {
    private static ElementInitializerBigFractionFactory Instance = new ElementInitializerBigFractionFactory();
  }
  
  public static ElementInitializerBigFractionFactory getInstance() {
    return ElementInitializerBigFractionFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigFraction zero() {
    return BigFraction.ZERO;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigFraction one() {
    return BigFraction.ONE;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigFraction minusOne() {
    return BigFraction.MINUS_ONE;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public BigFraction convertFrom(Number n) throws RuntimeException {
    if (n instanceof BigFraction) return (BigFraction) n;
    if (n instanceof Integer)     return new BigFraction(n.intValue());
    if (n instanceof Long)        return new BigFraction(n.longValue());
    if (n instanceof Short)       return new BigFraction(n.shortValue());
    if (n instanceof Byte)        return new BigFraction(n.byteValue());
    if (n instanceof Float)       return new BigFraction(n.floatValue());
    if (n instanceof Double)      return new BigFraction(n.doubleValue());
    if (n instanceof BigInteger)  return new BigFraction((BigInteger) n);
    
    if (n instanceof Fraction) {
      final Fraction fraction = (Fraction) n;
      return new BigFraction(fraction.getNumerator(), fraction.getDenominator());
    }
    
    if (n instanceof BigDecimal) {
      final BigDecimal decimal = (BigDecimal) n;
      final BigInteger ten = BigInteger.TEN;
      if (decimal.scale() >= 0) {
        return new BigFraction(decimal.unscaledValue(), ten.pow(decimal.scale()));
      } else {
        return new BigFraction(decimal.unscaledValue().multiply(ten.pow(-1 * decimal.scale())));
      }
    }
    
    // throwing exception
    throw new UnsupportedOperationException();
  }
}
