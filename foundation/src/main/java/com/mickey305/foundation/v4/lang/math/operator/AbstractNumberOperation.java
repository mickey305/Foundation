package com.mickey305.foundation.v4.lang.math.operator;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

public abstract class AbstractNumberOperation<E extends Number, R> implements BinaryFunction<E, E, R> {
  public AbstractNumberOperation() {
  }
  
  @Override
  public R apply(E l, E r) {
    final R result = this.operationDefault(l, r);
    
    if (result != null) return result;
    
    // throwing exception
    throw new UnsupportedOperationException();
  }
  
  /**
   * デフォルト演算・実装メソッド
   *
   * @param l 演算項１
   * @param r 演算項２
   * @return 演算結果
   */
  protected abstract R operationDefault(E l, E r);
}
