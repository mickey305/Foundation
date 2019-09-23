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

import com.mickey305.foundation.v3.compat.util.Function;
import com.mickey305.foundation.v3.util.Assert;
import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.mickey305.foundation.v4.lang.math.MathConst.ABS_NUM_TBL_CAPTURE_INI;
import static com.mickey305.foundation.v4.lang.math.MathConst.ABS_NUM_TBL_CAPTURE_TABLE_IO;

/**
 * 数値演算用の抽象テーブルクラスです
 * @param <E> テーブルの要素の総称型
 */
public abstract class AbstractNumberTable<E extends Number> implements Serializable {
  private static final long serialVersionUID = 6078171550155201724L;
  private final int rowSize;
  private final int columnSize;
  private final E[][] table;
  private final Map<Operator, AbstractNumberOperation<E, E>> op;
  private final Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop;
  private final IElementInitializer<E> initializer;
  
  public interface IOperator {
  }
  
  public enum Operator implements IOperator {
    ADD, SUB, MULTI, DIV, MAX, MIN
  }

  public enum RelationalOperator implements IOperator {
    EQ, NE, LT, LE, GT, GE
  }
  
  protected AbstractNumberTable(@Nonnull E[][] initialTable,
                                @Nonnull IElementInitializer<E> initializer,
                                @Nonnull Map<Operator, AbstractNumberOperation<E, E>> op,
                                @Nonnull Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    Assert.requireNonNull(initialTable);
    Assert.requireNonNull(initializer);
    Assert.requireNonNull(op);
    Assert.requireNonNull(rop);
    if (ABS_NUM_TBL_CAPTURE_INI) {
      Log.d("construct args => ini:" + ToStringBuilder.reflectionToString(initializer)
          + ", op:" + ToStringBuilder.reflectionToString(op)
          + ", rop:" + ToStringBuilder.reflectionToString(rop));
    }
    // initialize
    this.initializer = initializer;
    this.rowSize = initialTable.length;
    this.columnSize = (initialTable.length > 0)
        ? initialTable[0].length
        : 0;
    
    // argument data check
    this.checkArgument(this.rowSize, this.columnSize);
    
    this.table = initializer.table(this.rowSize, this.columnSize);
    this.op = new HashMap<>(Operator.values().length);
    this.rop = new HashMap<>(RelationalOperator.values().length);
    this.op.putAll(op);
    this.rop.putAll(rop);
    
    for (int i = 0; i < this.getRowSize(); i++) {
      if (initialTable[i].length != this.getColumnSize())
        throw new IllegalArgumentException();
      System.arraycopy(initialTable[i], 0, this.table[i], 0, this.getColumnSize());
    }
    if (ABS_NUM_TBL_CAPTURE_TABLE_IO) Log.d("table<=" + ToStringBuilder.reflectionToString(this.table));
  }
  
  protected AbstractNumberTable(int row, int column,
                                IElementInitializer<E> initializer,
                                Map<Operator, AbstractNumberOperation<E, E>> op,
                                Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    this(initializer.table(row, column), initializer, op, rop);
  }
  
  protected AbstractNumberTable(AbstractNumberTable<E> table) {
    this(table.getTable(), table.initializer, table.op, table.rop);
  }
  
  protected AbstractNumberTable(E scalar,
                                IElementInitializer<E> initializer,
                                Map<Operator, AbstractNumberOperation<E, E>> op,
                                Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    this(1, 1, initializer, op, rop);
    this.getTable()[0][0] = scalar;
  }
  
  /**
   * 引数チェックメソッド
   * <p>
   * 引数に設定された行列サイズのチェックを実施する。
   * </p>
   *
   * @param row    行サイズ
   * @param column 列サイズ
   */
  private void checkArgument(int row, int column) {
    if (row <= 0 || column <= 0)
      throw new IllegalArgumentException();
  }
  
  /**
   * elementary transformation method - 1 (row method - 1)
   *
   * @param row1 swap target row number first
   * @param row2 swap target row number second
   */
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
   */
  public void swapColumn(int column1, int column2) {
    E[] tmpRec = this.getColumn(column1);
    this.putColumn(column1, this.getColumn(column2));
    this.putColumn(column2, tmpRec);
  }
  
