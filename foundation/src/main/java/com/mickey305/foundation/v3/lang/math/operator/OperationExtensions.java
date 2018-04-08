package com.mickey305.foundation.v3.lang.math.operator;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

public interface OperationExtensions<T, U, R> {

    /**
     * 拡張演算処理注入メソッド
     * @param extension 注入対象演算処理
     */
    void setExtension(BinaryFunction<T, U, R> extension);
}
