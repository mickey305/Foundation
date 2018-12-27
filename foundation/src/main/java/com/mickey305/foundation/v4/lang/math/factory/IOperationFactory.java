/*
 * Copyright (c) 2018. K.Misaki
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

package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

public interface IOperationFactory<E extends Number> {
  //
  AbstractNumberOperation<E, E> add();
  
  AbstractNumberOperation<E, E> sub();
  
  AbstractNumberOperation<E, E> multi();
  
  AbstractNumberOperation<E, E> div();
  
  AbstractNumberOperation<E, E> max();
  
  AbstractNumberOperation<E, E> min();
  
  //
  AbstractNumberOperation<E, Boolean> eq();
  
  AbstractNumberOperation<E, Boolean> ne();
  
  AbstractNumberOperation<E, Boolean> lt();
  
  AbstractNumberOperation<E, Boolean> le();
  
  AbstractNumberOperation<E, Boolean> gt();
  
  AbstractNumberOperation<E, Boolean> ge();
}
