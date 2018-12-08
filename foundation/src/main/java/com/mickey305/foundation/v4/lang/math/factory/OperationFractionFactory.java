package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import org.apache.commons.math3.fraction.Fraction;

import java.util.Objects;

public class OperationFractionFactory extends AbstractOperationFactory<Fraction> {
  private OperationFractionFactory() {
  }
  
  private static final class OperationFractionFactoryHolder {
    private static OperationFractionFactory Instance = new OperationFractionFactory();
  }
  
  public static OperationFractionFactory getInstance() {
    return OperationFractionFactoryHolder.Instance;
  }
  
  @Override
  public AbstractNumberOperation<Fraction, Fraction> add() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return l.add(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Fraction, Fraction> sub() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return l.subtract(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Fraction, Fraction> multi() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return l.multiply(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Fraction, Fraction> div() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return l.divide(r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Fraction, Fraction> max() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return (l.compareTo(r) > 0) ? l : r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Fraction, Fraction> min() {
    return new AbstractNumberOperation<Fraction, Fraction>() {
      @Override
      protected Fraction operationDefault(Fraction l, Fraction r) {
        return (l.compareTo(r) < 0) ? l : r;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Fraction, Boolean> eq() {
    return new AbstractNumberOperation<Fraction, Boolean>() {
      @Override
      protected Boolean operationDefault(Fraction l, Fraction r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Fraction, Boolean> lt() {
    return new AbstractNumberOperation<Fraction, Boolean>() {
      @Override
      protected Boolean operationDefault(Fraction l, Fraction r) {
        return l.compareTo(r) < 0;
      }
    };
  }
  
  @Override
  public AbstractNumberOperation<Fraction, Boolean> gt() {
    return new AbstractNumberOperation<Fraction, Boolean>() {
      @Override
      protected Boolean operationDefault(Fraction l, Fraction r) {
        return l.compareTo(r) > 0;
      }
    };
  }
}
