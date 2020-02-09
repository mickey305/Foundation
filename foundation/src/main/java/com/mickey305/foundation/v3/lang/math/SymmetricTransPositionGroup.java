/*
 * Copyright (c) 2017 - 2020 K.Misaki
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

package com.mickey305.foundation.v3.lang.math;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Deprecated
public class SymmetricTransPositionGroup extends SymmetricCycleGroup {
  public static final int SYMMETRIC_GROUP_ID = 2;
  private static final long serialVersionUID = 2183760195771599112L;
  
  protected SymmetricTransPositionGroup(Number[][] initialTable) {
    super(initialTable);
    if (!this.checkTransPosition())
      throw new IllegalArgumentException();
  }
  
  protected SymmetricTransPositionGroup(SymmetricTransPositionGroup table) {
    super(table);
    if (!this.checkTransPosition())
      throw new IllegalArgumentException();
  }
  
  protected SymmetricTransPositionGroup(List<Pair<Number, Number>> list) {
    super(list);
    if (!this.checkTransPosition())
      throw new IllegalArgumentException();
  }
  
  protected SymmetricTransPositionGroup(Pair<Number, Number> row) {
    this(new Number[][]{{row.getLeft(), row.getRight()}, {row.getRight(), row.getLeft()}});
  }
  
  /**
   * 互換データチェック
   *
   * @return 判定結果
   */
  private boolean checkTransPosition() {
    return this.getDataSet().size() == 2;
  }
}
