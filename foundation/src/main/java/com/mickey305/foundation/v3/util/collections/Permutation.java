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

import com.mickey305.foundation.v3.util.ArrayUtil;

import java.io.Serializable;

public final class Permutation<T extends Serializable> {
  private final int baseIndex;
  private int index;
  private final T[] elements;
  private Permutation<T> nextPermutation;
  
  public Permutation(T[] elements) {
    this(0, 0, elements.clone());
  }
  
  private Permutation(int baseIndex, int index, T[] elements) {
    if (elements == null || elements.length == 0)
      throw new IllegalArgumentException();
    
    // initialization
    this.baseIndex = baseIndex;
    this.index = index;
    this.elements = elements;
    
    if (this.index < this.elements.length - 1)
      this.nextPermutation = new Permutation<>(this.baseIndex + 1, this.index + 1, this.elements);
  }
  
  public T[] getElements() {
    return this.elements;
  }
  
  /**
   * 順列トークンイテレーションメソッド
   *
   * @return イテレーション結果
   */
  public boolean next() {
    if (this.nextPermutation == null)
      return false;
    
    if (this.nextPermutation.next())
      return true;
    
    Permutation.swap(this.baseIndex, this.index, this.elements);
    
    this.index++;
    
    if (this.elements.length - 1 < this.index) {
      this.index = this.baseIndex;
      return false;
    }
    
    Permutation.swap(this.baseIndex, this.index, this.elements);
    
    return true;
  }
  
  /**
   * 要素入れ替えメソッド
   *
   * @param i1   要素番号１
   * @param i2   要素番号２
   * @param dest 入れ替え対象配列
   * @param <T>  入れ替え対象配列型
   */
  private static <T extends Serializable> void swap(int i1, int i2, T[] dest) {
    ArrayUtil.swap(dest, i1, i2);
  }
}

