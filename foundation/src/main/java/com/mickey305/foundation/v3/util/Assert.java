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
  
  public Assert<E> nonNull(final E object) {
    try {
      final E nonNullObject = Assert.requireNonNull(object);
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
   * true assertion
   * @param status target boolean
   * @throws AssertionException if {@code status} is false
   */
  public static void requireTrue(final boolean status) {
    if (IS_DEBUG_MODE && !status) {
      StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
      StackTraceElement element = StackFinder.tryGet(pos);
      Log.d("input data is false. called method info: " + Objects.toString(element));
    }
    if (!status) {
      throw new AssertionException("input data is false");
    }
  }
  
  /**
   * false assertion
   * @param status target boolean
   * @throws AssertionException if {@code status} is true
   */
  public static void requireFalse(final boolean status) {
    if (IS_DEBUG_MODE && status) {
      StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
      StackTraceElement element = StackFinder.tryGet(pos);
      Log.d("input data is true. called method info: " + Objects.toString(element));
    }
    if (status) {
      throw new AssertionException("input data is true");
    }
  }
  
  /**
   * wrapper method of {@link Objects#requireNonNull(Object)}.
   * @param object the object reference to check for nullity
   * @param <T> the type of the reference
   * @return {@code obj} if not {@code null}
   * @throws AssertionException if {@code obj} is {@code null}
   */
  public static <T> T requireNonNull(final T object) {
    if (IS_DEBUG_MODE && object == null) {
      StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
      StackTraceElement element = StackFinder.tryGet(pos);
      Log.d("input data is null-value. called method info: " + Objects.toString(element));
    }
    try {
      return Objects.requireNonNull(object);
    } catch (NullPointerException e) {
      throw new AssertionException("data is null", e);
    }
  }
  
  /**
   * is-null assertion
   * @param object check target object
   * @param <T> the type of the reference
   * @return {@code obj} if {@code null}
   * @throws AssertionException if {@code obj} is not {@code null}
   */
  public static <T> T requireNull(final T object) {
    if (IS_DEBUG_MODE && object != null) {
      StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
      StackTraceElement element = StackFinder.tryGet(pos);
      Log.d("input data is non-null-value. called method info: " + Objects.toString(element));
    }
    if (object != null) {
      throw new AssertionException("data is non-null. object: " + object.toString());
    }
    return null;
  }
  
  /**
   * equals assertion
   * @param a an object
   * @param b an object to be compared with {@code a} for equality
   * @throws AssertionException if {@code a} and {@code b} is different
   */
  public static void requireEquals(final Object a, final Object b) {
    final boolean result = Objects.equals(a, b);
    if (IS_DEBUG_MODE && !result) {
      StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
      StackTraceElement element = StackFinder.tryGet(pos);
      Log.d("input data is diff. called method info: " + Objects.toString(element));
    }
    if (!result) {
      throw new AssertionException("input data is different: data1=" + Objects.toString(a) + ", data2=" + Objects.toString(b));
    }
  }
  
  /**
   * not equals assertion
   * @param a an object
   * @param b an object to be compared with {@code a} for differently
   * @throws AssertionException if {@code a} and {@code b} is equal
   */
  public static void requireNotEquals(final Object a, final Object b) {
    final boolean result = Objects.equals(a, b);
    if (IS_DEBUG_MODE && result) {
      StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
      StackTraceElement element = StackFinder.tryGet(pos);
      Log.d("input data is same. called method info: " + Objects.toString(element));
    }
    if (result) {
      throw new AssertionException("input data is same: data1=" + Objects.toString(a) + ", data2=" + Objects.toString(b));
    }
  }
  
  /**
   * deep-equals assertion
   * @param a an object
   * @param b an object to be compared with {@code a} for equality
   * @throws AssertionException if {@code a} and {@code b} is different
   */
  public static void requireDeepEquals(final Object a, final Object b) {
    final boolean result = Objects.deepEquals(a, b);
    if (IS_DEBUG_MODE && !result) {
      StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
      StackTraceElement element = StackFinder.tryGet(pos);
      Log.d("input data is diff. called method info: " + Objects.toString(element));
    }
    if (!result) {
      throw new AssertionException("input data is different: data1=" + Objects.toString(a) + ", data2=" + Objects.toString(b));
    }
  }
  
  /**
   * not deep-equals assertion
   * @param a an object
   * @param b an object to be compared with {@code a} for differently
   * @throws AssertionException if {@code a} and {@code b} is equal
   */
  public static void requireNotDeepEquals(final Object a, final Object b) {
    final boolean result = Objects.deepEquals(a, b);
    if (IS_DEBUG_MODE && result) {
      StackFinder.Position pos = StackFinder.Position.thisMethodCaller();
      StackTraceElement element = StackFinder.tryGet(pos);
      Log.d("input data is same. called method info: " + Objects.toString(element));
    }
    if (result) {
      throw new AssertionException("input data is same: data1=" + Objects.toString(a) + ", data2=" + Objects.toString(b));
    }
  }
  
  public static class AssertionException extends RuntimeException {
    private static final long serialVersionUID = 6466366065807358619L;
  
    public AssertionException(String msg) {
      super(msg);
    }
    
    public AssertionException(Throwable t) {
      super(t);
    }
    
    public AssertionException(String msg, Throwable t) {
      super(msg, t);
    }
  }
}
