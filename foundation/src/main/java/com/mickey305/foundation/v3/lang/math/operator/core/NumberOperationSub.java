package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;
import com.mickey305.foundation.v3.lang.math.operator.AbstractNumberOperation;

public class NumberOperationSub extends AbstractNumberOperation<Number> {
    public NumberOperationSub() {
        super();
    }

    public NumberOperationSub(BinaryFunction<Number, Number, Number> extension) {
        super(extension);
    }

    @Override
    public Number operationDefault(Number l, Number r) {
        final Class<?> targetClazz = l.getClass();
        if (targetClazz.equals(Integer.class))
            return l.intValue()    - r.intValue();

        if (targetClazz.equals(Long.class))
            return l.longValue()   - r.longValue();

        if (targetClazz.equals(Float.class))
            return l.floatValue()  - r.floatValue();

        if (targetClazz.equals(Double.class))
            return l.doubleValue() - r.doubleValue();

        if (targetClazz.equals(Short.class))
            return l.shortValue()  - r.shortValue();

        if (targetClazz.equals(Byte.class))
            return l.byteValue()   - r.byteValue();

        return null;
    }
}
