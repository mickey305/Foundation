package com.mickey305.foundation.v3.lang.tuple;

import javax.annotation.Nonnull;

public class MutableSingle<T> extends Single<T> {
    /** use serialVersionUID for interoperability */
    private static final long serialVersionUID = 8314207908425199233L;

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
        super.setObject(object);
    }
}
