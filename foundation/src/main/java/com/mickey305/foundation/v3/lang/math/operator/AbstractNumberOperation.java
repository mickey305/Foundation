package com.mickey305.foundation.v3.lang.math.operator;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import java.math.BigInteger;

public abstract class AbstractNumberOperation<R> implements
        BinaryFunction<Number, Number, R>,
        OperationExtensions<Number, Number, R>
{
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

    @Override
    public void setExtension(BinaryFunction<Number, Number, R> extension) {
        this.extension = extension;
    }

    @Override
    public R apply(Number l, Number r) {
        R result = null;

        // extension operation invoke
        if (this.getExtension() != null)
            result = this.getExtension().apply(l, r);

        if(result != null)
            return result;

        // default operation invoke
        result = this.operationDefault(l, r);

        if(result != null)
            return result;

        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param data
     * @return
     */
    protected BigInteger convertToBigInteger(Number data) {
        if(data.getClass().equals(Integer.class))
            return new BigInteger(data.intValue() + "");

        if(data.getClass().equals(Long.class))
            return new BigInteger(data.longValue() + "");

        if(data.getClass().equals(Float.class))
            return new BigInteger(data.floatValue() + "");

        if(data.getClass().equals(Double.class))
            return new BigInteger(data.doubleValue() + "");

        if(data.getClass().equals(Short.class))
            return new BigInteger(data.shortValue() + "");

        if(data.getClass().equals(Byte.class))
            return new BigInteger(data.byteValue() + "");

        if(data.getClass().equals(Byte.class))
            return new BigInteger(data.byteValue() + "");

        if(data.getClass().equals(BigInteger.class))
            return (BigInteger) data;

        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param data
     * @return
     */
    protected Fraction convertToFraction(Number data) {
        if(data.getClass().equals(Integer.class))
            return new Fraction(data.intValue());

        if(data.getClass().equals(Long.class))
            return new Fraction(data.longValue());

        if(data.getClass().equals(Float.class))
            return new Fraction(data.floatValue());

        if(data.getClass().equals(Double.class))
            return new Fraction(data.doubleValue());

        if(data.getClass().equals(Short.class))
            return new Fraction(data.shortValue());

        if(data.getClass().equals(Byte.class))
            return new Fraction(data.byteValue());

        if(data.getClass().equals(Fraction.class))
            return (Fraction) data;

        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param data
     * @return
     */
    protected BigFraction convertToBigFraction(Number data) {
        if(data.getClass().equals(Integer.class))
            return new BigFraction(data.intValue());

        if(data.getClass().equals(Long.class))
            return new BigFraction(data.longValue());

        if(data.getClass().equals(Float.class))
            return new BigFraction(data.floatValue());

        if(data.getClass().equals(Double.class))
            return new BigFraction(data.doubleValue());

        if(data.getClass().equals(Short.class))
            return new BigFraction(data.shortValue());

        if(data.getClass().equals(Byte.class))
            return new BigFraction(data.byteValue());

        if(data.getClass().equals(BigInteger.class))
            return new BigFraction((BigInteger) data);

        if(data.getClass().equals(Fraction.class))
            return new BigFraction(
                    ((Fraction) data).getNumerator(), ((Fraction) data).getDenominator());

        if(data.getClass().equals(BigFraction.class))
            return (BigFraction) data;

        throw new UnsupportedOperationException();
    }

    protected abstract R operationDefault(Number l, Number r);
}
