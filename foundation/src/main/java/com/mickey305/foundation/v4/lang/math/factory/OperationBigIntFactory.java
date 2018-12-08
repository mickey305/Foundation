package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

import java.math.BigInteger;
import java.util.Objects;

public class OperationBigIntFactory extends AbstractOperationFactory<BigInteger> {
  private OperationBigIntFactory() {
  }
  
  private static final class OperationBigIntFactoryHolder {
    private static OperationBigIntFactory Instance = new OperationBigIntFactory();
  }
  
  public static OperationBigIntFactory getInstance() {
    return OperationBigIntFactoryHolder.Instance;
  }
  
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> add() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.add(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> sub() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.subtract(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> multi() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.multiply(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> div() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.divide(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> max() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.max(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> min() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.min(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigInteger, Boolean> eq() {
    return new AbstractNumberOperation<BigInteger, Boolean>() {
      @Override
      protected Boolean operationDefault(BigInteger l, BigInteger r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigInteger, Boolean> lt() {
    return new AbstractNumberOperation<BigInteger, Boolean>() {
      @Override
      protected Boolean operationDefault(BigInteger l, BigInteger r) {
        return l.compareTo(r) < 0;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<BigInteger, Boolean> gt() {
    return new AbstractNumberOperation<BigInteger, Boolean>() {
      @Override
      protected Boolean operationDefault(BigInteger l, BigInteger r) {
        return l.compareTo(r) > 0;
      }
    };
  }
}
