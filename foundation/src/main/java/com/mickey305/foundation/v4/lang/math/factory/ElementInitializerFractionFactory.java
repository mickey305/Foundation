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

public class ElementInitializerFractionFactory extends AbstractElementInitializer<Fraction> {
  public ElementInitializerFractionFactory() {
  }
  
  private static final class ElementInitializerFractionFactoryHolder {
    private static ElementInitializerFractionFactory Instance = new ElementInitializerFractionFactory();
  }
  
  public static ElementInitializerFractionFactory getInstance() {
    return ElementInitializerFractionFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Fraction zero() {
    return Fraction.ZERO;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Fraction one() {
    return Fraction.ONE;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Fraction minusOne() {
    return Fraction.MINUS_ONE;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Fraction convertFrom(Number n) throws RuntimeException {
    if (n instanceof Fraction) return (Fraction) n;
    if (n instanceof Integer)  return new Fraction(n.intValue());
    if (n instanceof Long)     return new Fraction(n.longValue());
    if (n instanceof Short)    return new Fraction(n.shortValue());
    if (n instanceof Byte)     return new Fraction(n.byteValue());
    if (n instanceof Float)    return new Fraction(n.floatValue());
    if (n instanceof Double)   return new Fraction(n.doubleValue());
    
    // unsafe
    if (n instanceof BigFraction) {
      return Fraction.getReducedFraction(
          ((BigFraction) n).getNumeratorAsInt(),
          ((BigFraction) n).getDenominatorAsInt());
    }
    if (n instanceof BigInteger) return new Fraction(n.longValue());
    if (n instanceof BigDecimal) return new Fraction(n.doubleValue());
    
    // throwing exception
    throw new UnsupportedOperationException("elementData-convert failed.");
  }
}
