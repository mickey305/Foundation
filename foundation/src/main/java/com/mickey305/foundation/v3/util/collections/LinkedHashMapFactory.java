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

class LinkedHashMapFactory {
  private LinkedHashMapFactory() {
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
  public static <K, V> LinkedHashMap<K, V> newCache(CacheType type, int capacity) {
    if (type == CacheType.FIFO) return FIFOMap.of(capacity);
    if (type == CacheType.LRU) return LRUMap.of(capacity);
    // throwing exception
    throw new RuntimeException();
  }
}
