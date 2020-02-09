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

package com.mickey305.foundation.v3.lang.tuple;

import java.io.Serializable;

public abstract class Single<T> implements Serializable {
  /**
   * use serialVersionUID for interoperability
   */
  private static final long serialVersionUID = -1859830271219122388L;

  private T object;

  protected Single(T object) {
    this.setObject(object);
  }

  public static <T> Single<T> of(T object) {
    return new MutableSingle<>(object);
  }

  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//
  public T get() {
    return this.getObject();
  }

  //===----------------------------------------------------------------------------------------------------------===//
  // Accessor                                                                                                       //
  //===----------------------------------------------------------------------------------------------------------===//
  T getObject() {
    return object;
  }

  void setObject(T object) {
    this.object = object;
  }
}
