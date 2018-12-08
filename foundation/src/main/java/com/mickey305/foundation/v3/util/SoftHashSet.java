package com.mickey305.foundation.v3.util;

import org.apache.commons.collections4.map.AbstractReferenceMap;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.SOFT;
import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.WEAK;

public class SoftHashSet<E> extends AbstractReferenceSet<E, SoftHashMap<E, Object>>
    implements Set<E>, Serializable, Cloneable {
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;
  private static final AbstractReferenceMap.ReferenceStrength DEFAULT_KEY_TYPE = SOFT;
  private static final AbstractReferenceMap.ReferenceStrength DEFAULT_VALUE_TYPE = WEAK;
  private static final long serialVersionUID = -5802596856478904771L;
  
  public SoftHashSet() {
    map = new SoftHashMap<>(DEFAULT_KEY_TYPE, DEFAULT_VALUE_TYPE);
  }
  
  public SoftHashSet(Collection<? extends E> c) {
    final int capacity = Math.max((int) (c.size() / .75f) + 1, 16);
    map = new SoftHashMap<>(DEFAULT_KEY_TYPE, DEFAULT_VALUE_TYPE, capacity, DEFAULT_LOAD_FACTOR);
    addAll(c);
  }
  
  public SoftHashSet(int initialCapacity, float loadFactor) {
    map = new SoftHashMap<>(DEFAULT_KEY_TYPE, DEFAULT_VALUE_TYPE, initialCapacity, loadFactor);
  }
  
  public SoftHashSet(int initialCapacity) {
    map = new SoftHashMap<>(DEFAULT_KEY_TYPE, DEFAULT_VALUE_TYPE, initialCapacity, DEFAULT_LOAD_FACTOR);
  }
  
  //
  // key reference switchable constructors
  //
  
  protected SoftHashSet(final AbstractReferenceMap.ReferenceStrength keyType) {
    map = new SoftHashMap<>(keyType, DEFAULT_VALUE_TYPE);
  }
  
  protected SoftHashSet(final AbstractReferenceMap.ReferenceStrength keyType, Collection<? extends E> c) {
    final int capacity = Math.max((int) (c.size() / .75f) + 1, 16);
    map = new SoftHashMap<>(keyType, DEFAULT_VALUE_TYPE, capacity, DEFAULT_LOAD_FACTOR);
    addAll(c);
  }
  
  protected SoftHashSet(final AbstractReferenceMap.ReferenceStrength keyType, int initialCapacity, float loadFactor) {
    map = new SoftHashMap<>(keyType, DEFAULT_VALUE_TYPE, initialCapacity, loadFactor);
  }
  
  protected SoftHashSet(final AbstractReferenceMap.ReferenceStrength keyType, int initialCapacity) {
    map = new SoftHashMap<>(keyType, DEFAULT_VALUE_TYPE, initialCapacity, DEFAULT_LOAD_FACTOR);
  }
  
  @Override
  public SoftHashSet<E> clone() {
    SoftHashSet<E> newSet = (SoftHashSet<E>) super.clone();
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
