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

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Proxy list class of element null check implements.
 * @param <E> element type
 */
public class NonNullElementList<E> implements List<E>, Cloneable, Serializable {
  private static final long serialVersionUID = 7044186567936720501L;
  private final List<E> list;
  
  /**
   * constructor
   * <p>data input sample below.
   * </p>
   * <pre>{@code
   * List<String> list = new NonNullElementList<>(new ArrayList<String>());
   * }</pre>
   * @param list
   */
  public NonNullElementList(List<E> list) {
    for (E e : list) {
      Assert.requireNonNull(e);
    }
    this.list = list;
  }
  
  @Override
  public E get(int index) {
    return list.get(index);
  }
  
  @Override
  public E set(int index, E element) {
    Assert.requireNonNull(element);
    return list.set(index, element);
  }
  
  @Override
  public void add(int index, E element) {
    Assert.requireNonNull(element);
    list.add(index, element);
  }
  
  @Override
  public E remove(int index) {
    return list.remove(index);
  }
  
  @Override
  public int indexOf(Object o) {
    return list.indexOf(o);
  }
  
  @Override
  public int lastIndexOf(Object o) {
    return list.lastIndexOf(o);
  }
  
  @Override
  public ListIterator<E> listIterator() {
    return list.listIterator();
  }
  
  @Override
  public ListIterator<E> listIterator(int index) {
    return list.listIterator(index);
  }
  
  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return list.subList(fromIndex, toIndex);
  }
  
  @Override
  public int size() {
    return list.size();
  }
  
  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }
  
  @Override
  public boolean contains(Object o) {
    return list.contains(o);
  }
  
  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }
  
  @Override
  public Object[] toArray() {
    return list.toArray();
  }
  
  @Override
  public <T> T[] toArray(T[] a) {
    return list.toArray(a);
  }
  
  @Override
  public boolean add(E e) {
    Assert.requireNonNull(e);
    return list.add(e);
  }
  
  @Override
  public boolean remove(Object o) {
    return list.remove(o);
  }
  
  @Override
  public boolean containsAll(Collection<?> c) {
    return list.containsAll(c);
  }
  
  @Override
  public boolean addAll(Collection<? extends E> c) {
    for (E e : c) {
      Assert.requireNonNull(e);
    }
    return list.addAll(c);
  }
  
  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    for (E e : c) {
      Assert.requireNonNull(e);
    }
    return list.addAll(index, c);
  }
  
  @Override
  public boolean removeAll(Collection<?> c) {
    return list.removeAll(c);
  }
  
  @Override
  public boolean retainAll(Collection<?> c) {
    return list.retainAll(c);
  }
  
  @Override
  public void clear() {
    list.clear();
  }
}
