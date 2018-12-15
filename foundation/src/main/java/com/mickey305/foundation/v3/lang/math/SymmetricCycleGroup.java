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
      throw new IllegalArgumentException();
  }
  
  protected SymmetricCycleGroup(SymmetricCycleGroup table) {
    super(table);
    if (!this.checkCycle())
      throw new IllegalArgumentException();
  }
  
  protected SymmetricCycleGroup(List<Pair<Number, Number>> list) {
    super(list);
    if (!this.checkCycle())
      throw new IllegalArgumentException();
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
  
  @Override
  public SymmetricCycleGroup compact() {
    return new SymmetricCycleGroup(super.compact().getTable());
  }
}
