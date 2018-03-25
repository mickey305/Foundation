package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

public class RelationalOperationGE extends RelationalOperationEQ {
    private final RelationalOperationGT opGT;

    public RelationalOperationGE() {
        super();
        opGT = new RelationalOperationGT();
    }

    public RelationalOperationGE(BinaryFunction<Number, Number, Boolean> extension) {
        super(extension);
        opGT = new RelationalOperationGT();
    }

    @Override
    protected Boolean operationDefault(Number l, Number r) {
        return super.operationDefault(l, r) || opGT.operationDefault(l, r);
    }
}
