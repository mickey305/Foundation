package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

@Deprecated
public class RelationalOperationLE extends RelationalOperationEQ {
  private final RelationalOperationLT opLT;
  
  public RelationalOperationLE() {
    super();
    opLT = new RelationalOperationLT();
  }
  
  public RelationalOperationLE(BinaryFunction<Number, Number, Boolean> extension) {
    super(extension);
    opLT = new RelationalOperationLT();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected Boolean operationDefault(Number l, Number r) {
    final Boolean status1 = super.operationDefault(l, r);
    final Boolean status2 = opLT.operationDefault(l, r);
    return (status1 == null || status2 == null)
        ? null
        : status1 || status2;
  }
}
