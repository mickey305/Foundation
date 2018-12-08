package com.mickey305.foundation.v3.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class WeakHashSet<E> extends AbstractReferenceSet<E, WeakHashMap<E, Object>>
    implements Set<E>, Serializable, Cloneable {
  private static final long serialVersionUID = -3655331414093806702L;
  
  public WeakHashSet() {
    map = new WeakHashMap<>();
  }
  
  public WeakHashSet(Collection<? extends E> c) {
    map = new WeakHashMap<>(Math.max((int) (c.size() / .75f) + 1, 16));
    addAll(c);
  }
  
  public WeakHashSet(int initialCapacity, float loadFactor) {
    map = new WeakHashMap<>(initialCapacity, loadFactor);
  }
  
  public WeakHashSet(int initialCapacity) {
    map = new WeakHashMap<>(initialCapacity);
  }
  
  @Override
  public WeakHashSet<E> clone() {
    WeakHashSet<E> newSet = (WeakHashSet<E>) super.clone();
    Set<Map.Entry<E, Object>> entries = map.entrySet();
    for (Map.Entry<E, Object> entry : entries) {
      newSet.map.put(
          // shallow copy
          entry.getKey(), DUMMY_VAL
      );
    }
    return newSet;
  }
}
