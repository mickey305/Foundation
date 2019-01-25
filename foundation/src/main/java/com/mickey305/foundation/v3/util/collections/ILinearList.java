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

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface ILinearList<E> {
  /**
   *
   * @return
   */
  int size();
  
  /**
   *
   * @return
   */
  boolean isEmpty();
  
  /**
   *
   */
  void clear();
  
  /**
   *
   * @return
   */
  Deque<E> toDeque();
  
  /**
   *
   * @param deque
   * @return
   */
  Deque<E> toDeque(Deque<E> deque);
  
  /**
   *
   * @param list
   * @return
   */
  List<E> toList(List<E> list);
  
  /**
   *
   * @param set
   * @return
   */
  Set<E> toSet(Set<E> set);
  
  /**
   *
   * @param collection
   * @return
   */
  Collection<E> toCollection(Collection<E> collection);
  
  /**
   *
   * @return
   */
  Object[] toArray();
  
  /**
   *
   * @param ary
   * @return
   */
  E[] toArray(E[] ary);
  
  /**
   *
   * @return
   */
  Iterator<E> iterator();
  
  /**
   *
   * @param o
   * @return
   */
  boolean contains(E o);
  
  /**
   *
   * @param o
   * @return
   */
  boolean remove(E o);
  
  /**
   *
   * @param c
   * @return
   */
  boolean containsAll(Collection<E> c);
  
  /**
   *
   * @param c
   * @return
   */
  boolean removeAll(Collection<E> c);
  
  /**
   *
   * @param c
   * @return
   */
  boolean retainAll(Collection<E> c);
  
  /**
   *
   * @param c
   * @return
   */
  boolean containsAll(E[] c);
  
  /**
   *
   * @param c
   * @return
   */
  boolean removeAll(E[] c);
  
  /**
   *
   * @param c
   * @return
   */
  boolean retainAll(E[] c);
  
  /**
   *
   * @return
   */
  E peek();
  
  /**
   *
   * @return
   */
  E remove();
  
  /**
   *
   * @param o
   */
  void add(E o);
  
  /**
   *
   * @param c
   */
  void addAll(Collection<E> c);
  
  /**
   *
   * @param c
   */
  void addAll(E[] c);
}
