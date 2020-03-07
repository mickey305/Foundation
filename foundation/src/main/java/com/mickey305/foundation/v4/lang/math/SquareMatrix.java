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

package com.mickey305.foundation.v4.lang.math;

import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v3.util.collections.Permutation;
import com.mickey305.foundation.v4.lang.math.factory.ElementInitializerFactory;
import com.mickey305.foundation.v4.lang.math.factory.OperationFactory;
import com.mickey305.foundation.v4.lang.math.operator.IOperationFactory;
import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;

import java.util.Collections;
import java.util.Map;

import com.mickey305.foundation.v3.compat.exception.wrap.UnsupportedOperationException;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

/**
 * 正方行列演算用のクラスです
 * @param <E> 行列の要素の総称型です
 */
public class SquareMatrix<E extends Number> extends Matrix<E> {
  private static final long serialVersionUID = -826829608095297048L;
  
  public SquareMatrix(int size, IElementInitializer<E> initializer,
                      Map<Operator, AbstractNumberOperation<E, E>> op,
                      Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(size, size, initializer, op, rop);
  }
  
  public SquareMatrix(E[][] initialTable, IElementInitializer<E> initializer,
                      Map<Operator, AbstractNumberOperation<E, E>> op,
                      Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(initialTable, initializer, op, rop);
    if (!super.isSquare())
      throw new UnsupportedOperationException("isSquare-check failed.");
  }
  
  public SquareMatrix(SquareMatrix<E> matrix) {
    super(matrix);
  }
  
  /**
   * べき乗処理
   *
   * @param index 指数（自然数）
   * @return 演算結果行列
   */
  public SquareMatrix<E> exp(int index) {
    if (index == 0)
      return this.createIdentityMatrix();
    
    return new SquareMatrix<>(super.exp(index).getTable(), this.getInitializer(), this.getOp(), this.getRop());
  }
  
  /**
   * 行列サイズ取得処理
   *
   * @return 行列サイズ
   */
  public int getSize() {
    return super.getRowSize();
  }
  
  /**
   * 単位行列取得メソッド
   *
   * @return 単位行列
   */
  public SquareMatrix<E> createIdentityMatrix() {
    final IElementInitializer<E> INI = this.getInitializer();
    final SquareMatrix<E> R = new SquareMatrix<>(this);
    for (int i = 0; i < this.getRowSize(); i++) {
      for (int j = 0; j < this.getColumnSize(); j++) {
        R.putCell(i, j, INI.zero());
        if (i == j)
          R.putCell(i, j, INI.one());
      }
    }
    return R;
  }
  
  /**
   * 逆行列取得メソッド
   *
   * @return 逆行列
   */
  public SquareMatrix<E> createInverseMatrix() {
    if (IS_DEBUG_MODE) Log.d("inverse matrix creating...");
    if (!this.isRegular())
      throw new UnsupportedOperationException("isRegular-check failed.");
    
    final Matrix<E> extMatrix = this.horizontalBind(this.createIdentityMatrix());
    
    // calc impl
    this.calcInverseMatrixPhase1(extMatrix);
    this.calcInverseMatrixPhase2(extMatrix);
    this.calcInverseMatrixPhase3(extMatrix);
    
    final SquareMatrix<E> R = new SquareMatrix<>(this);
    for (int i = 0; i < R.getRowSize(); i++)
      for (int j = 0; j < R.getColumnSize(); j++)
        R.putCell(i, j, extMatrix.getCell(i, this.getColumnSize() + j));
    return R;
  }
  
  /**
   * 正則行列判定メソッド
   *
   * @return 判定結果
   */
  public boolean isRegular() {
    return this.getRop(RelationalOperator.NE).apply(this.determinant(), this.getInitializer().zero());
  }
  
