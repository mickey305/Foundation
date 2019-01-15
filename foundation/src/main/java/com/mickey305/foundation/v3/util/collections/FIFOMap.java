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

public class FIFOMap<K, V> extends LinkedHashMap<K, V> {
  private static final long serialVersionUID = -1585678457031310289L;
  private final int capacity;
  
  public static <K, V> LinkedHashMap<K, V> of(int capacity) {
    return new FIFOMap<>(capacity);
  }
  
  private FIFOMap(int capacity) {
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
}
