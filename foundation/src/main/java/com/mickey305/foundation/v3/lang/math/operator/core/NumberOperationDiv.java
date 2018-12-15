package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;
import com.mickey305.foundation.v3.lang.math.operator.AbstractNumberOperation;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import java.math.BigInteger;

@Deprecated
public class NumberOperationDiv extends AbstractNumberOperation<Number> {
  public NumberOperationDiv() {
    super();
  }
  
  public NumberOperationDiv(BinaryFunction<Number, Number, Number> extension) {
    super(extension);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Number operationDefault(Number l, Number r) {
    final Class<?> targetClazz = l.getClass();
    if (targetClazz.equals(Integer.class))
      return l.intValue() / r.intValue();
    
    if (targetClazz.equals(Long.class))
      return l.longValue() / r.longValue();
    
    if (targetClazz.equals(Float.class))
      return l.floatValue() / r.floatValue();
    
    if (targetClazz.equals(Double.class))
      return l.doubleValue() / r.doubleValue();
    
    if (targetClazz.equals(Short.class))
      return l.shortValue() / r.shortValue();
    
    if (targetClazz.equals(Byte.class))
      return l.byteValue() / r.byteValue();
    
    if (targetClazz.equals(BigInteger.class))
      return ((BigInteger) l).divide(super.convertToBigInteger(r));
    
    if (targetClazz.equals(Fraction.class))
      return ((Fraction) l).divide(super.convertToFraction(r));
    
    if (targetClazz.equals(BigFraction.class))
      return ((BigFraction) l).divide(super.convertToBigFraction(r));
    
    return null;
  }
}
