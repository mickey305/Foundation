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

package com.mickey305.foundation.v3.compat.util;

import com.mickey305.foundation.v3.util.Assert;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

// JDK1.8 以降は標準API(java.util.Map)を参照
public class FunctionalMapAdapter<K, V> implements FunctionalMap<K, V> {
  private final Map<K, V> map;
  
  public FunctionalMapAdapter(Map<K, V> map) {
    this.map = map;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public V computeIfAbsent(K key, Function<? super K, ? extends V> function) {
    Assert.requireNonNull(function);
    V result;
    if ((result = map.get(key)) == null) {
      V newValue;
      if ((newValue = function.apply(key)) != null) {
        map.put(key, newValue);
        return newValue;
      }
    }
    return result;
  }
  
  // proxy of java.util.Map
  
  @Override
  public int size() {
    return map.size();
  }
  
  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }
  
  @Override
  public boolean containsKey(Object key) {
    return map.containsKey(key);
  }
  
  @Override
  public boolean containsValue(Object value) {
    return map.containsValue(value);
  }
  
  @Override
  public V get(Object key) {
    return map.get(key);
  }
  
  @Override
  public V put(K key, V value) {
    return map.put(key, value);
  }
  
  @Override
  public V remove(Object key) {
    return map.remove(key);
  }
  
  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    map.putAll(m);
  }
  
  @Override
  public void clear() {
    map.clear();
  }
  
  @Override
  public Set<K> keySet() {
    return map.keySet();
  }
  
  @Override
  public Collection<V> values() {
    return map.values();
  }
  
  @Override
  public Set<Entry<K, V>> entrySet() {
    return map.entrySet();
  }
  
  @Override
  public boolean equals(Object o) {
    return map.equals(o);
  }
  
  @Override
  public int hashCode() {
    return map.hashCode();
  }
}
