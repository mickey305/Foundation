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

import com.mickey305.foundation.v3.compat.exception.wrap.UnsupportedOperationException;
import com.mickey305.foundation.v3.compat.exception.wrap.IllegalArgumentException;
import com.mickey305.foundation.v3.util.Assert;
import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.Set;

/**
 * 行列演算用クラスです
 * @param <E> 行列の要素の総称型です
 */
public class Matrix<E extends Number> extends AbstractNumberTable<E> {
  private static final long serialVersionUID = -7165282387150597347L;
  
  public Matrix(int row, int column,
                IElementInitializer<E> initializer,
                Map<Operator, AbstractNumberOperation<E, E>> op,
                Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(row, column, initializer, op, rop);
  }
  
  public Matrix(E[][] initialTable,
                IElementInitializer<E> initializer,
                Map<Operator, AbstractNumberOperation<E, E>> op,
                Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(initialTable, initializer, op, rop);
  }
  
  public Matrix(Matrix<E> matrix) {
    this(matrix.getTable(), matrix.getInitializer(), matrix.getOp(), matrix.getRop());
  }
  
  public Matrix(E scalar,
                IElementInitializer<E> initializer,
                Map<Operator, AbstractNumberOperation<E, E>> op,
                Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(scalar, initializer, op, rop);
  }
  
  /**
   * 加算処理
   *
   * @param rightMatrix 右行列
   * @return 演算結果行列
   */
  public Matrix<E> add(Matrix<E> rightMatrix) {
    return Matrix.simplyOperate(this, rightMatrix, Operator.Add);
  }
  
  /**
   * 減算処理
   *
   * @param rightMatrix 右行列
   * @return 演算結果行列
   */
  public Matrix<E> sub(Matrix<E> rightMatrix) {
    return Matrix.simplyOperate(this, rightMatrix, Operator.Sub);
  }
  
  /**
   * 乗算処理
   *
   * @param scalar スカラー
   * @return 演算結果行列
   */
  public Matrix<E> multi(E scalar) {
    E resultCell;
    final Matrix<E> R = new Matrix<>(this);
    for (int i = 0; i < this.getRowSize(); i++) {
      for (int j = 0; j < this.getColumnSize(); j++) {
        resultCell = R.getOp(Operator.Multi).apply(this.getCell(i, j), scalar);
        R.putCell(i, j, resultCell);
      }
    }
    return R;
  }
  
  /**
   * 乗算処理
   *
   * @param rightMatrix 右行列
   * @return 演算結果行列
   */
  public Matrix<E> multi(Matrix<E> rightMatrix) {
    if (this.getColumnSize() != rightMatrix.getRowSize()) {
      throw new UnsupportedOperationException("multi-table-size-check failed. lhs-size=" + this.getColumnSize()
          + " rhs-size=" + rightMatrix.getRowSize());
    }
    
    final IElementInitializer<E> INI = this.getInitializer();
    final Matrix<E> R = new Matrix<>(
        INI.table(this.getRowSize(), rightMatrix.getColumnSize()),
        INI, this.getOp(), this.getRop());
    for (int i = 0; i < this.getRowSize(); i++) {
      E[] leftRec = this.getRow(i);
      for (int j = 0; j < rightMatrix.getColumnSize(); j++) {
        E[] rightRec = rightMatrix.getColumn(j);
        
        //assert leftRec.length == rightRec.length;
        Assert.requireEquals(leftRec.length, rightRec.length);
        
        E[] multiRec = INI.array(leftRec.length);
        for (int k = 0; k < multiRec.length; k++)
          multiRec[k] = this.getOp(Operator.Multi).apply(leftRec[k], rightRec[k]);
        
        E resultCell = INI.zero();
        for (E cell : multiRec)
          resultCell = this.getOp(Operator.Add).apply(cell, resultCell);
        
        R.putCell(i, j, resultCell);
      }
    }
    return R;
  }
  
