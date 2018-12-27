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
