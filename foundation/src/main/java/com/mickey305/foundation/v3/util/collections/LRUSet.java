/*
 * Copyright (c) 2019. K.Misaki
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

import com.mickey305.foundation.v3.util.Log;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public class LRUSet<E> extends AbstractLinkedHashSet<E, LinkedHashMap<E, Object>>
    implements Set<E>, ILRU<E, Object>, Cloneable, Serializable {
  private static final long serialVersionUID = -6822425441737891595L;
  
  public static <E> LinkedHashSet<E> of(int capacity) {
    return new LRUSet<>(capacity);
  }
  
  private LRUSet(int capacity) {
    map = LRUMap.of(capacity);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public LRUSet<E> clone() {
    LRUSet<E> newSet = (LRUSet<E>) super.clone();
    Set<Map.Entry<E, Object>> entries = map.entrySet();
    for (Map.Entry<E, Object> entry : entries) {
      newSet.map.put(
          // shallow copy
          entry.getKey(), DUMMY_VAL
      );
    }
    return newSet;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Object referenceData(E key, Object dummy) {
    if (IS_DEBUG_MODE) {
      Log.d("element references method called. key=" + key);
    }
    map.remove(key);
    return map.put(key, DUMMY_VAL);
  }
}
