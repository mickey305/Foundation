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

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class FIFOSet<E> extends AbstractLinkedHashSet<E, LinkedHashMap<E, Object>>
    implements Set<E>, Cloneable, Serializable {
  private static final long serialVersionUID = 2989051583487252498L;
  
  public static <E> LinkedHashSet<E> of(int capacity) {
    return new FIFOSet<>(capacity);
  }
  
  private FIFOSet(int capacity) {
    map = FIFOMap.of(capacity);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public FIFOSet<E> clone() {
    FIFOSet<E> newSet = (FIFOSet<E>) super.clone();
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
