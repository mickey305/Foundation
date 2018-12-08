package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

import java.util.Objects;

public class OperationShortFactory extends AbstractOperationFactory<Short> {
  private OperationShortFactory() {
  }
  
  private static final class OperationShortFactoryHolder {
    private static OperationShortFactory Instance = new OperationShortFactory();
  }
  
  public static OperationShortFactory getInstance() {
    return OperationShortFactoryHolder.Instance;
  }
  
  @Override
  public AbstractNumberOperation<Short, Short> add() {
    return new AbstractNumberOperation<Short, Short>() {
      @Override
      protected Short operationDefault(Short l, Short r) {
        return (short) (l + r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Short, Short> sub() {
    return new AbstractNumberOperation<Short, Short>() {
      @Override
      protected Short operationDefault(Short l, Short r) {
        return (short) (l - r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Short, Short> multi() {
    return new AbstractNumberOperation<Short, Short>() {
      @Override
      protected Short operationDefault(Short l, Short r) {
        return (short) (l * r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Short, Short> div() {
    return new AbstractNumberOperation<Short, Short>() {
      @Override
      protected Short operationDefault(Short l, Short r) {
        return (short) (l / r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Short, Short> max() {
    return new AbstractNumberOperation<Short, Short>() {
      @Override
      protected Short operationDefault(Short l, Short r) {
        return (short) Math.max(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Short, Short> min() {
    return new AbstractNumberOperation<Short, Short>() {
      @Override
      protected Short operationDefault(Short l, Short r) {
        return (short) Math.min(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Short, Boolean> eq() {
    return new AbstractNumberOperation<Short, Boolean>() {
      @Override
      protected Boolean operationDefault(Short l, Short r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Short, Boolean> lt() {
    return new AbstractNumberOperation<Short, Boolean>() {
      @Override
      protected Boolean operationDefault(Short l, Short r) {
        return l < r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Short, Boolean> gt() {
    return new AbstractNumberOperation<Short, Boolean>() {
      @Override
      protected Boolean operationDefault(Short l, Short r) {
        return l > r;
      }
    };
  }
}
