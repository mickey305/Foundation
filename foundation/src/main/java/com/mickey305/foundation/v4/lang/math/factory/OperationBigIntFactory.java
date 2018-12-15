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
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> add() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.add(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> sub() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.subtract(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> multi() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.multiply(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> div() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.divide(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> max() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.max(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigInteger, BigInteger> min() {
    return new AbstractNumberOperation<BigInteger, BigInteger>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigInteger operationDefault(BigInteger l, BigInteger r) {
        return l.min(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigInteger, Boolean> eq() {
    return new AbstractNumberOperation<BigInteger, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigInteger l, BigInteger r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigInteger, Boolean> lt() {
    return new AbstractNumberOperation<BigInteger, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigInteger l, BigInteger r) {
        return l.compareTo(r) < 0;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigInteger, Boolean> gt() {
    return new AbstractNumberOperation<BigInteger, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigInteger l, BigInteger r) {
        return l.compareTo(r) > 0;
      }
    };
  }
}
