package com.mickey305.foundation.v3.lang.math;

import com.mickey305.foundation.v3.util.Permutation;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.math3.fraction.BigFraction;

import java.util.Set;

public class Matrix extends AbstractNumberTable {
    private static final long serialVersionUID = 1685829924081972076L;

    protected Matrix(int row, int column) {
        super(row, column);
    }

    protected Matrix(Number[][] initialTable) {
        this(initialTable.length, (initialTable.length != 0)
                ? initialTable[0].length
                : 0);
        // inject element data
        for (int i = 0; i < this.getRowSize(); i++) {
            if (initialTable[i].length != this.getColumnSize())
                throw new IllegalArgumentException();
            System.arraycopy(initialTable[i], 0, this.getTable()[i], 0, this.getColumnSize());
        }
        AbstractNumberTable.putSameValueTable(CONTAINS_SIGNATURE, this.getSignatureTable());
    }

    protected Matrix(Matrix matrix) {
        this(matrix.getTable());
    }

    protected Matrix(Number scalar) {
        this(new Number[][]{{scalar}});
    }

    /**
     * インスタンス生成メソッド
     * @param row 行サイズ
     * @param column 列サイズ
     * @return インスタンス
     */
    public static Matrix of(int row, int column) {
        return new Matrix(row, column);
    }

    /**
     * インスタンス生成メソッド
     * @param initialTable 初期化テーブル
     * @return インスタンス
     */
    public static Matrix of(Number[][] initialTable) {
        return new Matrix(initialTable);
    }

    /**
     * インスタンス生成メソッド
     * @param matrix 初期化インスタンス
     * @return インスタンス
     */
    public static Matrix of(Matrix matrix) {
        return new Matrix(matrix);
    }

    /**
     * インスタンス生成メソッド
     * @param scalar 初期化スカラー
     * @return インスタンス
     */
    public static Matrix of(Number scalar) {
        return new Matrix(scalar);
    }

    /**
     * 加算処理
     * @param leftMatrix 左行列
     * @param rightMatrix 右行列
     * @return 演算結果行列
     */
    public static Matrix add(Matrix leftMatrix, Matrix rightMatrix) {
        return Matrix.simplyOperate(leftMatrix, rightMatrix, Operator.ADD);
    }

    /**
     * 減算処理
     * @param leftMatrix 左行列
     * @param rightMatrix 右行列
     * @return 演算結果行列
     */
    public static Matrix sub(Matrix leftMatrix, Matrix rightMatrix) {
        return Matrix.simplyOperate(leftMatrix, rightMatrix, Operator.SUB);
    }

    /**
     * 乗算処理
     * @param scalar スカラー
     * @param matrix 行列
     * @return 演算結果行列
     */
    public static Matrix multi(Number scalar, Matrix matrix) {
        Number resultCell;
        final Matrix resultMatrix = Matrix.of(matrix);
        for (int i = 0; i < matrix.getRowSize(); i++) {
            for (int j = 0; j < matrix.getColumnSize(); j++) {
                resultCell = Operator.MULTI.f.apply(matrix.getCell(i, j), scalar);
                resultMatrix.putCellForcibly(i, j, resultCell);
            }
        }
        return resultMatrix;
    }

    /**
     * 乗算処理
     * @param leftMatrix 左行列
     * @param rightMatrix 右行列
     * @return 演算結果行列
     */
    public static Matrix multi(Matrix leftMatrix, Matrix rightMatrix) {
        if (leftMatrix.getColumnSize() != rightMatrix.getRowSize())
            throw new UnsupportedOperationException();

        final Matrix resultMatrix = Matrix.of(new Number[leftMatrix.getRowSize()][rightMatrix.getColumnSize()]);
        for (int i = 0; i < leftMatrix.getRowSize(); i++) {
            Number[] leftRec = leftMatrix.getRow(i);
            for (int j = 0; j < rightMatrix.getColumnSize(); j++) {
                Number[] rightRec = rightMatrix.getColumn(j);

                assert leftRec.length == rightRec.length;

                Number[] multiRec = new Number[leftRec.length];
                for (int k = 0; k < multiRec.length; k++)
                    multiRec[k] = Operator.MULTI.f.apply(leftRec[k], rightRec[k]);

                Number resultCell = 0;
                for (Number cell: multiRec)
                    resultCell = Operator.ADD.f.apply(cell, resultCell);

                resultMatrix.putCellForcibly(i, j, resultCell);
            }
        }
        return resultMatrix;
    }

