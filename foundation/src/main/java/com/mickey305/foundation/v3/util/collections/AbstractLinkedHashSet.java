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

import com.mickey305.foundation.v3.util.Assert;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractLinkedHashSet<E, M extends LinkedHashMap<E, Object>> extends LinkedHashSet<E>
    implements Set<E>, Serializable, Cloneable {
  private static final long serialVersionUID = 998876508331857075L;
  protected transient M map;
  static final Object DUMMY_VAL;
  
  static {
    // create dummy value
    DUMMY_VAL = new Object();
  }
  
  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public Iterator<E> iterator() {
    Iterator<E> iterator = map.keySet().iterator();
    Assert.requireNonNull(iterator);
    return iterator;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int size() {
    return this.map.size();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return this.size() == 0;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contains(Object o) {
    return map.containsKey(o);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(E e) {
    return map.put(e, DUMMY_VAL) == null;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(Object o) {
    return map.remove(o) == DUMMY_VAL;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    map.clear();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public AbstractLinkedHashSet<E, M> clone() {
    AbstractLinkedHashSet<E, M> newSet = (AbstractLinkedHashSet<E, M>) super.clone();
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
