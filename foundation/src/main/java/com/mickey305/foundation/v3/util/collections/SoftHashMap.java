/*
 * Copyright (c) 2017 - 2020 K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
