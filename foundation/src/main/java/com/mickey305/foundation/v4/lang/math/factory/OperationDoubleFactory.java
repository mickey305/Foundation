package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

import java.util.Objects;

public class OperationDoubleFactory extends AbstractOperationFactory<Double> {
  private OperationDoubleFactory() { }
  
  private static final class OperationDoubleFactoryHolder {
    private static OperationDoubleFactory Instance = new OperationDoubleFactory();
  }
  
  public static OperationDoubleFactory getInstance() {
    return OperationDoubleFactoryHolder.Instance;
  }
  
  @Override
  public AbstractNumberOperation<Double, Double> add() {
    return new AbstractNumberOperation<Double, Double>() {
      @Override
      protected Double operationDefault(Double l, Double r) {
        return l + r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Double, Double> sub() {
    return new AbstractNumberOperation<Double, Double>() {
      @Override
      protected Double operationDefault(Double l, Double r) {
        return l - r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Double, Double> multi() {
    return new AbstractNumberOperation<Double, Double>() {
      @Override
      protected Double operationDefault(Double l, Double r) {
        return l * r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Double, Double> div() {
    return new AbstractNumberOperation<Double, Double>() {
      @Override
      protected Double operationDefault(Double l, Double r) {
        return l / r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Double, Double> max() {
    return new AbstractNumberOperation<Double, Double>() {
      @Override
      protected Double operationDefault(Double l, Double r) {
        return Math.max(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Double, Double> min() {
    return new AbstractNumberOperation<Double, Double>() {
      @Override
      protected Double operationDefault(Double l, Double r) {
        return Math.min(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Double, Boolean> eq() {
    return new AbstractNumberOperation<Double, Boolean>() {
      @Override
      protected Boolean operationDefault(Double l, Double r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Double, Boolean> lt() {
    return new AbstractNumberOperation<Double, Boolean>() {
      @Override
      protected Boolean operationDefault(Double l, Double r) {
        return l < r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Double, Boolean> gt() {
    return new AbstractNumberOperation<Double, Boolean>() {
      @Override
      protected Boolean operationDefault(Double l, Double r) {
        return l > r;
      }
    };
  }
}