  /**
   * 演算処理
   *
   * @param leftMatrix  左行列
   * @param rightMatrix 右行列
   * @param operator    オペレータ
   * @return 演算結果行列
   */
  private static <E extends Number> Matrix<E> simplyOperate(Matrix<E> leftMatrix, Matrix<E> rightMatrix, Operator operator) {
    if (leftMatrix.getRowSize() != rightMatrix.getRowSize()
        || leftMatrix.getColumnSize() != rightMatrix.getColumnSize()) {
      throw new UnsupportedOperationException("operation-target-tableData-size-check failed.");
    }
    
    E resultCell;
    final Matrix<E> R = new Matrix<>(leftMatrix);
    for (int i = 0; i < leftMatrix.getRowSize(); i++) {
      for (int j = 0; j < leftMatrix.getColumnSize(); j++) {
        resultCell = leftMatrix.getOp(operator).apply(leftMatrix.getCell(i, j), rightMatrix.getCell(i, j));
        R.putCell(i, j, resultCell);
      }
    }
    return R;
  }
  
  /**
   * べき乗処理
   *
   * @param index 指数（自然数）
   * @return 演算結果行列
   */
  public Matrix<E> exp(int index) {
    if (index <= 0) {
      throw new UnsupportedOperationException("exp-argument-check failed.");
    }
    
    Matrix<E> resultMatrix = this;
    for (int i = 0; i < index - 1; i++)
      resultMatrix = resultMatrix.multi(this);
    
    return resultMatrix;
  }
  
  /**
   * 水平連結
   *
   * @param r 右行列
   * @return 連結行列
   */
  public Matrix<E> horizontalBind(Matrix<E> r) {
    if (this.getRowSize() != r.getRowSize()) {
      throw new UnsupportedOperationException("bindData-size-check failed.");
    }
    
    final Matrix<E> R = new Matrix<>(
        this.getRowSize(),
        this.getColumnSize() + r.getColumnSize(),
        this.getInitializer(), this.getOp(), this.getRop());
    
    for (int i = 0; i < R.getRowSize(); i++) {
      for (int j = 0; j < R.getColumnSize(); j++) {
        Matrix<E> targetMatrix = r;
        int jj = j - this.getColumnSize();
        if (j < this.getColumnSize()) {
          targetMatrix = this;
          jj = j;
        }
        final E cell = targetMatrix.getCell(i, jj);
        R.putCell(i, j, cell);
      }
    }
    return R;
  }
  
  /**
   * 垂直連結
   *
   * @param b 下行列
   * @return 連結行列
   */
  public Matrix<E> verticalBind(Matrix<E> b) {
    if (this.getColumnSize() != b.getColumnSize()) {
      throw new UnsupportedOperationException("bindData-size-check failed.");
    }
    
    final Matrix<E> R = new Matrix<>(
        this.getRowSize() + b.getRowSize(),
        this.getColumnSize(),
        this.getInitializer(), this.getOp(), this.getRop());
    
    for (int i = 0; i < R.getRowSize(); i++) {
      Matrix<E> targetMatrix = b;
      int ii = i - this.getRowSize();
      if (i < this.getRowSize()) {
        targetMatrix = this;
        ii = i;
      }
      for (int j = 0; j < R.getColumnSize(); j++) {
        final E cell = targetMatrix.getCell(ii, j);
        R.putCell(i, j, cell);
      }
    }
    return R;
  }
  
  /**
   * 零行列取得メソッド
   *
   * @return 零行列
   */
  public Matrix<E> createZeroMatrix() {
    final Matrix<E> R = new Matrix<>(this);
    for (int i = 0; i < this.getRowSize(); i++)
      for (int j = 0; j < this.getColumnSize(); j++)
        R.putCell(i, j, this.getInitializer().zero());
    
    return R;
  }
  
