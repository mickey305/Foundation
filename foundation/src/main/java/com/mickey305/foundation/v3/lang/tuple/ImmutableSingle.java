package com.mickey305.foundation.v3.lang.tuple;

public class ImmutableSingle<T> extends Single<T> {
  /**
   * use serialVersionUID for interoperability
   */
  private static final long serialVersionUID = 1662786483949947910L;

  public ImmutableSingle(T object) {
    super(object);
  }

  public static <T> ImmutableSingle<T> of(T object) {
    return new ImmutableSingle<>(object);
  }
}