  /**
   * 行列式取得メソッド
   *
   * @return 計算結果
   */
  public E determinant() {
    final IElementInitializer<E> INI = this.getInitializer();
    E R = INI.zero();
    final Integer[] rowIndexes = new Integer[this.getColumnSize()];
    for (int i = 0; i < rowIndexes.length; i++)
      rowIndexes[i] = i;
    final Permutation<Integer> RP = new Permutation<>(rowIndexes);
  
    IOperationFactory<Integer> factory = OperationFactory.<Integer>getFactory();
    IElementInitializer<Integer> ini = ElementInitializerFactory.<Integer>getFactory();
    do {
      final Integer[][] tbl = new Integer[][]{rowIndexes, RP.getElements()};
      final SymmetricPermutationGroup<Integer> PG = new SymmetricPermutationGroup<>(
          tbl, ini,
          Collections.<Operator, AbstractNumberOperation<Integer, Integer>>emptyMap(),
          Collections.<RelationalOperator, AbstractNumberOperation<Integer, Boolean>>emptyMap());
      
      OperatorRegistryUtil.registerAll(PG.getOp(), PG.getRop(), factory);
      
      E multiResult = INI.one();
      final int sgn = PG.sgn();
      for (int j = 0; j < PG.getColumnSize(); j++) {
        final E data = this.getCell(j, PG.getPairOf(j));
        multiResult = this.getOp(Operator.Multi).apply(data, multiResult);
      }
      multiResult = this.getOp(Operator.Multi).apply(multiResult, INI.convertFrom(sgn));
      
      R = this.getOp(Operator.Add).apply(multiResult, R);
    } while (RP.next());
    
    return R;
  }
  
  /**
   * トレース取得メソッド
   *
   * @return 計算結果
   */
  public E trace() {
    E result = this.getInitializer().zero();
    for (int i = 0; i < this.getSize(); i++)
      result = this.getOp(Operator.Add).apply(this.getCell(i, i), result);
    
    return result;
  }
  
  /**
   * matrix(row=i,column=i) &lt;==&gt; NE ZERO transformation
   * 掃き出し法の第１段階実装メソッドです。同じ行/列番号上の要素がゼロ以外になるように行基本変形を行います。
   * @param M 拡大係数行列
   */
  protected void calcInverseMatrixPhase1(Matrix<E> M) {
    final IElementInitializer<E> INI = this.getInitializer();
  
    for (int i = 0; i < this.getRowSize(); i++) {
      if (this.getRop(RelationalOperator.Eq).apply(M.getCell(i, i), INI.zero())) {
        final E[] vertical = M.getColumn(i);
        for (int j = 0; j < vertical.length; j++) {
          if (j != i && this.getRop(RelationalOperator.NE).apply(vertical[j], INI.zero())) {
            M.multiAndAddRow(INI.one(), j, i);
            break;
          }
        }
      }
    }
    
  }
  
  /**
   * matrix(row=i,column=j), i != j &lt;==&gt; EQ ZERO transformation
   * 掃き出し法の第２段階実装メソッドです。異なる行/列番号上の要素がゼロになるように行基本変形を行います。
   * @param M 拡大係数行列
   */
  protected void calcInverseMatrixPhase2(Matrix<E> M) {
    final IElementInitializer<E> INI = this.getInitializer();
  
    for (int j = 0; j < this.getColumnSize(); j++) {
      for (int i = 0; i < this.getRowSize(); i++) {
        final E targetCell = M.getCell(i, j);
        final E[] vertical = M.getColumn(j);
        if (j != i && this.getRop(RelationalOperator.NE).apply(vertical[j], INI.zero())) {
          int kk = 0;
          int max = Integer.MIN_VALUE;
          for (int k = 0; k < vertical.length; k++) {
            int cntZero = 0;
            final E[] horizontal = M.getRow(k);
            for (int l = 0; l < this.getColumnSize(); l++) {
              if (this.getRop(RelationalOperator.Eq).apply(horizontal[l], INI.zero())) {
                cntZero++;
              }
            }
          
            if (i != k && this.getRop(RelationalOperator.NE).apply(vertical[k], INI.zero())) {
              if (max < cntZero) {
                max = cntZero;
                kk = k;
              }
            }
          }
          E scalar = this.getOp(Operator.Div).apply(targetCell, vertical[kk]);
          scalar = this.getOp(Operator.Multi).apply(scalar, INI.minusOne());
          M.multiAndAddRow(scalar, kk, i);
        }
      }
    }
    
  }
  
  /**
   * matrix(row=i,column=i) &lt;==&gt; EQ ONE transformation
   * 掃き出し法の第３段階実装メソッドです。同じ行/列番号上の要素が１になるように行基本変形を行います。
   * このメソッドの実装が完了した段階で、horizontalExtensionMatrixの左部分が単位行列になります。
   * @param M 拡大係数行列
   */
  protected void calcInverseMatrixPhase3(Matrix<E> M) {
    final IElementInitializer<E> INI = this.getInitializer();
  
    for (int i = 0; i < this.getRowSize(); i++) {
      if (this.getRop(RelationalOperator.NE).apply(
          M.getCell(i, i),
          INI.one())) {
        
        M.multiRow(this.getOp(Operator.Div).apply(
            INI.one(),
            M.getCell(i, i)), i);
      }
    }
  }
}
