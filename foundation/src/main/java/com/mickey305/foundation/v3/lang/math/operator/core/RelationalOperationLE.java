package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

public class RelationalOperationLE extends RelationalOperationEQ {
    private final RelationalOperationLT opLT;

    public RelationalOperationLE() {
        super();
        opLT = new RelationalOperationLT();
    }

    public RelationalOperationLE(BinaryFunction<Number, Number, Boolean> extension) {
        super(extension);
        opLT = new RelationalOperationLT();
    }

    @Override
    protected Boolean operationDefault(Number l, Number r) {
        return super.operationDefault(l, r) || opLT.operationDefault(l, r);
    }
}
