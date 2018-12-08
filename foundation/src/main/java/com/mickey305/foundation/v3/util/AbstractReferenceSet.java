package com.mickey305.foundation.v3.util;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class AbstractReferenceSet<E, M extends Map<E, Object>> extends AbstractSet<E>
    implements Set<E>, Serializable, Cloneable {
  private static final long serialVersionUID = 888121226761401105L;
  protected transient M map;
  static final Object DUMMY_VAL;
  
  static {
    // create dummy value
    DUMMY_VAL = new Object();
  }
  
  @Nonnull
  @Override
  public Iterator<E> iterator() {
    return map.keySet().iterator();
  }
  
  @Override
  public int size() {
    return this.recountSize();
  }
  
  @Override
  public boolean isEmpty() {
    return this.size() == 0;
  }
  
  /**
   * キー要素イテレータをもとに、キー要素数を算出する
   *
   * @return キー要素数
   */
  private int recountSize() {
    final Iterator<E> iterator = this.iterator();
    int i = 0;
    while (iterator.hasNext()) {
      iterator.next();
      i++;
    }
    return i;
  }
  
  protected int offsetSize() {
    return this.allocatedSize() - this.size();
  }
  
  protected boolean isFrag() {
    return this.offsetSize() != 0;
  }
  
  protected int allocatedSize() {
    return map.keySet().size();
  }
  
  protected boolean isAllocatedEmpty() {
    return this.allocatedSize() == 0;
  }
  
  @Override
  public boolean contains(Object o) {
    return map.containsKey(o);
  }
  
  @Override
  public boolean add(E e) {
    return map.put(e, DUMMY_VAL) == null;
  }
  
  @Override
  public boolean remove(Object o) {
    return map.remove(o) == DUMMY_VAL;
  }
  
  @Override
  public void clear() {
    map.clear();
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public AbstractReferenceSet<E, M> clone() {
    try {
      AbstractReferenceSet<E, M> newSet = (AbstractReferenceSet<E, M>) super.clone();
      Set<Map.Entry<E, Object>> entries = map.entrySet();
      for (Map.Entry<E, Object> entry : entries) {
        newSet.map.put(
            // shallow copy
            entry.getKey(), DUMMY_VAL
        );
      }
      return newSet;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}
