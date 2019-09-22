/*
 * Copyright (c) 2019. K.Misaki
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

package com.mickey305.foundation.v4.lang.math.handler;

import com.mickey305.foundation.v3.compat.util.Function;
import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v4.lang.math.Matrix;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import com.mickey305.foundation.v4.lang.math.operator.IOperationFactory;

import java.util.Arrays;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public abstract class AbstractMatrixHandler<
    M1 extends Matrix<T1>, T1 extends Number, M2 extends Matrix<T2>, T2 extends Number> {
  private IOperationFactory<T2> OPF;
  private IElementInitializer<T2> EI;
  private M1 orgMatrix;
  private M2 tmpMatrix;
  private M2 resultMatrix;
  
  public AbstractMatrixHandler<M1, T1, M2, T2> setOperator(IOperationFactory<T2> op) {
    this.OPF = op;
    return this;
  }
  
  public AbstractMatrixHandler<M1, T1, M2, T2> setInitializer(IElementInitializer<T2> ini) {
    this.EI = ini;
    return this;
  }
  
  /**
   * 準備メソッド
   * @param orgMatrix 計算対象の行列オブジェクト
   */
  public void prepare(M1 orgMatrix) {
    if (orgMatrix == null) return;
    
    this.orgMatrix = orgMatrix;
    
    if (OPF == null) return;
    if (EI == null) return;
    
    if (IS_DEBUG_MODE) Log.d("bef:prepare" + Arrays.deepToString(orgMatrix.getTable()));
    
    this.tmpMatrix = this.createTemporaryMatrix(orgMatrix, OPF, EI);
  
    if (IS_DEBUG_MODE) Log.d("aft:prepare" + Arrays.deepToString(this.tmpMatrix.getTable()));
  }
  
  /**
   * 計算処理実行メソッド
   * @param function 計算処理実装ファンクションオブジェクト
   */
  public void execute(Function<M2, M2> function) {
    if (function == null) return;
    if (tmpMatrix == null) return;
    
    this.resultMatrix = function.apply(tmpMatrix);
    
    if (IS_DEBUG_MODE) Log.d("aft:execute" + Arrays.deepToString(this.resultMatrix.getTable()));
  }
  
  /**
   * 後処理実行メソッド
   * @return 計算結果行列オブジェクト
   */
  public M1 finalizer() {
    if (orgMatrix    == null) throw new UnsupportedOperationException();
    if (resultMatrix == null) throw new UnsupportedOperationException();
    
    M1 res = this.createResultMatrix(orgMatrix, resultMatrix);
  
    if (IS_DEBUG_MODE) Log.d("aft:finalizer" + Arrays.deepToString(res.getTable()));
    
    return res;
  }
  
  /**
   * 作業用行列オブジェクト生成処理
   * @param orgMatrix 計算対象の行列オブジェクト
   * @param op 計算時に使用する初期化オブジェクト
   * @param ini 計算時に使用するオペレーションファクトリーオブジェクト
   * @return 作業用行列オブジェクト
   */
  protected abstract M2 createTemporaryMatrix(M1 orgMatrix, IOperationFactory<T2> op, IElementInitializer<T2> ini);
  
  /**
   * 計算結果格納行列オブジェクト生成処理
   * @param orgMatrix 計算対象の行列オブジェクト
   * @param resultMatrix 計算結果作業用行列オブジェクト
   * @return 計算結果格納行列オブジェクト
   */
  protected abstract M1 createResultMatrix(M1 orgMatrix, M2 resultMatrix);
}
