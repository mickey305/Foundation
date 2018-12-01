package com.mickey305.foundation.v4.lang.math.operator;

public interface IElementInitializer<E extends Number> {
  E zero();
  
  E one();
  
  E minusOne();
  
  E[] array(int size);
  
  E[][] table(int r, int c);
  
  E convertFrom(Number n) throws RuntimeException;
}
