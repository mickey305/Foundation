package com.mickey305.foundation.v3.lang.tuple;

import javax.annotation.Nonnull;

public class ImmutableSingle<T> extends Single<T> {
    /** use serialVersionUID for interoperability */
    private static final long serialVersionUID = 1389534397433311780L;

    public ImmutableSingle(T object) {
        super(object);
    }

    public static <T> ImmutableSingle<T> of(T object) {
        return new ImmutableSingle<>(object);
    }

    public void setNull() {
        super.setObject(null);
    }

    public void set(@Nonnull T object) {
        super.setObject(object);
    }
}
