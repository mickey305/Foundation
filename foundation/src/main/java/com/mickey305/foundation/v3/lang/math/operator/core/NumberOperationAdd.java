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

package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;
import com.mickey305.foundation.v3.lang.math.operator.AbstractNumberOperation;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import java.math.BigInteger;

@Deprecated
public class NumberOperationAdd extends AbstractNumberOperation<Number> {
  public NumberOperationAdd() {
    super();
  }
  
  public NumberOperationAdd(BinaryFunction<Number, Number, Number> extension) {
    super(extension);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Number operationDefault(Number l, Number r) {
    final Class<?> targetClazz = l.getClass();
    if (targetClazz.equals(Integer.class))
      return l.intValue() + r.intValue();
    
    if (targetClazz.equals(Long.class))
      return l.longValue() + r.longValue();
    
    if (targetClazz.equals(Float.class))
      return l.floatValue() + r.floatValue();
    
    if (targetClazz.equals(Double.class))
      return l.doubleValue() + r.doubleValue();
    
    if (targetClazz.equals(Short.class))
      return l.shortValue() + r.shortValue();
    
    if (targetClazz.equals(Byte.class))
      return l.byteValue() + r.byteValue();
    
    if (targetClazz.equals(BigInteger.class))
      return ((BigInteger) l).add(super.convertToBigInteger(r));
    
    if (targetClazz.equals(Fraction.class))
      return ((Fraction) l).add(super.convertToFraction(r));
    
    if (targetClazz.equals(BigFraction.class))
      return ((BigFraction) l).add(super.convertToBigFraction(r));
    
    return null;
  }
}
