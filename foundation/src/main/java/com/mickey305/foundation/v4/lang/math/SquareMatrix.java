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

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

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
      throw new UnsupportedOperationException();
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
    final SquareMatrix<E> matrix = new SquareMatrix<>(this);
    for (int i = 0; i < this.getRowSize(); i++) {
      for (int j = 0; j < this.getColumnSize(); j++) {
        matrix.putCell(i, j, this.getInitializer().zero());
        if (i == j)
          matrix.putCell(i, j, this.getInitializer().one());
      }
    }
    return matrix;
  }
  
  /**
   * 逆行列取得メソッド
   *
   * @return 逆行列
   */
  public SquareMatrix<E> createInverseMatrix() {
    if (IS_DEBUG_MODE) Log.d("inverse matrix creating...");
    if (!this.isRegular())
      throw new UnsupportedOperationException();
    
    final Matrix<E> extMatrix = this.horizontalBind(this.createIdentityMatrix());
    
    // calc impl
    this.calcInverseMatrixPhase1(extMatrix);
    this.calcInverseMatrixPhase2(extMatrix);
    this.calcInverseMatrixPhase3(extMatrix);
    
    final SquareMatrix<E> result = new SquareMatrix<>(this);
    for (int i = 0; i < result.getRowSize(); i++)
      for (int j = 0; j < result.getColumnSize(); j++)
        result.putCell(i, j, extMatrix.getCell(i, this.getColumnSize() + j));
    return result;
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
    E result = this.getInitializer().zero();
    final Integer[] rowIndexes = new Integer[this.getColumnSize()];
    for (int i = 0; i < rowIndexes.length; i++)
      rowIndexes[i] = i;
    final Permutation<Integer> rowPermutation = new Permutation<>(rowIndexes);
  
    IOperationFactory<Integer> factory = OperationFactory.getFactory();
    IElementInitializer<Integer> initializer = ElementInitializerFactory.getFactory();
    do {
      final SymmetricPermutationGroup<Integer> permutationGroup = new SymmetricPermutationGroup<>(
          new Integer[][]{rowIndexes, rowPermutation.getElements()},
          initializer,
          Collections.<Operator, AbstractNumberOperation<Integer, Integer>>emptyMap(),
          Collections.<RelationalOperator, AbstractNumberOperation<Integer, Boolean>>emptyMap());
      permutationGroup.getOp().put(Operator.ADD, factory.add());
      permutationGroup.getOp().put(Operator.SUB, factory.sub());
      permutationGroup.getOp().put(Operator.MULTI, factory.multi());
      permutationGroup.getOp().put(Operator.DIV, factory.div());
      permutationGroup.getOp().put(Operator.MAX, factory.max());
      permutationGroup.getOp().put(Operator.MIN, factory.min());
      permutationGroup.getRop().put(RelationalOperator.EQ, factory.eq());
      permutationGroup.getRop().put(RelationalOperator.NE, factory.ne());
      permutationGroup.getRop().put(RelationalOperator.LT, factory.lt());
      permutationGroup.getRop().put(RelationalOperator.LE, factory.le());
      permutationGroup.getRop().put(RelationalOperator.GT, factory.gt());
      permutationGroup.getRop().put(RelationalOperator.GE, factory.ge());
      
      E multiResult = this.getInitializer().one();
      final int sgn = permutationGroup.sgn();
      for (int j = 0; j < permutationGroup.getColumnSize(); j++) {
        final E data = this.getCell(j, permutationGroup.getPairOf(j));
        multiResult = this.getOp(Operator.MULTI).apply(data, multiResult);
      }
      multiResult = this.getOp(Operator.MULTI).apply(multiResult, this.getInitializer().convertFrom(sgn));
      
      result = this.getOp(Operator.ADD).apply(multiResult, result);
    } while (rowPermutation.next());
    
    return result;
  }
  
  /**
   * トレース取得メソッド
   *
   * @return 計算結果
   */
  public E trace() {
    E result = this.getInitializer().zero();
    for (int i = 0; i < this.getSize(); i++)
      result = this.getOp(Operator.ADD).apply(this.getCell(i, i), result);
    
    return result;
  }
  
  /**
   * matrix(row=i,column=i) <==> NE ZERO transformation
   * 掃き出し法の第１段階実装メソッドです。同じ行/列番号上の要素がゼロ以外になるように行基本変形を行います。
   * @param M 拡大係数行列
   */
  protected void calcInverseMatrixPhase1(Matrix<E> M) {
  
    for (int i = 0; i < this.getRowSize(); i++) {
      if (this.getRop(RelationalOperator.EQ).apply(M.getCell(i, i), this.getInitializer().zero())) {
        final E[] vertical = M.getColumn(i);
        for (int j = 0; j < vertical.length; j++) {
          if (j != i && this.getRop(RelationalOperator.NE).apply(vertical[j], this.getInitializer().zero())) {
            M.multiAndAddRow(this.getInitializer().one(), j, i);
            break;
          }
        }
      }
    }
    
  }
  
  /**
   * matrix(row=i,column=j), i != j <==> EQ ZERO transformation
   * 掃き出し法の第２段階実装メソッドです。異なる行/列番号上の要素がゼロになるように行基本変形を行います。
   * @param M 拡大係数行列
   */
  protected void calcInverseMatrixPhase2(Matrix<E> M) {
  
    for (int j = 0; j < this.getColumnSize(); j++) {
      for (int i = 0; i < this.getRowSize(); i++) {
        final E targetCell = M.getCell(i, j);
        final E[] vertical = M.getColumn(j);
        if (j != i && this.getRop(RelationalOperator.NE).apply(vertical[j], this.getInitializer().zero())) {
          int kk = 0;
          int max = Integer.MIN_VALUE;
          for (int k = 0; k < vertical.length; k++) {
            int cntZero = 0;
            final E[] horizontal = M.getRow(k);
            for (int l = 0; l < this.getColumnSize(); l++) {
              if (this.getRop(RelationalOperator.EQ).apply(horizontal[l], this.getInitializer().zero())) {
                cntZero++;
              }
            }
          
            if (i != k && this.getRop(RelationalOperator.NE).apply(vertical[k], this.getInitializer().zero())) {
              if (max < cntZero) {
                max = cntZero;
                kk = k;
              }
            }
          }
          E scalar = this.getOp(Operator.DIV).apply(targetCell, vertical[kk]);
          scalar = this.getOp(Operator.MULTI).apply(scalar, this.getInitializer().minusOne());
          M.multiAndAddRow(scalar, kk, i);
        }
      }
    }
    
  }
  
  /**
   * matrix(row=i,column=i) <==> EQ ONE transformation
   * 掃き出し法の第３段階実装メソッドです。同じ行/列番号上の要素が１になるように行基本変形を行います。
   * このメソッドの実装が完了した段階で、horizontalExtensionMatrixの左部分が単位行列になります。
   * @param M 拡大係数行列
   */
  protected void calcInverseMatrixPhase3(Matrix<E> M) {
  
    for (int i = 0; i < this.getRowSize(); i++) {
      if (this.getRop(RelationalOperator.NE).apply(
          M.getCell(i, i),
          this.getInitializer().one())) {
        
        M.multiRow(this.getOp(Operator.DIV).apply(
            this.getInitializer().one(),
            M.getCell(i, i)), i);
      }
    }
  }
}
