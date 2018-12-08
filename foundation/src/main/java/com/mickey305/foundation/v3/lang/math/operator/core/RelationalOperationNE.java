package com.mickey305.foundation.v3.lang.math.operator.core;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;

@Deprecated
public class RelationalOperationNE extends RelationalOperationEQ {
  public RelationalOperationNE() {
    super();
  }

  public RelationalOperationNE(BinaryFunction<Number, Number, Boolean> extension) {
    super(extension);
  }

  @Override
  protected Boolean operationDefault(Number l, Number r) {
    final Boolean status = super.operationDefault(l, r);
    return (status == null)
        ? null
        : !status;
  }
}
