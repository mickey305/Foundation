package com.mickey305.foundation.v3.lang.math;

import com.mickey305.foundation.v3.compat.util.BinaryFunction;
import com.mickey305.foundation.v3.compat.util.Function;
import com.mickey305.foundation.v3.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v3.lang.math.operator.OperationExtensions;
import com.mickey305.foundation.v3.lang.math.operator.core.NumberOperationAdd;
import com.mickey305.foundation.v3.lang.math.operator.core.NumberOperationDiv;
import com.mickey305.foundation.v3.lang.math.operator.core.NumberOperationMax;
import com.mickey305.foundation.v3.lang.math.operator.core.NumberOperationMin;
import com.mickey305.foundation.v3.lang.math.operator.core.NumberOperationMulti;
import com.mickey305.foundation.v3.lang.math.operator.core.NumberOperationSub;
import com.mickey305.foundation.v3.lang.math.operator.core.RelationalOperationEQ;
import com.mickey305.foundation.v3.lang.math.operator.core.RelationalOperationGE;
import com.mickey305.foundation.v3.lang.math.operator.core.RelationalOperationGT;
import com.mickey305.foundation.v3.lang.math.operator.core.RelationalOperationLE;
import com.mickey305.foundation.v3.lang.math.operator.core.RelationalOperationLT;
import com.mickey305.foundation.v3.lang.math.operator.core.RelationalOperationNE;

import java.io.Serializable;
import java.util.Objects;

@Deprecated
abstract class AbstractNumberTable implements Serializable {
  protected static final int NULL_SIGNATURE = 0;
  protected static final int CONTAINS_SIGNATURE = 1;
  private static final long serialVersionUID = 9068089860957512191L;
  private final int rowSize;
  private final int columnSize;
  private final Number[][] table;
  private final int[][] signatureTable;

  protected enum Operator {
    ADD(0x0101, OperationLogicHolder.ADD_DEFAULT_FUNC),
    SUB(0x0102, OperationLogicHolder.SUB_DEFAULT_FUNC),
    MULTI(0x0103, OperationLogicHolder.MULTI_DEFAULT_FUNC),
    DIV(0x0104, OperationLogicHolder.DIV_DEFAULT_FUNC),
    MAX(0x0F01, OperationLogicHolder.MAX_DEFAULT_FUNC),
    MIN(0x0F02, OperationLogicHolder.MIN_DEFAULT_FUNC);

    Operator(int id, AbstractNumberOperation<Number> function) {
      this.id = id;
      this.f = function;
      this.ext = function;
    }

    public final int id;
    public final BinaryFunction<Number, Number, Number> f;
    public final OperationExtensions<Number, Number, Number> ext;
  }

  protected enum RelationalOperator {
    EQ(0x0101, OperationLogicHolder.EQ_DEFAULT_FUNC),
    NE(0x0102, OperationLogicHolder.NE_DEFAULT_FUNC),
    LT(0x0103, OperationLogicHolder.LT_DEFAULT_FUNC),
    LE(0x0104, OperationLogicHolder.LE_DEFAULT_FUNC),
    GT(0x0105, OperationLogicHolder.GT_DEFAULT_FUNC),
    GE(0x0106, OperationLogicHolder.GE_DEFAULT_FUNC);

    RelationalOperator(int id, AbstractNumberOperation<Boolean> function) {
      this.id = id;
      this.f = function;
      this.callbacks = function;
    }

    public final int id;
    public final BinaryFunction<Number, Number, Boolean> f;
    public final OperationExtensions<Number, Number, Boolean> callbacks;
  }

  protected AbstractNumberTable(int row, int column) {
    // argument data check
    this.checkArgument(row, column);
    // initialize
    this.table = new Number[row][column];
    this.signatureTable = new int[row][column];
    this.rowSize = row;
    this.columnSize = column;
    AbstractNumberTable.putSameValueTable(NULL_SIGNATURE, this.signatureTable);
  }

  protected AbstractNumberTable(Number[][] initialTable) {
    this(initialTable.length, (initialTable.length != 0)
        ? initialTable[0].length
        : 0);
    // inject element data
    for (int i = 0; i < this.getRowSize(); i++) {
      if (initialTable[i].length != this.getColumnSize())
        throw new IllegalArgumentException();
      System.arraycopy(initialTable[i], 0, this.table[i], 0, this.getColumnSize());
    }
    AbstractNumberTable.putSameValueTable(CONTAINS_SIGNATURE, this.signatureTable);
  }