    /**
     * 演算処理
     * @param leftMatrix 左行列
     * @param rightMatrix 右行列
     * @param operator オペレータ
     * @return 演算結果行列
     */
    private static Matrix simplyOperate(Matrix leftMatrix, Matrix rightMatrix, Operator operator) {
        if (leftMatrix.getRowSize() != rightMatrix.getRowSize()
                || leftMatrix.getColumnSize() != rightMatrix.getColumnSize())
            throw new UnsupportedOperationException();

        Number resultCell;
        final Matrix resultMatrix = Matrix.of(leftMatrix);
        for (int i = 0; i < leftMatrix.getRowSize(); i++) {
            for (int j = 0; j < leftMatrix.getColumnSize(); j++) {
                resultCell = operator.f.apply(leftMatrix.getCell(i, j), rightMatrix.getCell(i, j));
                resultMatrix.putCellForcibly(i, j, resultCell);
            }
        }
        return resultMatrix;
    }

    /**
     * べき乗処理
     * @param matrix 行列
     * @param index 指数（自然数）
     * @return 演算結果行列
     */
    public static Matrix exp(Matrix matrix, int index) {
        if (index < 0)
            throw new UnsupportedOperationException();

        if (index == 0)
            return matrix.createIdentityMatrix();

        Matrix resultMatrix = matrix;
        for (int i = 0; i < index - 1; i++)
            resultMatrix = Matrix.multi(resultMatrix, matrix);

        return resultMatrix;
    }

    /**
     * 水平連結
     * @param l 左行列
     * @param r 右行列
     * @return 連結行列
     */
    public static Matrix horizontalBind(Matrix l, Matrix r) {
        if(l.getRowSize() != r.getRowSize())
            throw new UnsupportedOperationException();

        final Matrix resultMatrix = Matrix.of(l.getRowSize(), l.getColumnSize() + r.getColumnSize());
        for(int i = 0; i < resultMatrix.getRowSize(); i++) {
            for (int j = 0; j < resultMatrix.getColumnSize(); j++) {
                Matrix targetMatrix = r;
                int jj = j - l.getColumnSize();
                if (j < l.getColumnSize()) {
                    targetMatrix = l;
                    jj = j;
                }
                final Number cell = targetMatrix.getCell(i, jj);
                resultMatrix.putCellForcibly(i, j ,cell);
            }
        }
        return resultMatrix;
    }

    /**
     * 垂直連結
     * @param t 上行列
     * @param b 下行列
     * @return 連結行列
     */
    public static Matrix verticalBind(Matrix t, Matrix b) {
        if(t.getColumnSize() != b.getColumnSize())
            throw new UnsupportedOperationException();

        final Matrix resultMatrix = Matrix.of(t.getRowSize() + b.getRowSize(), t.getColumnSize());
        for(int i = 0; i < resultMatrix.getRowSize(); i++) {
            Matrix targetMatrix = b;
            int ii = i - t.getRowSize();
            if (i < t.getRowSize()) {
                targetMatrix = t;
                ii = i;
            }
            for (int j = 0; j < resultMatrix.getColumnSize(); j++) {
                final Number cell = targetMatrix.getCell(ii, j);
                resultMatrix.putCellForcibly(i, j ,cell);
            }
        }
        return resultMatrix;
    }

