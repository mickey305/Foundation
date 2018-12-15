package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;

import java.util.Objects;

public class OperationByteFactory extends AbstractOperationFactory<Byte> {
  private OperationByteFactory() {
  }
  
  private static final class OperationByteFactoryHolder {
    private static OperationByteFactory Instance = new OperationByteFactory();
  }
  
  public static OperationByteFactory getInstance() {
    return OperationByteFactoryHolder.Instance;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> add() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) (l + r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> sub() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) (l - r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> multi() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) (l * r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> div() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) (l / r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> max() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) Math.max(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Byte> min() {
    return new AbstractNumberOperation<Byte, Byte>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Byte operationDefault(Byte l, Byte r) {
        return (byte) Math.min(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Boolean> eq() {
    return new AbstractNumberOperation<Byte, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Byte l, Byte r) {
        return Objects.equals(l, r);
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Boolean> lt() {
    return new AbstractNumberOperation<Byte, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Byte l, Byte r) {
        return l < r;
      }
    };
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractNumberOperation<Byte, Boolean> gt() {
    return new AbstractNumberOperation<Byte, Boolean>() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected Boolean operationDefault(Byte l, Byte r) {
        return l > r;
      }
    };
  }
}
