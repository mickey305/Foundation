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

package com.mickey305.foundation.v3.lang.tuple;

import com.mickey305.foundation.v3.util.Assert;

import javax.annotation.Nonnull;

public class MutableSingle<T> extends Single<T> {
  /**
   * use serialVersionUID for interoperability
   */
  private static final long serialVersionUID = 1389534397433311780L;

  public MutableSingle(T object) {
    super(object);
  }

  public static <T> MutableSingle<T> of(T object) {
    return new MutableSingle<>(object);
  }

  public void setNull() {
    super.setObject(null);
  }

  public void set(@Nonnull T object) {
    Assert.requireNonNull(object);
    super.setObject(object);
  }
}
