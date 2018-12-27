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

package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;
import com.mickey305.foundation.v3.lang.math.operator.AbstractNumberOperation;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import java.math.BigInteger;

@Deprecated
public class NumberOperationMax extends AbstractNumberOperation<Number> {
  public NumberOperationMax() {
    super();
  }
  
  public NumberOperationMax(BinaryFunction<Number, Number, Number> extension) {
    super(extension);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Number operationDefault(Number l, Number r) {
    final Class<?> targetClazz = l.getClass();
    if (targetClazz.equals(Integer.class))
      return Math.max(l.intValue(), r.intValue());
    
    if (targetClazz.equals(Long.class))
      return Math.max(l.longValue(), r.longValue());
    
    if (targetClazz.equals(Float.class))
      return Math.max(l.floatValue(), r.floatValue());
    
    if (targetClazz.equals(Double.class))
      return Math.max(l.doubleValue(), r.doubleValue());
    
    if (targetClazz.equals(Short.class))
      return Math.max(l.shortValue(), r.shortValue());
    
    if (targetClazz.equals(Byte.class))
      return Math.max(l.byteValue(), r.byteValue());
    
    if (targetClazz.equals(BigInteger.class))
      return ((BigInteger) l).max(super.convertToBigInteger(r));
    
    if (targetClazz.equals(Fraction.class))
      return (((Fraction) l).compareTo(super.convertToFraction(r)) > 0)
          ? l
          : r;
    
    if (targetClazz.equals(BigFraction.class))
      return (((BigFraction) l).compareTo(super.convertToBigFraction(r)) > 0)
          ? l
          : r;
    
    return null;
  }
}
