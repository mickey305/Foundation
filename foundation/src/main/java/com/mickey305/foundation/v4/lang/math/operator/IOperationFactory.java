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

package com.mickey305.foundation.v4.lang.math.operator;

public interface IOperationFactory<E extends Number> {
  ///////////////////////////////////
  // Operator
  ///////////////////////////////////
  
  /**
   * Standard operator - Add
   * 加算演算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, E> add();
  
  /**
   * Standard operator - Sub
   * 減算演算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, E> sub();
  
  /**
   * Standard operator - Multi
   * 乗算演算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, E> multi();
  
  /**
   * Standard operator - div
   * 除算演算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, E> div();
  
  /**
   * Standard operator - max
   * 最大演算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, E> max();
  
  /**
   * Standard operator - min
   * 最小演算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, E> min();
  
  ///////////////////////////////////
  // Relational Operator
  ///////////////////////////////////
  
  /**
   * Condition - equal
   * 比較算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, Boolean> eq();
  
  /**
   * Condition - not equal
   * 比較算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, Boolean> ne();
  
  /**
   * Condition - less than
   * 比較算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, Boolean> lt();
  
  /**
   * Condition - less than or equal
   * 比較算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, Boolean> le();
  
  /**
   * Condition - grater than
   * 比較算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, Boolean> gt();
  
  /**
   * Condition - grater than or equal
   * 比較算子オブジェクト
   * @return Operator object
   */
  AbstractNumberOperation<E, Boolean> ge();
}
