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
