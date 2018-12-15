package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

import java.math.BigDecimal;
import java.util.Objects;

public class OperationBigDecimalFactory extends AbstractOperationFactory<BigDecimal> {
  private static final int DEFAULT_SCALE = 3;
  private int scale;
  
  public OperationBigDecimalFactory scale(int scale) {
    this.scale = scale;
    return this;
  }
  
  private OperationBigDecimalFactory() {
    this.scale = DEFAULT_SCALE;
  }
  
  private static final class OperationBigDecimalFactoryHolder {
    private static OperationBigDecimalFactory Instance = new OperationBigDecimalFactory();
  }
  
  public static OperationBigDecimalFactory getInstance() {
    return OperationBigDecimalFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> add() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.add(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> sub() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.subtract(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> multi() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.multiply(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> div() {
    final int scale = this.scale;
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.divide(r, scale, BigDecimal.ROUND_DOWN);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> max() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.max(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, BigDecimal> min() {
    return new AbstractNumberOperation<BigDecimal, BigDecimal>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected BigDecimal operationDefault(BigDecimal l, BigDecimal r) {
        return l.min(r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, Boolean> eq() {
    return new AbstractNumberOperation<BigDecimal, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigDecimal l, BigDecimal r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, Boolean> lt() {
    return new AbstractNumberOperation<BigDecimal, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigDecimal l, BigDecimal r) {
        return l.compareTo(r) < 0;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<BigDecimal, Boolean> gt() {
    return new AbstractNumberOperation<BigDecimal, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(BigDecimal l, BigDecimal r) {
        return l.compareTo(r) > 0;
      }
    };
  }
}
