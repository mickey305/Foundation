package com.mickey305.foundation.v3.lang.tuple;

public class ImmutableSingle<T> extends Single<T> {
    /** use serialVersionUID for interoperability */
    private static final long serialVersionUID = 4494194685134984525L;

    public ImmutableSingle(T object) {
        super(object);
    }

    public static <T> ImmutableSingle<T> of(T object) {
        return new ImmutableSingle<>(object);
    }
}
