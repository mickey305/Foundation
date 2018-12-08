package com.mickey305.foundation.v4.lang.math;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public class SymmetricTransPositionGroup<E extends Number> extends SymmetricCycleGroup<E> {
  public static final int SYMMETRIC_GROUP_ID = 2;
  private static final long serialVersionUID = 7023764951081127516L;
  
  protected SymmetricTransPositionGroup(E[][] initialTable, IElementInitializer<E> initializer,
                                        Map<Operator, AbstractNumberOperation<E, E>> op,
                                        Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(initialTable, initializer, op, rop);
    if (!this.checkTransPosition())
      throw new IllegalArgumentException();
  }
  
  protected SymmetricTransPositionGroup(SymmetricTransPositionGroup<E> table) {
    super(table);
    if (!this.checkTransPosition())
      throw new IllegalArgumentException();
  }
  
  protected SymmetricTransPositionGroup(List<Pair<E, E>> list, IElementInitializer<E> initializer,
                                        Map<Operator, AbstractNumberOperation<E, E>> op,
                                        Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(list, initializer, op, rop);
    if (!this.checkTransPosition())
      throw new IllegalArgumentException();
  }
  
  protected SymmetricTransPositionGroup(Pair<E, E> row, IElementInitializer<E> initializer,
                                        Map<Operator, AbstractNumberOperation<E, E>> op,
                                        Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    this(convert(row, initializer), initializer, op, rop);
  }
  
  private static <E extends Number> E[][] convert(Pair<E, E> row, IElementInitializer<E> initializer) {
    final E[] elm1 = initializer.array(2);
    elm1[0] = row.getLeft();
    elm1[1] = row.getRight();
    final E[] elm2 = initializer.array(2);
    elm2[0] = row.getRight();
    elm2[1] = row.getLeft();
    final E[][] elms = initializer.table(2, 2);
    elms[0] = elm1;
    elms[1] = elm2;
    return elms;
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
