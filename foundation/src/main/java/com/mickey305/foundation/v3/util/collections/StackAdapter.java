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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;

public class StackAdapter<E> extends AbstractLinearListAdapter<E> implements IStack<E>, ILinearList<E>, Serializable {
  private static final long serialVersionUID = -8406324575960631491L;
  
  public StackAdapter(Deque<E> deque) {
    super(deque);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public E peek() {
    return getDeque().peekFirst();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void push(E element) {
    getDeque().offerFirst(element);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void pushAll(Collection<E> c) {
    for (E e : c) {
      this.push(e);
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void pushAll(E[] c) {
    this.pushAll(Arrays.asList(c));
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public E pop() {
    return getDeque().pollFirst();
  }
}
