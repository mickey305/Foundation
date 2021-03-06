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
public class SymmetricCycleGroup extends SymmetricPermutationGroup {
  public static final int SYMMETRIC_GROUP_ID = 1;
  private static final long serialVersionUID = 4305739241750291008L;
  
  protected SymmetricCycleGroup(Number[][] initialTable) {
    super(initialTable);
    if (!this.checkCycle())
      throw new IllegalArgumentException("symmetricCycleGroup-check failed.");
  }
  
  protected SymmetricCycleGroup(SymmetricCycleGroup table) {
    super(table);
    if (!this.checkCycle())
      throw new IllegalArgumentException("symmetricCycleGroup-check failed.");
  }
  
  protected SymmetricCycleGroup(List<Pair<Number, Number>> list) {
    super(list);
    if (!this.checkCycle())
      throw new IllegalArgumentException("symmetricCycleGroup-check failed.");
  }
  
  /**
   * 巡回置換データチェック
   *
   * @return 判定結果
   */
  private boolean checkCycle() {
    final Number tpData = this.getRow(0)[0];
    int cnt = 0;
    Number btmData = tpData;
    do {
      btmData = this.getPairOf(btmData);
      cnt++;
    } while (!btmData.equals(tpData));
    return cnt == this.getDataSet().size();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public SymmetricCycleGroup compact() {
    return new SymmetricCycleGroup(super.compact().getTable());
  }
}
