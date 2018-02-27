package com.mickey305.foundation.v3.lang.tuple;

import java.io.Serializable;

public abstract class Single<T> implements Serializable {
    /** use serialVersionUID for interoperability */
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