  /**
   * 行更新メソッド
   * <p>
   * 更新対象行番号の行データを更新データに置き換える。
   * </p>
   *
   * @param row     更新対象行番号
   * @param rowData 更新データ
   */
  protected void putRow(int row, E[] rowData) {
    if (rowData.length != this.getRowSize())
      throw new IllegalArgumentException();
    
    int i = 0;
    for (E cell : rowData)
      this.putCell(row, i++, cell);
  }
  
  /**
   * 列更新メソッド
   * <p>
   * 更新対象列番号の列データを更新データに置き換える。
   * </p>
   *
   * @param column     更新対象列番号
   * @param columnData 更新データ
   */
  protected void putColumn(int column, E[] columnData) {
    if (columnData.length != this.getColumnSize())
      throw new IllegalArgumentException();
    
    int i = 0;
    for (E cell : columnData)
      this.putCell(i++, column, cell);
  }
  
  /**
   * セル更新メソッド
   * <p>
   * 更新対象行列番号のセルデータを更新データに置き換える。
   * </p>
   *
   * @param row    更新対象行番号
   * @param column 更新対象列番号
   * @param cell   更新データ
   */
  public void putCell(int row, int column, E cell) {
    this.table[row][column] = cell;
    if (ABS_NUM_TBL_CAPTURE_TABLE_IO)
      Log.d("cell[" + row + "," + column + "]<=" + ToStringBuilder.reflectionToString(cell));
  }
  
  /**
   * スカラー取得メソッド
   *
   * @param defaultScalar 取得失敗時のデフォルトスカラー
   * @return スカラー
   */
  public E getScalarOrElse(E defaultScalar) {
    if (!this.isScalar())
      return defaultScalar;
    
    return this.getCell(0, 0);
  }
  
  /**
   * セル取得メソッド
   *
   * @param row    取得対象行番号
   * @param column 取得対象列番号
   * @return セルデータ
   */
  public E getCell(int row, int column) {
    if (ABS_NUM_TBL_CAPTURE_TABLE_IO)
      Log.d("cell[" + row + "," + column + "]=>" + ToStringBuilder.reflectionToString(this.table[row][column]));
    return this.getTable()[row][column];
  }
  
  /**
   * セル最大値・取得メソッド
   *
   * @return 最大値
   */
  public E getMaxCell() {
    E max = this.getCell(0, 0);
    E target;
    for (int i = 0; i < this.getRowSize(); i++) {
      for (int j = 0; j < this.getColumnSize(); j++) {
        target = this.getCell(i, j);
        max = this.op.get(Operator.MAX).apply(target, max);
      }
    }
    return max;
  }
  
  /**
   * セル最小値・取得メソッド
   *
   * @return 最小値
   */
  public E getMinCell() {
    E min = this.getCell(0, 0);
    E target;
    for (int i = 0; i < this.getRowSize(); i++) {
      for (int j = 0; j < this.getColumnSize(); j++) {
        target = this.getCell(i, j);
        min = this.op.get(Operator.MIN).apply(target, min);
      }
    }
    return min;
  }
  
  /**
   * 最大値セルカウンター
   *
   * @return 最大値セル数
   */
  public int countMaxCell() {
    return this.countCellOf(this.getMaxCell());
  }
  
  /**
   * 最小値セルカウンター
   *
   * @return 最小値セル数
   */
  public int countMinCell() {
    return this.countCellOf(this.getMinCell());
  }
  
  /**
   * セルカウンター
   *
   * @param cell カウント対象数値
   * @return カウントセル数
   */
  public int countCellOf(final E cell) {
    return this.countCellIf(new Function<E, Boolean>() {
      @Override
      public Boolean apply(E number) {
        if (number == null) return false;
        return rop.get(RelationalOperator.EQ).apply(number, cell);
      }
    });
  }
  
  /**
   * セルカウンター
   *
   * @param func カウント実装インターフェース
   * @return カウントセル数
   */
  public int countCellIf(Function<E, Boolean> func) {
    E target;
    int count = 0;
    for (int i = 0; i < this.getRowSize(); i++) {
      for (int j = 0; j < this.getColumnSize(); j++) {
        target = this.getCell(i, j);
        if (func.apply(target))
          count++;
      }
    }
    return count;
  }
  
  /**
   * 行データ取得メソッド
   *
   * @param row 対象行番号
   * @return 取得データ
   */
  public E[] getRecord(int row) {
    return this.getRow(row);
  }
  
