package com.mickey305.foundation.v3.util;

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
      Log.d("input data is null-value");
    }
    return Objects.requireNonNull(object);
  }
}