  /**
   * 2値行列取得メソッド
   * <p>
   * 行列内の要素を<code>0</code>と<code>1</code>の2値に変換する。
   * <code>0</code>以下の数は<code>0</code>に、<code>0</code>を越える数は<code>1</code>に変換する。
   * </p>
   *
   * @return 2値行列
   */
  public Matrix<E> createLogicalMatrix() {
    final Matrix<E> R = new Matrix<>(this);
    final IElementInitializer<E> INI = this.getInitializer();
    for (int i = 0; i < this.getRowSize(); i++) {
      for (int j = 0; j < this.getColumnSize(); j++) {
        final E cell = R.getCell(i, j);
        R.putCell(i, j, this.getRop(RelationalOperator.LE).apply(cell, INI.zero())
            ? INI.zero()
            : INI.one());
      }
    }
    return R;
  }
  
  /**
   * elementary transformation method - 1 (row method - 1)
   *
   * @param row1 swap target row number first
   * @param row2 swap target row number second
   *             <p>
   *             {@inheritDoc}
   */
  @Override
  public void swapRow(int row1, int row2) {
    E[] tmpRec = this.getRow(row1);
    this.putRow(row1, this.getRow(row2));
    this.putRow(row2, tmpRec);
  }
  
  /**
   * elementary transformation method - 2 (column method - 1)
   *
   * @param column1 swap target column number first
   * @param column2 swap target column number second
   *                <p>
   *                {@inheritDoc}
   */
  @Override
  public void swapColumn(int column1, int column2) {
    E[] tmpRec = this.getColumn(column1);
    this.putColumn(column1, this.getColumn(column2));
    this.putColumn(column2, tmpRec);
  }
  
  /**
   * elementary transformation method - 3 (row method - 2)
   *
   * @param scalar scalar value
   * @param row    target row number
   * @return result
   */
  public E[] multiRow(E scalar, int row) {
    final IElementInitializer<E> INI = this.getInitializer();
    final E[][] tmpTable = INI.table(1, this.getRow(row).length);
    tmpTable[0] = this.getRow(row);
    final Matrix<E> matrix = new Matrix<>(tmpTable, INI, this.getOp(), this.getRop());
    final E[] rowData = matrix.multi(scalar).getRow(0);
    this.putRow(row, rowData);
    return rowData;
  }
  
  /**
   * elementary transformation method - 4 (column method - 2)
   *
   * @param scalar scalar value
   * @param column target column number
   * @return result
   */
  public E[] multiColumn(E scalar, int column) {
    final IElementInitializer<E> INI = this.getInitializer();
    final E[][] tmpTable = INI.table(1, this.getColumn(column).length);
    tmpTable[0] = this.getColumn(column);
    final Matrix<E> matrix = new Matrix<>(tmpTable, INI, this.getOp(), this.getRop());
    final E[] columnData = matrix.multi(scalar).getRow(0);
    this.putColumn(column, columnData);
    return columnData;
  }
  
  /**
   * elementary transformation method - 5 (row method - 3)
   * <p>algorithm: matrix[addRow, i] += scalar * matrix[multiRow, i], (i = 0,1,2...n)</p>
   *
   * @param scalar   scalar value
   * @param multiRow multiply target row number
   * @param addRow   add target row number
   */
  public void multiAndAddRow(E scalar, int multiRow, int addRow) {
    Matrix<E> matrix;
    final IElementInitializer<E> INI = this.getInitializer();
    final E[][] tmpTable = INI.table(1, this.getRow(multiRow).length);
    tmpTable[0] = this.getRow(multiRow);
    matrix = new Matrix<>(tmpTable, INI, this.getOp(), this.getRop());
    final Matrix<E> multiMatrix = matrix.multi(scalar);
    tmpTable[0] = this.getRow(addRow);
    matrix = new Matrix<>(tmpTable, INI, this.getOp(), this.getRop());
    final Matrix<E> addMatrix = multiMatrix.add(matrix);
    final E[] rowData = addMatrix.getRow(0);
    this.putRow(addRow, rowData);
  }
  
