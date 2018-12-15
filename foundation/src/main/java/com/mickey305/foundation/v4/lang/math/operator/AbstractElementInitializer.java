package com.mickey305.foundation.v4.lang.math.operator;

import java.lang.reflect.Array;

public abstract class AbstractElementInitializer<E extends Number> implements IElementInitializer<E> {
  private final Class<E> type;
  
  @SuppressWarnings("unchecked")
  protected AbstractElementInitializer(E... dummy) {
    type = (Class<E>) dummy.getClass().getComponentType();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public E[] array(int size) {
    return (E[]) Array.newInstance(type, size);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public E[][] table(int r, int c) {
    final Class<E[]> type = (Class<E[]>) this.array(1).getClass();
    final E[][] table = (E[][]) Array.newInstance(type, r);
    for (int i = 0; i < r; i++) {
      table[i] = this.array(c);
    }
    return table;
  }
}
