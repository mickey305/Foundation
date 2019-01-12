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

package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.lang.StackFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public class Assert<E> {
  private final List<E> result;
  
  public Assert() {
    result = new ArrayList<>();
  }
  
  public Assert nonNull(E object) {
    try {
      final E nonNullObject = Assert.requireNonNull(object);
      assert nonNullObject != null;
      result.add(nonNullObject);
    } catch (Exception e) {
      Log.e(e.getMessage());
      throw e;
    }
    return this;
  }
  
  public List<E> getResult() {
    return Collections.unmodifiableList(result);
  }
  
  /**
   * wrapper method of {@link Objects#requireNonNull(Object)}.
   * Checks that the specified object reference is not {@code null}. This
   * method is designed primarily for doing parameter validation in methods
   * and constructors, as demonstrated below:
   * <blockquote><pre>
   * public Foo(Bar bar) {
   *     this.bar = Assert.requireNonNull(bar);
   * }
   * </pre></blockquote>
   *
   * @param object the object reference to check for nullity
   * @param <T> the type of the reference
   * @return {@code obj} if not {@code null}
   * @throws NullPointerException if {@code obj} is {@code null}
   */
  public static <T> T requireNonNull(T object) {
    if (IS_DEBUG_MODE && object == null) {
      StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
      StackTraceElement element = StackFinder.tryGet(pos);
      assert element != null;
      Log.d("input data is null-value. called method info: " + element.toString());
    }
    return Objects.requireNonNull(object);
  }
}