  /**
   * elementary transformation method - 6 (column method - 3)
   * <p>algorithm: matrix[i, addColumn] += scalar * matrix[i, multiColumn], (i = 0,1,2...n)</p>
   *
   * @param scalar      scalar value
   * @param multiColumn multiply target column number
   * @param addColumn   add target column number
   */
  public void multiAndAddColumn(E scalar, int multiColumn, int addColumn) {
    Matrix<E> matrix;
    final IElementInitializer<E> INI = this.getInitializer();
    final E[][] tmpTable = INI.table(1, this.getColumn(multiColumn).length);
    tmpTable[0] = this.getColumn(multiColumn);
    matrix = new Matrix<>(tmpTable, INI, this.getOp(), this.getRop());
    final Matrix<E> multiMatrix = matrix.multi(scalar);
    tmpTable[0] = this.getColumn(addColumn);
    matrix = new Matrix<>(tmpTable, INI, this.getOp(), this.getRop());
    final Matrix<E> addMatrix = multiMatrix.add(matrix);
    final E[] columnData = addMatrix.getRow(0);
    this.putColumn(addColumn, columnData);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected void putRow(int row, E[] rowData) {
    if (rowData.length != this.getColumnSize()) {
      throw new IllegalArgumentException("rowData-size-check failed.");
    }
    
    int i = 0;
    for (E cell : rowData)
      this.putCell(row, i++, cell);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected void putColumn(int column, E[] columnData) {
    if (columnData.length != this.getRowSize()) {
      throw new IllegalArgumentException("columnData-size-check failed.");
    }
    
    int i = 0;
    for (E cell : columnData)
      this.putCell(i++, column, cell);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void putCell(int row, int column, E cell) {
    this.getTable()[row][column] = cell;
  }
  
  /**
   * セル更新メソッド
   * <p>
   * 更新対象行列番号のセルデータを更新データに置き換える。
   * </p>
   *
   * @param point 更新データ
   */
  private void putCell(Triple<Integer, Integer, E> point) {
    this.putCell(point.getLeft(), point.getMiddle(), point.getRight());
  }
  
  /**
   * セル更新メソッド
   * <p>
   * 更新対象行列番号のセルデータを更新データに置き換える。
   * </p>
   *
   * @param points 更新データ
   */
  public void putCells(Set<Triple<Integer, Integer, E>> points) {
    for (Triple<Integer, Integer, E> point : points)
      this.putCell(point);
  }
  
  /**
   * 行合計
   *
   * @return 計算結果行列
   */
  public Matrix<E> sumOfRow() {
    final IElementInitializer<E> INI = this.getInitializer();
    final E[] ary = this.sumArrayOfRow();
    final E[][] table = INI.table(this.getRowSize(), 1);
    for (int i = 0; i < this.getRowSize(); i++)
      table[i][0] = ary[i];
    return new Matrix<>(table, INI, this.getOp(), this.getRop());
  }
  
  /**
   * 行平均
   *
   * @return 計算結果行列
   */
  public Matrix<E> averageOfRow() {
    final IElementInitializer<E> INI = this.getInitializer();
    final E[] ary = this.averageArrayOfRow();
    final E[][] table = INI.table(this.getRowSize(), 1);
    for (int i = 0; i < this.getRowSize(); i++)
      table[i][0] = ary[i];
    return new Matrix<>(table, INI, this.getOp(), this.getRop());
  }
  
  /**
   * 列合計
   *
   * @return 計算結果行列
   */
  public Matrix<E> sumOfColumn() {
    final IElementInitializer<E> INI = this.getInitializer();
    final E[] ary = this.sumArrayOfColumn();
    final E[][] table = INI.table(1, this.getColumnSize());
    System.arraycopy(ary, 0, table[0], 0, this.getColumnSize());
    return new Matrix<>(table, INI, this.getOp(), this.getRop());
  }
  
  /**
   * 列平均
   *
   * @return 計算結果行列
   */
  public Matrix<E> averageOfColumn() {
    final IElementInitializer<E> INI = this.getInitializer();
    final E[] ary = this.averageArrayOfColumn();
    final E[][] table = INI.table(1, this.getColumnSize());
    System.arraycopy(ary, 0, table[0], 0, this.getColumnSize());
    return new Matrix<>(table, INI, this.getOp(), this.getRop());
  }
}
