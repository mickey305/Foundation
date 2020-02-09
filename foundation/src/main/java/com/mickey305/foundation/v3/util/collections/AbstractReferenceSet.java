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

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class AbstractReferenceSet<E, M extends Map<E, Object>> extends AbstractSet<E>
    implements Set<E>, Serializable, Cloneable {
  private static final long serialVersionUID = -6946699353161557573L;
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
   * タイミングによっては、ガベージコレクトが動作した場合に、実際の要素数より多くのサイズであるかのように
   * ふるまう場合があるため、このサイズメソッドの情報をもとに繰り返し処理などを実行する場合は、注意する。
   * <p>
   * {@inheritDoc}
   */
  @Override
  public int size() {
    return this.recountSize();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return this.size() == 0;
  }
  
  /**
   * キー要素イテレータをもとに、キー要素数を算出する
   *
   * @return キー要素数
   */
  private int recountSize() {
    final Iterator<E> iterator = this.iterator();
    int i = 0;
    while (iterator.hasNext()) {
      iterator.next();
      i++;
    }
    return i;
  }
  
  /**
   * @return
   */
  protected int offsetSize() {
    return this.allocatedSize() - this.size();
  }
  
  /**
   * @return
   */
  protected boolean isFrag() {
    return this.offsetSize() != 0;
  }
  
  /**
   * @return
   */
  protected int allocatedSize() {
    return map.keySet().size();
  }
  
  /**
   * @return
   */
  protected boolean isAllocatedEmpty() {
    return this.allocatedSize() == 0;
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
  public AbstractReferenceSet<E, M> clone() {
    try {
      AbstractReferenceSet<E, M> newSet = (AbstractReferenceSet<E, M>) super.clone();
      Set<Map.Entry<E, Object>> entries = map.entrySet();
      for (Map.Entry<E, Object> entry : entries) {
        newSet.map.put(
            // shallow copy
            entry.getKey(), DUMMY_VAL
        );
      }
      return newSet;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}
