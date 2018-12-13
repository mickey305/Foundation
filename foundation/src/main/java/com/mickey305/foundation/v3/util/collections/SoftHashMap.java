package com.mickey305.foundation.v3.util.collections;

import org.apache.commons.collections4.map.AbstractReferenceMap;
import org.apache.commons.collections4.map.ReferenceMap;

import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.HARD;
import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.SOFT;

public class SoftHashMap<K, V> extends ReferenceMap<K, V> {
  protected static final AbstractReferenceMap.ReferenceStrength DEFAULT_KEY_TYPE = HARD;
  protected static final AbstractReferenceMap.ReferenceStrength DEFAULT_VALUE_TYPE = SOFT;
  private static final long serialVersionUID = 8020690413962254679L;
  
  public SoftHashMap() {
    super(DEFAULT_KEY_TYPE, DEFAULT_VALUE_TYPE, DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, false);
  }
  
  public SoftHashMap(final ReferenceStrength keyType, final ReferenceStrength valueType) {
    super(keyType, valueType, DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, false);
  }
  
  public SoftHashMap(final ReferenceStrength keyType, final ReferenceStrength valueType, final boolean purgeValues) {
    super(keyType, valueType, DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, purgeValues);
  }
  
  public SoftHashMap(final ReferenceStrength keyType, final ReferenceStrength valueType, final int capacity,
                     final float loadFactor) {
    super(keyType, valueType, capacity, loadFactor, false);
  }
  
  public SoftHashMap(final ReferenceStrength keyType, final ReferenceStrength valueType, final int capacity,
                     final float loadFactor, final boolean purgeValues) {
    super(keyType, valueType, capacity, loadFactor, purgeValues);
  }
  
  public SoftHashMap(final int capacity) {
    super(DEFAULT_KEY_TYPE, DEFAULT_VALUE_TYPE, capacity, DEFAULT_LOAD_FACTOR, false);
  }
}
