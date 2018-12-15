package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

import java.util.Objects;

public class OperationFloatFactory extends AbstractOperationFactory<Float> {
  private OperationFloatFactory() {
  }
  
  private static final class OperationFloatFactoryHolder {
    private static OperationFloatFactory Instance = new OperationFloatFactory();
  }
  
  public static OperationFloatFactory getInstance() {
    return OperationFloatFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> add() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return l + r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> sub() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return l - r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> multi() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return l * r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> div() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return l / r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> max() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return Math.max(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Float> min() {
    return new AbstractNumberOperation<Float, Float>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Float operationDefault(Float l, Float r) {
        return Math.min(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Boolean> eq() {
    return new AbstractNumberOperation<Float, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Float l, Float r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Boolean> lt() {
    return new AbstractNumberOperation<Float, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Float l, Float r) {
        return l < r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Float, Boolean> gt() {
    return new AbstractNumberOperation<Float, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Float l, Float r) {
        return l > r;
      }
    };
  }
}
