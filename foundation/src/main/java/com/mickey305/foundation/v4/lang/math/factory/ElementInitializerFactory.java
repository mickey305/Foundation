package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractElementInitializer;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ElementInitializerFactory {
  
  private ElementInitializerFactory() { }
  
  public static IElementInitializer<Integer> intIni() {
    return new AbstractElementInitializer<Integer>() {
      @Override
      public Integer zero() {
        return 0;
      }
  
      @Override
      public Integer one() {
        return 1;
      }
  
      @Override
      public Integer minusOne() {
        return -1;
      }
  
      @Override
      public Integer convertFrom(Number n) throws RuntimeException {
        // unsafe
        return n.intValue();
      }
    };
  }
  
  public static IElementInitializer<Long> longIni() {
    return new AbstractElementInitializer<Long>() {
      @Override
      public Long zero() {
        return 0L;
      }
  
      @Override
      public Long one() {
        return 1L;
      }
  
      @Override
      public Long minusOne() {
        return -1L;
      }
  
      @Override
      public Long convertFrom(Number n) throws RuntimeException {
        // unsafe
        return n.longValue();
      }
    };
  }
  
  public static IElementInitializer<Short> shortIni() {
    return new AbstractElementInitializer<Short>() {
      @Override
      public Short zero() {
        return (short) 0;
      }
  
      @Override
      public Short one() {
        return (short) 1;
      }
  
      @Override
      public Short minusOne() {
        return (short) -1;
      }
  
      @Override
      public Short convertFrom(Number n) throws RuntimeException {
        // unsafe
        return n.shortValue();
      }
    };
  }
  
  public static IElementInitializer<Byte> byteIni() {
    return new AbstractElementInitializer<Byte>() {
      @Override
      public Byte zero() {
        return (byte) 0;
      }
  
      @Override
      public Byte one() {
        return (byte) 1;
      }
  
      @Override
      public Byte minusOne() {
        return (byte) -1;
      }
  
      @Override
      public Byte convertFrom(Number n) throws RuntimeException {
        // unsafe
        return n.byteValue();
      }
    };
  }
  
  public static IElementInitializer<Float> floatIni() {
    return new AbstractElementInitializer<Float>() {
      @Override
      public Float zero() {
        return 0.0f;
      }
  
      @Override
      public Float one() {
        return 1.0f;
      }
  
      @Override
      public Float minusOne() {
        return -1.0f;
      }
  
      @Override
      public Float convertFrom(Number n) throws RuntimeException {
        // unsafe
        return n.floatValue();
      }
    };
  }
  
  public static IElementInitializer<Double> doubleIni() {
    return new AbstractElementInitializer<Double>() {
      @Override
      public Double zero() {
        return 0.0d;
      }
  
      @Override
      public Double one() {
        return 1.0d;
      }
  
      @Override
      public Double minusOne() {
        return -1.0d;
      }
  
      @Override
      public Double convertFrom(Number n) throws RuntimeException {
        // unsafe
        return n.doubleValue();
      }
    };
  }
  
  public static IElementInitializer<BigInteger> bigIntIni() {
    return new AbstractElementInitializer<BigInteger>() {
      @Override
      public BigInteger zero() {
        return BigInteger.ZERO;
      }
  
      @Override
      public BigInteger one() {
        return BigInteger.ONE;
      }
  
      @Override
      public BigInteger minusOne() {
        return BigInteger.valueOf(-1L);
      }
  
      @Override
      public BigInteger convertFrom(Number n) throws RuntimeException {
        if (n instanceof BigInteger)  return (BigInteger) n;
        if (n instanceof Integer)     return new BigInteger(String.valueOf(n.intValue()));
        if (n instanceof Long)        return new BigInteger(String.valueOf(n.longValue()));
        if (n instanceof Short)       return new BigInteger(String.valueOf(n.shortValue()));
        if (n instanceof Byte)        return new BigInteger(String.valueOf(n.byteValue()));
        if (n instanceof Float)       return new BigInteger(String.valueOf(n.floatValue()));
        if (n instanceof Double)      return new BigInteger(String.valueOf(n.doubleValue()));
  
        // unsafe
        if (n instanceof BigDecimal)  return ((BigDecimal) n).toBigInteger();
        if (n instanceof Fraction)    return new BigInteger(String.valueOf(n.doubleValue()));
        if (n instanceof BigFraction) return new BigInteger(String.valueOf(n.doubleValue()));
        
        // throwing exception
        throw new UnsupportedOperationException();
      }
    };
  }
  
  public static IElementInitializer<BigDecimal> bigDcmlIni() {
    return new AbstractElementInitializer<BigDecimal>() {
      @Override
      public BigDecimal zero() {
        return BigDecimal.ZERO;
      }
  
      @Override
      public BigDecimal one() {
        return BigDecimal.ONE;
      }
  
      @Override
      public BigDecimal minusOne() {
        return new BigDecimal("-1.0");
      }
  
      @Override
      public BigDecimal convertFrom(Number n) throws RuntimeException {
        if (n instanceof BigDecimal)  return (BigDecimal) n;
        if (n instanceof Integer)     return new BigDecimal(String.valueOf(n.intValue()));
        if (n instanceof Long)        return new BigDecimal(String.valueOf(n.longValue()));
        if (n instanceof Short)       return new BigDecimal(String.valueOf(n.shortValue()));
        if (n instanceof Byte)        return new BigDecimal(String.valueOf(n.byteValue()));
        if (n instanceof Float)       return new BigDecimal(String.valueOf(n.floatValue()));
        if (n instanceof Double)      return new BigDecimal(String.valueOf(n.doubleValue()));
        if (n instanceof BigInteger)  return new BigDecimal((BigInteger) n);
        
        // unsafe
        if (n instanceof Fraction)    return new BigDecimal(String.valueOf(n.doubleValue()));
        if (n instanceof BigFraction) return new BigDecimal(String.valueOf(n.doubleValue()));
  
        // throwing exception
        throw new UnsupportedOperationException();
      }
    };
  }
  
  public static IElementInitializer<Fraction> fractionIni() {
    return new AbstractElementInitializer<Fraction>() {
      @Override
      public Fraction zero() {
        return Fraction.ZERO;
      }
  
      @Override
      public Fraction one() {
        return Fraction.ONE;
      }
  
      @Override
      public Fraction minusOne() {
        return Fraction.MINUS_ONE;
      }
  
      @Override
      public Fraction convertFrom(Number n) throws RuntimeException {
        if (n instanceof Fraction)    return (Fraction) n;
        if (n instanceof Integer)     return new Fraction(n.intValue());
        if (n instanceof Long)        return new Fraction(n.longValue());
        if (n instanceof Short)       return new Fraction(n.shortValue());
        if (n instanceof Byte)        return new Fraction(n.byteValue());
        if (n instanceof Float)       return new Fraction(n.floatValue());
        if (n instanceof Double)      return new Fraction(n.doubleValue());
  
        // unsafe
        if (n instanceof BigFraction) {
          return Fraction.getReducedFraction(((BigFraction) n).getNumeratorAsInt(), ((BigFraction) n).getDenominatorAsInt());
        }
        if (n instanceof BigInteger)  return new Fraction(n.longValue());
        if (n instanceof BigDecimal)  return new Fraction(n.doubleValue());
  
        // throwing exception
        throw new UnsupportedOperationException();
      }
    };
  }
  
  public static IElementInitializer<BigFraction> bigFractionIni() {
    return new AbstractElementInitializer<BigFraction>() {
      @Override
      public BigFraction zero() {
        return BigFraction.ZERO;
      }
  
      @Override
      public BigFraction one() {
        return BigFraction.ONE;
      }
  
      @Override
      public BigFraction minusOne() {
        return BigFraction.MINUS_ONE;
      }
  
      @Override
      public BigFraction convertFrom(Number n) throws RuntimeException {
        if (n instanceof BigFraction) return (BigFraction) n;
        if (n instanceof Integer)     return new BigFraction(n.intValue());
        if (n instanceof Long)        return new BigFraction(n.longValue());
        if (n instanceof Short)       return new BigFraction(n.shortValue());
        if (n instanceof Byte)        return new BigFraction(n.byteValue());
        if (n instanceof Float)       return new BigFraction(n.floatValue());
        if (n instanceof Double)      return new BigFraction(n.doubleValue());
        if (n instanceof BigInteger)  return new BigFraction((BigInteger) n);
        if (n instanceof Fraction) {
          final Fraction fraction = (Fraction) n;
          return new BigFraction(fraction.getNumerator(), fraction.getDenominator());
        }
        if (n instanceof BigDecimal) {
          final BigDecimal decimal = (BigDecimal) n;
          final BigInteger ten = BigInteger.TEN;
          if (decimal.scale() >= 0) {
            return new BigFraction(decimal.unscaledValue(), ten.pow(decimal.scale()));
          } else {
            return new BigFraction(decimal.unscaledValue().multiply(ten.pow(-1 * decimal.scale())));
          }
        }
        
        // throwing exception
        throw new UnsupportedOperationException();
      }
    };
  }
}
