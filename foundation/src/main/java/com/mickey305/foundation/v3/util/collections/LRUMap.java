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

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUMap<K, V> extends LinkedHashMap<K, V> {
  private static final long serialVersionUID = 641853634711374566L;
  private final int capacity;
  
  public static <K, V> LinkedHashMap<K, V> of(int capacity) {
    return new LRUMap<>(capacity);
  }
  
  private LRUMap(int capacity) {
    super(capacity);
    this.capacity = capacity;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest)  {
    return size() > capacity;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public V get(Object key) {
    final V value = super.get(key);
    if (value != null) {
      remove(key);
      put((K) key, value);
    }
    return value;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public boolean containsKey(Object key) {
    final boolean contains = super.containsKey(key);
    if (contains) {
      final V value = remove(key);
      put((K) key, value);
    }
    return contains;
  }
}