  /**
   * 行データ取得メソッド
   *
   * @param row 対象行番号
   * @return 取得データ
   */
  public E[] getRow(int row) {
    return this.getTable()[row];
  }
  
  /**
   * 列データ取得メソッド
   *
   * @param column 対象列番号
   * @return 取得データ
   */
  public E[] getColumn(int column) {
    final E[] ary = this.initializer.array(this.getRowSize());
    for (int i = 0; i < this.getRowSize(); i++)
      ary[i] = this.getTable()[i][column];
    return ary;
  }
  
  /**
   * 入力数値クラス取得メソッド
   *
   * @return 数値クラス
   */
  private Class<? extends Number> getElementType() {
    final E elm = this.getCell(0, 0);
    return elm.getClass();
  }
  
  /**
   * 行合計
   *
   * @param row 計算対象行番号
   * @return 計算結果
   */
  public E sumOfRow(int row) {
    final E[] record = this.getRow(row);
    E sum = this.initializer.zero();
    for (E cell : record)
      sum = op.get(Operator.ADD).apply(cell, sum);
    return sum;
  }
  
  /**
   * 行合計
   *
   * @return 計算結果
   */
  public E[] sumArrayOfRow() {
    final E[] ary = this.getColumn(0).clone();
    for (int i = 0; i < this.getRowSize(); i++)
      ary[i] = this.sumOfRow(i);
    return ary;
  }
  
  /**
   * 行平均
   *
   * @param row 計算対象行番号
   * @return 計算結果
   */
  public E averageOfRow(int row) {
    E sum = this.sumOfRow(row);
    return op.get(Operator.DIV).apply(sum, this.initializer.convertFrom(this.getColumnSize()));
  }
  
  /**
   * 行平均
   *
   * @return 計算結果
   */
  public E[] averageArrayOfRow() {
    final E[] ary = this.getColumn(0).clone();
    for (int i = 0; i < this.getRowSize(); i++)
      ary[i] = this.averageOfRow(i);
    return ary;
  }
  
  /**
   * 列合計
   *
   * @param column 計算対象列番号
   * @return 計算結果
   */
  public E sumOfColumn(int column) {
    final E[] record = this.getColumn(column);
    E sum = this.initializer.zero();
    for (E cell : record)
      sum = op.get(Operator.ADD).apply(cell, sum);
    return sum;
  }
  
  /**
   * 列合計
   *
   * @return 計算結果
   */
  public E[] sumArrayOfColumn() {
    final E[] ary = this.getRow(0).clone();
    for (int i = 0; i < this.getColumnSize(); i++)
      ary[i] = this.sumOfColumn(i);
    return ary;
  }
  
  /**
   * 列平均
   *
   * @param column 計算対象列番号
   * @return 計算結果
   */
  public E averageOfColumn(int column) {
    E sum = this.sumOfColumn(column);
    return op.get(Operator.DIV).apply(sum, this.initializer.convertFrom(this.getRowSize()));
  }
  
  /**
   * 列平均
   *
   * @return 計算結果
   */
  public E[] averageArrayOfColumn() {
    final E[] ary = this.getRow(0).clone();
    for (int i = 0; i < this.getColumnSize(); i++)
      ary[i] = this.averageOfColumn(i);
    return ary;
  }
  
  /**
   * 正方テーブル判定
   *
   * @return 判定結果
   */
  public boolean isSquare() {
    return this.getRowSize() == this.getColumnSize();
  }
  
  /**
   * スカラー判定
   *
   * @return 判定結果
   */
  public boolean isScalar() {
    return this.getRowSize() * this.getColumnSize() == 1;
  }
  
  public E[][] getTable() {
    if (ABS_NUM_TBL_CAPTURE_TABLE_IO) Log.d("table=>" + ToStringBuilder.reflectionToString(table));
    return table;
  }
  
  public int getRowSize() {
    return rowSize;
  }
  
  public int getColumnSize() {
    return columnSize;
  }
  
  public Map<Operator, AbstractNumberOperation<E, E>> getOp() {
    return op;
  }

  public Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> getRop() {
    return rop;
  }
  
  public AbstractNumberOperation<E, E> getOp(Operator O) {
    return op.get(O);
  }
  
  public AbstractNumberOperation<E, Boolean> getRop(RelationalOperator RO) {
    return rop.get(RO);
  }
  
  protected IElementInitializer<E> getInitializer() {
    return initializer;
  }
}
