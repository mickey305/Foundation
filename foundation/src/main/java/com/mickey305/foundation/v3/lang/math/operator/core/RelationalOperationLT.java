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
public class RelationalOperationLT extends AbstractNumberOperation<Boolean> {
  public RelationalOperationLT() {
    super();
  }
  
  public RelationalOperationLT(BinaryFunction<Number, Number, Boolean> extension) {
    super(extension);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected Boolean operationDefault(Number l, Number r) {
    final Class<?> targetClazz = l.getClass();
    if (targetClazz.equals(Integer.class))
      return l.intValue() < r.intValue();
    
    if (targetClazz.equals(Long.class))
      return l.longValue() < r.longValue();
    
    if (targetClazz.equals(Float.class))
      return l.floatValue() < r.floatValue();
    
    if (targetClazz.equals(Double.class))
      return l.doubleValue() < r.doubleValue();
    
    if (targetClazz.equals(Short.class))
      return l.shortValue() < r.shortValue();
    
    if (targetClazz.equals(Byte.class))
      return l.byteValue() < r.byteValue();
    
    if (targetClazz.equals(BigInteger.class))
      return ((BigInteger) l).compareTo(super.convertToBigInteger(r)) < 0;
    
    if (targetClazz.equals(Fraction.class))
      return ((Fraction) l).compareTo(super.convertToFraction(r)) < 0;
    
    if (targetClazz.equals(BigFraction.class))
      return ((BigFraction) l).compareTo(super.convertToBigFraction(r)) < 0;
    
    return null;
  }
}
