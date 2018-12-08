package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

public interface IOperationFactory<E extends Number> {
  //
  AbstractNumberOperation<E, E> add();
  
  AbstractNumberOperation<E, E> sub();
  
  AbstractNumberOperation<E, E> multi();
  
  AbstractNumberOperation<E, E> div();
  
  AbstractNumberOperation<E, E> max();
  
  AbstractNumberOperation<E, E> min();
  
  //
  AbstractNumberOperation<E, Boolean> eq();
  
  AbstractNumberOperation<E, Boolean> ne();
  
  AbstractNumberOperation<E, Boolean> lt();
  
  AbstractNumberOperation<E, Boolean> le();
  
  AbstractNumberOperation<E, Boolean> gt();
  
  AbstractNumberOperation<E, Boolean> ge();
}
