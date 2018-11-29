package com.mickey305.foundation.v4.lang.math;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public class SymmetricCycleGroup<E extends Number> extends SymmetricPermutationGroup<E> {
  public static final int SYMMETRIC_GROUP_ID = 1;
  private static final long serialVersionUID = -2400659284024880159L;
  
  protected SymmetricCycleGroup(E[][] initialTable, IElementInitializer<E> initializer,
                                Map<Operator, AbstractNumberOperation<E, E>> op,
                                Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(initialTable, initializer, op, rop);
    if (!this.checkCycle())
      throw new IllegalArgumentException();
    }

  protected SymmetricCycleGroup(SymmetricCycleGroup<E> table) {
    super(table);
    if (!this.checkCycle())
      throw new IllegalArgumentException();
  }

  protected SymmetricCycleGroup(List<Pair<E, E>> list, IElementInitializer<E> initializer,
                                Map<Operator, AbstractNumberOperation<E, E>> op,
                                Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(list, initializer, op, rop);
    if (!this.checkCycle())
      throw new IllegalArgumentException();
  }

  /**
   * 巡回置換データチェック
   * @return 判定結果
   */
  private boolean checkCycle() {
    final E tpData = this.getRow(0)[0];
    int cnt = 0;
    E btmData = tpData;
    do {
      btmData = this.getPairOf(btmData);
      cnt++;
    } while (!btmData.equals(tpData));
    return cnt == this.getDataSet().size();
  }

  @Override public SymmetricCycleGroup<E> compact() {
    return new SymmetricCycleGroup<>(super.compact().getTable(), this.getInitializer(), this.getOp(), this.getRop());
  }
}
