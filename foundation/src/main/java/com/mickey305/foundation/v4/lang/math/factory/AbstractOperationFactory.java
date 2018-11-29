package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

public abstract class AbstractOperationFactory<E extends Number> implements IOperationFactory<E> {
  @Override
  public AbstractNumberOperation<E, Boolean> ne() {
    return new AbstractNumberOperation<E, Boolean>() {
      @Override
      protected Boolean operationDefault(E l, E r) {
        return !eq().apply(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<E, Boolean> le() {
    return new AbstractNumberOperation<E, Boolean>() {
      @Override
      protected Boolean operationDefault(E l, E r) {
        return eq().apply(l, r) || lt().apply(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<E, Boolean> ge() {
    return new AbstractNumberOperation<E, Boolean>() {
      @Override
      protected Boolean operationDefault(E l, E r) {
        return eq().apply(l, r) || gt().apply(l, r);
      }
    };
  }
}
