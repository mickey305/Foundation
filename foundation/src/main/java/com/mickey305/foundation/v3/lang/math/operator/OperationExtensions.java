package com.mickey305.foundation.v3.lang.math.operator;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

public interface OperationExtensions<T, U, R> {

    /**
     *
     * @param extension
     */
    void setExtension(BinaryFunction<T, U, R> extension);
}
