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

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class CacheFactory {
  private static final int DEFAULT_CAPACITY = 1024 * 2;
  
  private CacheFactory() {
    // nop
  }
  
  /**
   *
   * @param type
   * @param capacity
   * @param <K>
   * @param <V>
   * @return
   */
  public static <K, V> LinkedHashMap<K, V> newMap(CacheType type, int capacity) {
    return LinkedHashMapFactory.newCache(type, capacity);
  }
  
  /**
   *
   * @param type
   * @param capacity
   * @param <K>
   * @return
   */
  public static <K> LinkedHashSet<K> newSet(CacheType type, int capacity) {
    return LinkedHashSetFactory.newCache(type, capacity);
  }
  
  /**
   *
   * @param capacity
   * @param <K>
   * @param <V>
   * @return
   */
  public static <K, V> ILRUMap<K, V> newLRUMap(int capacity) {
    return LRUMap.<K, V>of(capacity);
  }
  
  /**
   *
   * @param capacity
   * @param <K>
   * @return
   */
  public static <K> ILRUSet<K> newLRUSet(int capacity) {
    return LRUSet.<K>of(capacity);
  }
  
  /**
   *
   * @param type
   * @param <K>
   * @param <V>
   * @return
   */
  public static <K, V> LinkedHashMap<K, V> newMap(CacheType type) {
    return newMap(type, DEFAULT_CAPACITY);
  }
  
  /**
   *
   * @param type
   * @param <K>
   * @return
   */
  public static <K> LinkedHashSet<K> newSet(CacheType type) {
    return newSet(type, DEFAULT_CAPACITY);
  }
  
  /**
   *
   * @param <K>
   * @param <V>
   * @return
   */
  public static <K, V> ILRUMap<K, V> newLRUMap() {
    return newLRUMap(DEFAULT_CAPACITY);
  }
  
  /**
   *
   * @param <K>
   * @return
   */
  public static <K> ILRUSet<K> newLRUSet() {
    return newLRUSet(DEFAULT_CAPACITY);
  }
}
