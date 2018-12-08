package com.mickey305.foundation.v4.lang.math;

import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;

import java.util.Arrays;
import java.util.Map;

public class AdjacencyMatrix<E extends Number> extends SquareMatrix<E> {
  private static final long serialVersionUID = 7454505265628784889L;
  
  public AdjacencyMatrix(int size, IElementInitializer<E> initializer,
                         Map<Operator, AbstractNumberOperation<E, E>> op,
                         Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(size, initializer, op, rop);
  }
  
  public AdjacencyMatrix(E[][] initialTable, IElementInitializer<E> initializer,
                         Map<Operator, AbstractNumberOperation<E, E>> op,
                         Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(initialTable, initializer, op, rop);
  }
  
  public AdjacencyMatrix(AdjacencyMatrix<E> matrix) {
    super(matrix);
  }

//    // todo
//    /**
//     * 木判定メソッド
//     * @return 判定結果
//     */
//    public boolean isTree() {
//        return false;
//    }
  
  /**
   * 有向グラフ判定メソッド
   *
   * @return 判定結果
   */
  public boolean isDirectedGraph() {
    for (int i = 0; i < this.getSize(); i++)
      if (!Arrays.equals(this.getRow(i), this.getColumn(i)))
        return false;
    
    return true;
  }
}
