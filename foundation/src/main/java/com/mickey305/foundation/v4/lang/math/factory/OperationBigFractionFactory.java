package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import org.apache.commons.math3.fraction.BigFraction;

import java.util.Objects;

public class OperationBigFractionFactory extends AbstractOperationFactory<BigFraction> {
  private OperationBigFractionFactory() { }
  
  private static final class OperationBigFractionFactoryHolder {
    private static OperationBigFractionFactory Instance = new OperationBigFractionFactory();
  }
  
  public static OperationBigFractionFactory getInstance() {
    return OperationBigFractionFactoryHolder.Instance;
  }
  
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> add() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return l.add(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> sub() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return l.subtract(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> multi() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return l.multiply(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> div() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return l.divide(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> max() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return (l.compareTo(r) > 0)? l: r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigFraction, BigFraction> min() {
    return new AbstractNumberOperation<BigFraction, BigFraction>() {
      @Override
      protected BigFraction operationDefault(BigFraction l, BigFraction r) {
        return (l.compareTo(r) < 0)? l: r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigFraction, Boolean> eq() {
    return new AbstractNumberOperation<BigFraction, Boolean>() {
      @Override
      protected Boolean operationDefault(BigFraction l, BigFraction r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigFraction, Boolean> lt() {
    return new AbstractNumberOperation<BigFraction, Boolean>() {
      @Override
      protected Boolean operationDefault(BigFraction l, BigFraction r) {
        return l.compareTo(r) < 0;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigFraction, Boolean> gt() {
    return new AbstractNumberOperation<BigFraction, Boolean>() {
      @Override
      protected Boolean operationDefault(BigFraction l, BigFraction r) {
        return l.compareTo(r) > 0;
      }
    };
  }
}
