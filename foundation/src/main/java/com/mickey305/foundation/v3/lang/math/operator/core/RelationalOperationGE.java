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
        final Boolean status1 = super.operationDefault(l, r);
        final Boolean status2 = opGT.operationDefault(l, r);
        return (status1 == null || status2 == null)
                ? null
                : status1 || status2;
    }
}
