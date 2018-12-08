package com.mickey305.foundation.v3.lang.tuple;

public class MutableSingle<T> extends Single<T> {
  /**
   * use serialVersionUID for interoperability
   */
  private static final long serialVersionUID = 1662786483949947910L;

  public MutableSingle(T object) {
    super(object);
  }

  public static <T> MutableSingle<T> of(T object) {
    return new MutableSingle<>(object);
  }
}
