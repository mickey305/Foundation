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

import com.mickey305.foundation.v3.util.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class AbstractLinearListAdapter<E> implements ILinearList<E> {
  private final Deque<E> deque;
  
  protected AbstractLinearListAdapter(Deque<E> deque) {
    this.deque = deque;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int size() {
    return deque.size();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return deque.isEmpty();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    deque.clear();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Deque<E> toDeque() {
    return deque;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Deque<E> toDeque(Deque<E> deque) {
    return (Deque<E>) this.toCollection(deque);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public List<E> toList(List<E> list) {
    return (List<E>) this.toCollection(list);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Set<E> toSet(Set<E> set) {
    set = (Set<E>) this.toCollection(set);
    Assert.requireEquals(set.size(), deque.size());
    return set;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    return deque.iterator();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<E> toCollection(Collection<E> collection) {
    collection.clear();
    collection.addAll(deque);
    return collection;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] toArray() {
    return deque.toArray();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public E[] toArray(E[] ary) {
    return deque.toArray(ary);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contains(E o) {
    return deque.contains(o);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(E o) {
    return deque.remove(o);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAll(Collection<E> c) {
    return deque.containsAll(c);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean removeAll(Collection<E> c) {
    return deque.removeAll(c);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean retainAll(Collection<E> c) {
    return deque.retainAll(c);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAll(E[] c) {
    return deque.containsAll(Arrays.asList(c));
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean removeAll(E[] c) {
    return deque.removeAll(Arrays.asList(c));
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean retainAll(E[] c) {
    return deque.retainAll(Arrays.asList(c));
  }
  
  protected Deque<E> getDeque() {
    return deque;
  }
}
