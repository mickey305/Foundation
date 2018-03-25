package com.mickey305.foundation.v3.lang.math.operator;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

public abstract class AbstractNumberOperation<R> implements BinaryFunction<Number, Number, R> {
    private BinaryFunction<Number, Number, R> extension;

    public AbstractNumberOperation() {
        this(null);
    }

    public AbstractNumberOperation(BinaryFunction<Number, Number, R> extension) {
        this.setExtension(extension);
    }

    public BinaryFunction<Number, Number, R> getExtension() {
        return extension;
    }

    public void setExtension(BinaryFunction<Number, Number, R> extension) {
        this.extension = extension;
    }

    @Override
    public R apply(Number l, Number r) {
        R result;

        // default operation invoke
        result = this.operationDefault(l, r);

        if(result != null)
            return result;

        // extension operation invoke
        if (this.getExtension() != null)
            result = this.getExtension().apply(l, r);

        if(result != null)
            return result;

        throw new UnsupportedOperationException();
    }

    protected abstract R operationDefault(Number l, Number r);
}
