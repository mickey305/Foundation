/*
 * Copyright (c) 2018. K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
