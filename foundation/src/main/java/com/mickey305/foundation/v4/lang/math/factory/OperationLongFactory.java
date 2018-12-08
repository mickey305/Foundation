package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

import java.util.Objects;

public class OperationLongFactory extends AbstractOperationFactory<Long> {
  private OperationLongFactory() {
  }
  
  private static final class OperationLongFactoryHolder {
    private static OperationLongFactory Instance = new OperationLongFactory();
  }
  
  public static OperationLongFactory getInstance() {
    return OperationLongFactoryHolder.Instance;
  }
  
  @Override
  public AbstractNumberOperation<Long, Long> add() {
    return new AbstractNumberOperation<Long, Long>() {
      @Override
      protected Long operationDefault(Long l, Long r) {
        return l + r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Long, Long> sub() {
    return new AbstractNumberOperation<Long, Long>() {
      @Override
      protected Long operationDefault(Long l, Long r) {
        return l - r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Long, Long> multi() {
    return new AbstractNumberOperation<Long, Long>() {
      @Override
      protected Long operationDefault(Long l, Long r) {
        return l * r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Long, Long> div() {
    return new AbstractNumberOperation<Long, Long>() {
      @Override
      protected Long operationDefault(Long l, Long r) {
        return l / r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Long, Long> max() {
    return new AbstractNumberOperation<Long, Long>() {
      @Override
      protected Long operationDefault(Long l, Long r) {
        return Math.max(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Long, Long> min() {
    return new AbstractNumberOperation<Long, Long>() {
      @Override
      protected Long operationDefault(Long l, Long r) {
        return Math.min(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Long, Boolean> eq() {
    return new AbstractNumberOperation<Long, Boolean>() {
      @Override
      protected Boolean operationDefault(Long l, Long r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Long, Boolean> lt() {
    return new AbstractNumberOperation<Long, Boolean>() {
      @Override
      protected Boolean operationDefault(Long l, Long r) {
        return l < r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Long, Boolean> gt() {
    return new AbstractNumberOperation<Long, Boolean>() {
      @Override
      protected Boolean operationDefault(Long l, Long r) {
        return l > r;
      }
    };
  }
}