  protected AbstractNumberTable(AbstractNumberTable table) {
    this(table.getTable());
  }

  protected AbstractNumberTable(Number scalar) {
    this(new Number[][]{{scalar}});
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
   * テーブル初期化メソッド
   * <p>
   * 初期化データをもとに、初期化対象テーブルの内容を初期化する。
   * </p>
   *
   * @param data 初期化データ
   * @param dest 初期化対象テーブル
   * @return 初期化対象テーブル
   */
  protected static int[][] putSameValueTable(int data, int[][] dest) {
    for (int i = 0; i < dest.length; i++)
      for (int j = 0; j < dest[i].length; j++)
        dest[i][j] = data;
    return dest;
  }

  /**
   * elementary transformation method - 1 (row method - 1)
   *
   * @param row1 swap target row number first
   * @param row2 swap target row number second
   */
  public void swapRow(int row1, int row2) {
    Number[] tmpRec = this.getRow(row1);
    this.putRowForcibly(row1, this.getRow(row2));
    this.putRowForcibly(row2, tmpRec);
  }

  /**
   * elementary transformation method - 2 (column method - 1)
   *
   * @param column1 swap target column number first
   * @param column2 swap target column number second
   */
  public void swapColumn(int column1, int column2) {
    Number[] tmpRec = this.getColumn(column1);
    this.putColumnForcibly(column1, this.getColumn(column2));
    this.putColumnForcibly(column2, tmpRec);
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
  protected void putRowForcibly(int row, Number[] rowData) {
    if (rowData.length != this.getRowSize())
      throw new IllegalArgumentException();

    int i = 0;
    for (Number cell : rowData)
      this.putCellForcibly(row, i++, cell);
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
  protected void putColumnForcibly(int column, Number[] columnData) {
    if (columnData.length != this.getColumnSize())
      throw new IllegalArgumentException();

    int i = 0;
    for (Number cell : columnData)
      this.putCellForcibly(i++, column, cell);
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
  public void putCellForcibly(int row, int column, Number cell) {
    this.table[row][column] = cell;
    this.signatureTable[row][column] = CONTAINS_SIGNATURE;
  }

  /**
   * セル更新メソッド
   * <p>
   * 更新対象行列番号のセルデータを更新データに置き換える。
   * シグネチャテーブルが<code>CONTAINS_SIGNATURE</code>更新済（更新対象セルに、既にデータが明示的に追加されている）
   * の場合、更新を実行しない。
   * </p>
   *
   * @param row    更新対象行番号
   * @param column 更新対象列番号
   * @param cell   更新データ
   * @return 処理結果
   */
  public boolean putCell(int row, int column, Number cell) {
    final Number targetCell = this.getCell(row, column);
    if (RelationalOperator.EQ.f.apply(targetCell, 0)
        && this.signatureTable[row][column] == NULL_SIGNATURE) {
      this.putCellForcibly(row, column, cell);
      return true;
    } else {
      return false;
    }
  }

  /**
   * スカラー取得メソッド
   *
   * @param defaultScalar 取得失敗時のデフォルトスカラー
   * @return スカラー
   */
  public Number getScalarOrElse(Number defaultScalar) {
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
  public Number getCell(int row, int column) {
    return this.getTable()[row][column];
  }

  /**
   * セル最大値・取得メソッド
   *
   * @return 最大値
   */
  public Number getMaxCell() {
    Number max = this.getCell(0, 0);
    Number target;
    for (int i = 0; i < this.getRowSize(); i++) {
      for (int j = 0; j < this.getColumnSize(); j++) {
        target = this.getCell(i, j);
        max = Operator.MAX.f.apply(target, max);
      }
    }
    return max;
  }

  /**
   * セル最小値・取得メソッド
   *
   * @return 最小値
   */
  public Number getMinCell() {
    Number min = this.getCell(0, 0);
    Number target;
    for (int i = 0; i < this.getRowSize(); i++) {
      for (int j = 0; j < this.getColumnSize(); j++) {
        target = this.getCell(i, j);
        min = Operator.MIN.f.apply(target, min);
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
  public int countCellOf(final Number cell) {
    return this.countCellIf(new Function<Number, Boolean>() {
      @Override
      public Boolean apply(Number number) {
        return Objects.equals(number, cell);
      }
    });
  }

  /**
   * セルカウンター
   *
   * @param func カウント実装インターフェース
   * @return カウントセル数
   */
  public int countCellIf(Function<Number, Boolean> func) {
    Number target;
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
  public Number[] getRecord(int row) {
    return this.getRow(row);
  }

  /**
   * 行データ取得メソッド
   *
   * @param row 対象行番号
   * @return 取得データ
   */
  public Number[] getRow(int row) {
    return this.getTable()[row];
  }

  /**
   * 列データ取得メソッド
   *
   * @param column 対象列番号
   * @return 取得データ
   */
  public Number[] getColumn(int column) {
    final Number[] ary = new Number[this.getRowSize()];
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
    final Number elm = this.getCell(0, 0);
    return elm.getClass();
  }

  /**
   * 行合計
   *
   * @param row 計算対象行番号
   * @return 計算結果
   */
  public Number sumOfRow(int row) {
    final Number[] record = this.getRow(row);
    Number sum = 0;
    for (Number cell : record)
      sum = Operator.ADD.f.apply(cell, sum);
    return sum;
  }

  /**
   * 行合計
   *
   * @return 計算結果
   */
  public Number[] sumArrayOfRow() {
    final Number[] ary = new Number[this.getRowSize()];
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
  public Number averageOfRow(int row) {
    Number sum = this.sumOfRow(row);
    return Operator.DIV.f.apply(sum, this.getColumnSize());
  }

  /**
   * 行平均
   *
   * @return 計算結果
   */
  public Number[] averageArrayOfRow() {
    final Number[] ary = new Number[this.getRowSize()];
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
  public Number sumOfColumn(int column) {
    final Number[] record = this.getColumn(column);
    Number sum = 0;
    for (Number cell : record)
      sum = Operator.ADD.f.apply(cell, sum);
    return sum;
  }

  /**
   * 列合計
   *
   * @return 計算結果
   */
  public Number[] sumArrayOfColumn() {
    final Number[] ary = new Number[this.getColumnSize()];
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
  public Number averageOfColumn(int column) {
    Number sum = this.sumOfColumn(column);
    return Operator.DIV.f.apply(sum, this.getRowSize());
  }

  /**
   * 列平均
   *
   * @return 計算結果
   */
  public Number[] averageArrayOfColumn() {
    final Number[] ary = new Number[this.getColumnSize()];
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

  public Number[][] getTable() {
    return table;
  }

  protected int[][] getSignatureTable() {
    return signatureTable;
  }

  public int getRowSize() {
    return rowSize;
  }

  public int getColumnSize() {
    return columnSize;
  }

  private static final class OperationLogicHolder {
    private static final AbstractNumberOperation<Number> ADD_DEFAULT_FUNC = new NumberOperationAdd();
    private static final AbstractNumberOperation<Number> SUB_DEFAULT_FUNC = new NumberOperationSub();
    private static final AbstractNumberOperation<Number> MULTI_DEFAULT_FUNC = new NumberOperationMulti();
    private static final AbstractNumberOperation<Number> DIV_DEFAULT_FUNC = new NumberOperationDiv();
    private static final AbstractNumberOperation<Number> MAX_DEFAULT_FUNC = new NumberOperationMax();
    private static final AbstractNumberOperation<Number> MIN_DEFAULT_FUNC = new NumberOperationMin();
    // relational operator
    private static final AbstractNumberOperation<Boolean> EQ_DEFAULT_FUNC = new RelationalOperationEQ();
    private static final AbstractNumberOperation<Boolean> NE_DEFAULT_FUNC = new RelationalOperationNE();
    private static final AbstractNumberOperation<Boolean> LT_DEFAULT_FUNC = new RelationalOperationLT();
    private static final AbstractNumberOperation<Boolean> LE_DEFAULT_FUNC = new RelationalOperationLE();
    private static final AbstractNumberOperation<Boolean> GT_DEFAULT_FUNC = new RelationalOperationGT();
    private static final AbstractNumberOperation<Boolean> GE_DEFAULT_FUNC = new RelationalOperationGE();
  }
}
