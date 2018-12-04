package com.mickey305.foundation.v3.util;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class WeakHashSet<E> extends AbstractSet<E> implements Set<E>, Serializable {
  private static final long serialVersionUID = -224180042445043218L;
  private final transient WeakHashMap<E, Object> map;
  private static final Object DUMMY_VAL;
  
  static {
    DUMMY_VAL = new Object();
  }
  
  public WeakHashSet() {
    map = new WeakHashMap<>();
  }
  
  public WeakHashSet(Collection<? extends E> c) {
    map = new WeakHashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
    addAll(c);
  }
  
  public WeakHashSet(int initialCapacity, float loadFactor) {
    map = new WeakHashMap<>(initialCapacity, loadFactor);
  }
  
  public WeakHashSet(int initialCapacity) {
    map = new WeakHashMap<>(initialCapacity);
  }
  
  @Nonnull
  @Override
  public Iterator<E> iterator() {
    return map.keySet().iterator();
  }
  
  @Override
  public int size() {
    return map.size();
  }
  
  @Override
  public boolean isEmpty() {
    return map.isEmpty();
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
  public WeakHashSet<E> clone() {
    try {
      WeakHashSet<E> newSet = (WeakHashSet<E>) super.clone();
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
