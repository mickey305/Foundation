package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;
import com.mickey305.foundation.v3.lang.math.operator.AbstractNumberOperation;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import java.math.BigInteger;

@Deprecated
public class NumberOperationAdd extends AbstractNumberOperation<Number> {
    public NumberOperationAdd() {
        super();
    }

    public NumberOperationAdd(BinaryFunction<Number, Number, Number> extension) {
        super(extension);
    }

    @Override
    public Number operationDefault(Number l, Number r) {
        final Class<?> targetClazz = l.getClass();
        if (targetClazz.equals(Integer.class))
            return l.intValue()    + r.intValue();

        if (targetClazz.equals(Long.class))
            return l.longValue()   + r.longValue();

        if (targetClazz.equals(Float.class))
            return l.floatValue()  + r.floatValue();

        if (targetClazz.equals(Double.class))
            return l.doubleValue() + r.doubleValue();

        if (targetClazz.equals(Short.class))
            return l.shortValue()  + r.shortValue();

        if (targetClazz.equals(Byte.class))
            return l.byteValue()   + r.byteValue();

        if (targetClazz.equals(BigInteger.class))
            return ((BigInteger) l).add(super.convertToBigInteger(r));

        if (targetClazz.equals(Fraction.class))
            return ((Fraction) l).add(super.convertToFraction(r));

        if (targetClazz.equals(BigFraction.class))
            return ((BigFraction) l).add(super.convertToBigFraction(r));

        return null;
    }
}