    /**
     * 単位行列取得メソッド
     * @return 単位行列
     */
    public Matrix createIdentityMatrix() {
        if (!this.isSquare())
            throw new UnsupportedOperationException();

        final Matrix matrix = Matrix.of(this);
        for(int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < this.getColumnSize(); j++) {
                matrix.putCellForcibly(i, j ,0);
                if (i == j)
                    matrix.putCellForcibly(i, j, 1);
            }
        }
        return matrix;
    }

    /**
     * 零行列取得メソッド
     * @return 零行列
     */
    public Matrix createZeroMatrix() {
        final Matrix matrix = Matrix.of(this);
        for(int i = 0; i < this.getRowSize(); i++)
            for (int j = 0; j < this.getColumnSize(); j++)
                matrix.putCellForcibly(i, j ,0);

        return matrix;
    }

    /**
     * 逆行列取得メソッド
     * @return 逆行列
     */
    public Matrix createInverseMatrix() {
        if(!this.isRegular())
            throw new UnsupportedOperationException();

        final BigFraction bigFractionZero     = BigFraction.ZERO;
        final BigFraction bigFractionOne      = BigFraction.ONE;
        final BigFraction bigFractionMinusOne = BigFraction.MINUS_ONE;
        final Matrix em = Matrix.horizontalBind(this, this.createIdentityMatrix());
        for (int i = 0; i < em.getRowSize(); i++)
            for (int j = 0; j < em.getColumnSize(); j++)
                em.putCellForcibly(i, j, Operator.ADD.f.apply(bigFractionZero, em.getCell(i,j)));

        // CELL(i,i) <==> NE ZERO transformation
        for (int i = 0; i < this.getRowSize(); i++) {
            if (RelationalOperator.EQ.f.apply(em.getCell(i, i), bigFractionZero)) {
                final Number[] vertical = em.getColumn(i);
                for (int j = 0; j < vertical.length; j++) {
                    if (j != i && RelationalOperator.NE.f.apply(vertical[j], bigFractionZero)) {
                        em.multiAndAddRow(bigFractionOne, j, i);
                        break;
                    }
                }
            }
        }

        // CELL(i,j), i != j <==> EQ ZERO transformation
        for (int j = 0; j < this.getColumnSize(); j++) {
            for (int i = 0; i < this.getRowSize(); i++) {
                final Number targetCell = em.getCell(i, j);
                final Number[] vertical = em.getColumn(j);
                if (j != i && RelationalOperator.NE.f.apply(vertical[j], bigFractionZero)) {
                    int kk = 0;
                    int max = Integer.MIN_VALUE;
                    for (int k = 0; k < vertical.length; k++) {
                        int cntZero = 0;
                        final Number[] horizontal = em.getRow(k);
                        for (int l = 0; l < this.getColumnSize(); l++)
                            if (RelationalOperator.EQ.f.apply(horizontal[l], bigFractionZero))
                                cntZero++;

                        if (i != k && RelationalOperator.NE.f.apply(vertical[k], bigFractionZero)) {
                            if (max < cntZero) {
                                max = cntZero;
                                kk = k;
                            }
                        }
                    }
                    Number scalar = Operator.DIV.f.apply(targetCell, vertical[kk]);
                    scalar = Operator.MULTI.f.apply(scalar, bigFractionMinusOne);
                    em.multiAndAddRow(scalar, kk, i);
                }
            }
        }

        // CELL(i,i) <==> EQ ONE transformation
        for (int i = 0; i < this.getRowSize(); i++)
            if (RelationalOperator.NE.f.apply(em.getCell(i, i), bigFractionOne))
                em.multiRow(Operator.DIV.f.apply(bigFractionOne, em.getCell(i, i)), i);

        final Matrix result = Matrix.of(this);
        for (int i = 0; i < result.getRowSize(); i++)
            for (int j = 0; j < result.getColumnSize(); j++)
                result.putCellForcibly(i, j, em.getCell(i, this.getColumnSize() + j));
        return result;
    }

    /**
     * 2値行列取得メソッド
     * <p>
     *     行列内の要素を<code>0</code>と<code>1</code>の2値に変換する。
     *     <code>0</code>以下の数は<code>0</code>に、<code>0</code>を越える数は<code>1</code>に変換する。
     * </p>
     * @return 2値行列
     */
    public Matrix createLogicalMatrix() {
        final Matrix matrix = Matrix.of(this);
        for(int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < this.getColumnSize(); j++) {
                final Number cell = matrix.getCell(i, j);
                matrix.putCellForcibly(i, j, RelationalOperator.LE.f.apply(cell, 0)
                        ? 0
                        : 1);
            }
        }
        return matrix;
    }

    /**
     * 行列式メソッド
     * @return 計算結果
     */
    public Number determinant() {
        if (!this.isSquare())
            throw new UnsupportedOperationException();

        Number result = 0;
        final Integer[] rowIndexes = new Integer[this.getColumnSize()];
        for (int i = 0; i < rowIndexes.length; i++)
            rowIndexes[i] = i;
        final Permutation<Integer> rowPermutation = new Permutation<>(rowIndexes);

        do {
            final SymmetricPermutationGroup permutationGroup = new SymmetricPermutationGroup(
                    new Integer[][]{rowIndexes, rowPermutation.getElements()});

            Number multiResult = 1;
            final int sgn = SymmetricPermutationGroup.sgn(permutationGroup);
            for (int j = 0; j < permutationGroup.getColumnSize(); j++) {
                final Number data = this.getCell(j, (Integer) permutationGroup.getPairOf(j));
                multiResult = Operator.MULTI.f.apply(data, multiResult);
            }
            multiResult = Operator.MULTI.f.apply(multiResult, sgn);

            result = Operator.ADD.f.apply(multiResult, result);
        } while (rowPermutation.next());

        return result;
    }

    /**
     * 正則行列判定メソッド
     * @return 判定結果
     */
    public boolean isRegular() {
        return this.isSquare() && RelationalOperator.NE.f.apply(this.determinant(), 0);
    }

    /**
     * elementary transformation method - 1 (row method - 1)
     * @param row1 swap target row number first
     * @param row2 swap target row number second
     */
    @Override public void swapRow(int row1, int row2) {
        Number[] tmpRec = this.getRow(row1);
        this.putRowForcibly(row1, this.getRow(row2));
        this.putRowForcibly(row2, tmpRec);
    }

    /**
     * elementary transformation method - 2 (column method - 1)
     * @param column1 swap target column number first
     * @param column2 swap target column number second
     */
    @Override public void swapColumn(int column1, int column2) {
        Number[] tmpRec = this.getColumn(column1);
        this.putColumnForcibly(column1, this.getColumn(column2));
        this.putColumnForcibly(column2, tmpRec);
    }

    /**
     * elementary transformation method - 3 (row method - 2)
     * @param scalar scalar value
     * @param row target row number
     * @return result
     */
    public Number[] multiRow(Number scalar, int row) {
        final Matrix matrix = Matrix.of(new Number[][]{this.getRow(row)});
        final Number[] rowData = Matrix.multi(scalar, matrix).getRow(0);
        this.putRowForcibly(row, rowData);
        return rowData;
    }

    /**
     * elementary transformation method - 4 (column method - 2)
     * @param scalar scalar value
     * @param column target column number
     * @return result
     */
    public Number[] multiColumn(Number scalar, int column) {
        final Matrix matrix = Matrix.of(new Number[][]{this.getColumn(column)});
        final Number[] columnData = Matrix.multi(scalar, matrix).getRow(0);
        this.putColumnForcibly(column, columnData);
        return columnData;
    }

    /**
     * elementary transformation method - 5 (row method - 3)
     * <p>algorithm: matrix[addRow, i] += scalar * matrix[multiRow, i], (i = 0,1,2...n)</p>
     * @param scalar scalar value
     * @param multiRow multiply target row number
     * @param addRow add target row number
     */
    public void multiAndAddRow(Number scalar, int multiRow, int addRow) {
        Matrix matrix;
        matrix = Matrix.of(new Number[][]{this.getRow(multiRow)});
        final Matrix multiMatrix = Matrix.multi(scalar, matrix);
        matrix = Matrix.of(new Number[][]{this.getRow(addRow)});
        final Matrix addMatrix = Matrix.add(multiMatrix, matrix);
        final Number[] rowData = addMatrix.getRow(0);
        this.putRowForcibly(addRow, rowData);
    }

    /**
     * elementary transformation method - 6 (column method - 3)
     * <p>algorithm: matrix[i, addColumn] += scalar * matrix[i, multiColumn], (i = 0,1,2...n)</p>
     * @param scalar scalar value
     * @param multiColumn multiply target column number
     * @param addColumn add target column number
     */
    public void multiAndAddColumn(Number scalar, int multiColumn, int addColumn) {
        Matrix matrix;
        matrix = Matrix.of(new Number[][]{this.getColumn(multiColumn)});
        final Matrix multiMatrix = Matrix.multi(scalar, matrix);
        matrix = Matrix.of(new Number[][]{this.getColumn(addColumn)});
        final Matrix addMatrix = Matrix.add(multiMatrix, matrix);
        final Number[] columnData = addMatrix.getRow(0);
        this.putColumnForcibly(addColumn, columnData);
    }

    @Override protected void putRowForcibly(int row, Number[] rowData) {
        if (rowData.length != this.getColumnSize())
            throw new IllegalArgumentException();

        int i = 0;
        for(Number cell: rowData)
            this.putCellForcibly(row, i++, cell);
    }

    @Override protected void putColumnForcibly(int column, Number[] columnData) {
        if (columnData.length != this.getRowSize())
            throw new IllegalArgumentException();

        int i = 0;
        for(Number cell: columnData)
            this.putCellForcibly(i++, column, cell);
    }

    @Override public void putCellForcibly(int row, int column, Number cell) {
        this.getTable()[row][column] = cell;
        this.getSignatureTable()[row][column] = CONTAINS_SIGNATURE;
    }

    /**
     * セル更新メソッド
     * <p>
     *     更新対象行列番号のセルデータを更新データに置き換える。
     * </p>
     * @param point 更新データ
     */
    private void putCellForcibly(Triple<Integer, Integer, Number> point) {
        this.putCellForcibly(point.getLeft(), point.getMiddle(), point.getRight());
    }

    /**
     * セル更新メソッド
     * <p>
     *     更新対象行列番号のセルデータを更新データに置き換える。
     * </p>
     * @param points 更新データ
     */
    public void putCellsForcibly(Set<Triple<Integer, Integer, Number>> points) {
        for (Triple<Integer, Integer, Number> point: points)
            this.putCellForcibly(point);
    }

    @Override public boolean putCell(int row, int column, Number cell) {
        final Number targetCell = this.getCell(row, column);
        if (RelationalOperator.EQ.f.apply(targetCell, 0)
                && this.getSignatureTable()[row][column] == NULL_SIGNATURE) {
            this.putCellForcibly(row, column, cell);
            return true;
        } else {
            return false;
        }
    }

    /**
     * セル更新メソッド
     * <p>
     *     更新対象行列番号のセルデータを更新データに置き換える。
     *     シグネチャテーブルが<code>CONTAINS_SIGNATURE</code>更新済（更新対象セルに、既にデータが明示的に追加されている）
     *     の場合、更新を実行しない。
     * </p>
     * @param point 更新データ
     * @return 処理結果
     */
    private boolean putCell(Triple<Integer, Integer, Number> point) {
        return this.putCell(point.getLeft(), point.getMiddle(), point.getRight());
    }

    /**
     * セル更新メソッド
     * <p>
     *     更新対象行列番号のセルデータを更新データに置き換える。
     *     シグネチャテーブルが１つでも<code>CONTAINS_SIGNATURE</code>更新済（更新対象セルに、既にデータが明示的に追加されている）
     *     の場合、更新を実行しない。
     * </p>
     * @param points 更新データ
     * @return 処理結果
     */
    public boolean putCells(Set<Triple<Integer, Integer, Number>> points) {
        boolean status;
        final Number[][] cacheTable = this.getTable();
        final int[][] cacheSignatureTable = this.getSignatureTable();
        for (Triple<Integer, Integer, Number> point: points) {
            // insert logic
            status = this.putCell(point);

            if (!status) {
                // rollback
                for (int i = 0; i < this.getRowSize(); i++) {
                    System.arraycopy(cacheTable[i], 0, this.getTable()[i], 0, this.getColumnSize());
                    System.arraycopy(cacheSignatureTable[i], 0, this.getSignatureTable()[i], 0, this.getColumnSize());
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 行合計
     * @return 計算結果行列
     */
    public Matrix sumOfRow() {
        final Number[] ary = this.sumArrayOfRow();
        final Number[][] table = new Number[this.getRowSize()][1];
        for (int i = 0; i < this.getRowSize(); i++)
            table[i][0] = ary[i];
        return Matrix.of(table);
    }

    /**
     * 行平均
     * @return 計算結果行列
     */
    public Matrix averageOfRow() {
        final Number[] ary = this.averageArrayOfRow();
        final Number[][] table = new Number[this.getRowSize()][1];
        for (int i = 0; i < this.getRowSize(); i++)
            table[i][0] = ary[i];
        return Matrix.of(table);
    }

    /**
     * 列合計
     * @return 計算結果行列
     */
    public Matrix sumOfColumn() {
        final Number[] ary = this.sumArrayOfColumn();
        final Number[][] table = new Number[1][this.getColumnSize()];
        System.arraycopy(ary, 0, table[0], 0, this.getColumnSize());
        return Matrix.of(table);
    }

    /**
     * 列平均
     * @return 計算結果行列
     */
    public Matrix averageOfColumn() {
        final Number[] ary = this.averageArrayOfColumn();
        final Number[][] table = new Number[1][this.getColumnSize()];
        System.arraycopy(ary, 0, table[0], 0, this.getColumnSize());
        return Matrix.of(table);
    }
}
