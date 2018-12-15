package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

import java.util.Objects;

public class OperationIntFactory extends AbstractOperationFactory<Integer> {
  private OperationIntFactory() {
  }
  
  private static final class OperationIntFactoryHolder {
    private static OperationIntFactory Instance = new OperationIntFactory();
  }
  
  public static OperationIntFactory getInstance() {
    return OperationIntFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Integer, Integer> add() {
    return new AbstractNumberOperation<Integer, Integer>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Integer operationDefault(Integer l, Integer r) {
        return l + r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Integer, Integer> sub() {
    return new AbstractNumberOperation<Integer, Integer>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Integer operationDefault(Integer l, Integer r) {
        return l - r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Integer, Integer> multi() {
    return new AbstractNumberOperation<Integer, Integer>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Integer operationDefault(Integer l, Integer r) {
        return l * r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Integer, Integer> div() {
    return new AbstractNumberOperation<Integer, Integer>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Integer operationDefault(Integer l, Integer r) {
        return l / r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Integer, Integer> max() {
    return new AbstractNumberOperation<Integer, Integer>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Integer operationDefault(Integer l, Integer r) {
        return Math.max(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Integer, Integer> min() {
    return new AbstractNumberOperation<Integer, Integer>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Integer operationDefault(Integer l, Integer r) {
        return Math.min(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Integer, Boolean> eq() {
    return new AbstractNumberOperation<Integer, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Integer l, Integer r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Integer, Boolean> lt() {
    return new AbstractNumberOperation<Integer, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Integer l, Integer r) {
        return l < r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Integer, Boolean> gt() {
    return new AbstractNumberOperation<Integer, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Integer l, Integer r) {
        return l > r;
      }
    };
  }
}
