package com.mickey305.foundation.v3.util;

import java.io.Serializable;

public final class Permutation<T extends Serializable> {
    private final int baseIndex;
    private int index;
    private T[] elements;
    private Permutation<T> nextPermutation;

    public Permutation(T[] elements) {
        this(0, 0, elements.clone());
    }

    private Permutation(int baseIndex, int index, T[] elements) {
        if (elements == null || elements.length == 0)
            throw new IllegalArgumentException();

        // initialization
        this.baseIndex = baseIndex;
        this.index     = index;
        this.elements  = elements;

        if (this.index < this.elements.length - 1)
            this.nextPermutation = new Permutation<>(this.baseIndex + 1, this.index + 1, this.elements);
    }

    public T[] getElements() {
        return this.elements;
    }

    /**
     *
     * @return
     */
    public boolean next() {
        if (this.nextPermutation == null)
            return false;

        if (this.nextPermutation.next())
            return true;

        Permutation.swap(this.baseIndex, this.index, this.elements);

        this.index++;

        if (this.elements.length -1 < this.index) {
            this.index = this.baseIndex;
            return false;
        }

        Permutation.swap(this.baseIndex, this.index, this.elements);

        return true;
    }

    /**
     *
     * @param i1
     * @param i2
     * @param dest
     * @param <T>
     */
    private static <T extends Serializable> void swap(int i1, int i2, T[] dest) {
        final T element = dest[i1];
        dest[i1] = dest[i2];
        dest[i2] = element;
    }
}

