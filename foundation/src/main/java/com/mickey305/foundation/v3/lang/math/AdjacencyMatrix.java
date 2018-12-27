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

package com.mickey305.foundation.v3.lang.math;

import java.util.Arrays;

@Deprecated
public class AdjacencyMatrix extends SquareMatrix {
  private static final long serialVersionUID = -8268567830873490892L;
  
  protected AdjacencyMatrix(int size) {
    super(size);
  }
  
  protected AdjacencyMatrix(Number[][] initialTable) {
    super(initialTable);
  }
  
  protected AdjacencyMatrix(AdjacencyMatrix matrix) {
    super(matrix);
  }
  
  /**
   * インスタンス生成メソッド
   *
   * @param size 行・列サイズ
   * @return インスタンス
   */
  public static AdjacencyMatrix of(int size) {
    return new AdjacencyMatrix(size);
  }
  
  /**
   * インスタンス生成メソッド
   *
   * @param initialTable 初期化テーブル
   * @return インスタンス
   */
  public static AdjacencyMatrix of(Number[][] initialTable) {
    return new AdjacencyMatrix(initialTable);
  }
  
  /**
   * インスタンス生成メソッド
   *
   * @param matrix 初期化インスタンス
   * @return インスタンス
   */
  public static AdjacencyMatrix of(AdjacencyMatrix matrix) {
    return new AdjacencyMatrix(matrix);
  }

//    // todo
//    /**
//     * 木判定メソッド
//     * @param matrix 判定対象の隣接行列
//     * @return 判定結果
//     */
//    public static boolean isTree(AdjacencyMatrix matrix) {
//        return false;
//    }
  
  /**
   * 有向グラフ判定メソッド
   *
   * @param matrix 判定対象の隣接行列
   * @return 判定結果
   */
  public static boolean isDirectedGraph(AdjacencyMatrix matrix) {
    for (int i = 0; i < matrix.getSize(); i++)
      if (!Arrays.equals(matrix.getRow(i), matrix.getColumn(i)))
        return false;
    
    return true;
  }
}
