package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

public class RelationalOperationNE extends RelationalOperationEQ {
    public RelationalOperationNE() {
        super();
    }

    public RelationalOperationNE(BinaryFunction<Number, Number, Boolean> extension) {
        super(extension);
    }

    @Override
    protected Boolean operationDefault(Number l, Number r) {
        return !super.operationDefault(l, r);
    }
}
