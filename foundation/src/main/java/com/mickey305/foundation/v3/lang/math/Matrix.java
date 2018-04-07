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
     *
     * @param row
     * @param column
     */
    private void checkArgument(int row, int column) {
        if (row <= 0 || column <= 0)
            throw new IllegalArgumentException();
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public static Matrix of(int row, int column) {
        return new Matrix(row, column);
    }

    /**
     *
     * @param initialTable
     * @return
     */
    public static Matrix of(Number[][] initialTable) {
        return new Matrix(initialTable);
    }

    /**
     *
     * @param matrix
     * @return
     */
    public static Matrix of(Matrix matrix) {
        return new Matrix(matrix);
    }

    /**
     *
     * @param scalar
     * @return
     */
    public static Matrix of(Number scalar) {
        return new Matrix(scalar);
    }

    /**
     *
     * @param leftMatrix
     * @param rightMatrix
     * @return
     */
    public static Matrix add(Matrix leftMatrix, Matrix rightMatrix) {
        return Matrix.simplyOperate(leftMatrix, rightMatrix, Operator.ADD);
    }

    /**
     *
     * @param leftMatrix
     * @param rightMatrix
     * @return
     */
    public static Matrix sub(Matrix leftMatrix, Matrix rightMatrix) {
        return Matrix.simplyOperate(leftMatrix, rightMatrix, Operator.SUB);
    }

    /**
     *
     * @param scalar
     * @param matrix
     * @return
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
     *
     * @param leftMatrix
     * @param rightMatrix
     * @return
     */
    public static Matrix multi(Matrix leftMatrix, Matrix rightMatrix) {
        if (leftMatrix.getColumnSize() != rightMatrix.getRowSize())
            throw new UnsupportedOperationException();

        final Matrix resultMatrix = Matrix.of(new Number[leftMatrix.getRowSize()][rightMatrix.getColumnSize()]);
        for (int i = 0; i < leftMatrix.getRowSize(); i++) {
            Number[] leftRec = leftMatrix.getHorizontalArray(i);
            for (int j = 0; j < rightMatrix.getColumnSize(); j++) {
                Number[] rightRec = rightMatrix.getVerticalArray(j);

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
     *
     * @param leftMatrix
     * @param rightMatrix
     * @param operator
     * @return
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
     *
     * @param matrix
     * @param index
     * @return
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
     *
     * @param l
     * @param r
     * @return
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
     *
     * @param t
     * @param b
     * @return
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
     *
     * @return
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
     *
     * @return
     */
    public Matrix createZeroMatrix() {
        final Matrix matrix = Matrix.of(this);
        for(int i = 0; i < this.getRowSize(); i++)
            for (int j = 0; j < this.getColumnSize(); j++)
                matrix.putCellForcibly(i, j ,0);

        return matrix;
    }

    /**
     *
     * @return
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
                final Number[] vertical = em.getVerticalArray(i);
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
                final Number[] vertical = em.getVerticalArray(j);
                if (j != i && RelationalOperator.NE.f.apply(vertical[j], bigFractionZero)) {
                    int kk = 0;
                    int max = Integer.MIN_VALUE;
                    for (int k = 0; k < vertical.length; k++) {
                        int cntZero = 0;
                        final Number[] horizontal = em.getHorizontalArray(k);
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
     *
     * @return
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
     *
     * @return
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
     *
     * @return
     */
    public boolean isRegular() {
        return this.isSquare() && RelationalOperator.NE.f.apply(this.determinant(), 0);
    }

    /**
     * elementary transformation method - 1 (row method - 1)
     * @param row1
     * @param row2
     */
    @Override public void swapRow(int row1, int row2) {
        Number[] tmpRec = this.getHorizontalArray(row1);
        this.putRowForcibly(row1, this.getHorizontalArray(row2));
        this.putRowForcibly(row2, tmpRec);
    }

    /**
     * elementary transformation method - 2 (column method - 1)
     * @param column1
     * @param column2
     */
    @Override public void swapColumn(int column1, int column2) {
        Number[] tmpRec = this.getVerticalArray(column1);
        this.putColumnForcibly(column1, this.getVerticalArray(column2));
        this.putColumnForcibly(column2, tmpRec);
    }

    /**
     * elementary transformation method - 3 (row method - 2)
     * @param scalar
     * @param row
     * @return
     */
    public Number[] multiRow(Number scalar, int row) {
        final Matrix matrix = Matrix.of(new Number[][]{this.getHorizontalArray(row)});
        final Number[] rowData = Matrix.multi(scalar, matrix).getHorizontalArray(0);
        this.putRowForcibly(row, rowData);
        return rowData;
    }

    /**
     * elementary transformation method - 4 (column method - 2)
     * @param scalar
     * @param column
     * @return
     */
    public Number[] multiColumn(Number scalar, int column) {
        final Matrix matrix = Matrix.of(new Number[][]{this.getVerticalArray(column)});
        final Number[] columnData = Matrix.multi(scalar, matrix).getHorizontalArray(0);
        this.putColumnForcibly(column, columnData);
        return columnData;
    }

    /**
     * elementary transformation method - 5 (row method - 3)
     * @param scalar
     * @param multiRow
     * @param addRow
     */
    public void multiAndAddRow(Number scalar, int multiRow, int addRow) {
        Matrix matrix;
        matrix = Matrix.of(new Number[][]{this.getHorizontalArray(multiRow)});
        final Matrix multiMatrix = Matrix.multi(scalar, matrix);
        matrix = Matrix.of(new Number[][]{this.getHorizontalArray(addRow)});
        final Matrix addMatrix = Matrix.add(multiMatrix, matrix);
        final Number[] rowData = addMatrix.getHorizontalArray(0);
        this.putRowForcibly(addRow, rowData);
    }

    /**
     * elementary transformation method - 6 (column method - 3)
     * @param scalar
     * @param multiColumn
     * @param addColumn
     */
    public void multiAndAddColumn(Number scalar, int multiColumn, int addColumn) {
        Matrix matrix;
        matrix = Matrix.of(new Number[][]{this.getVerticalArray(multiColumn)});
        final Matrix multiMatrix = Matrix.multi(scalar, matrix);
        matrix = Matrix.of(new Number[][]{this.getVerticalArray(addColumn)});
        final Matrix addMatrix = Matrix.add(multiMatrix, matrix);
        final Number[] columnData = addMatrix.getHorizontalArray(0);
        this.putColumnForcibly(addColumn, columnData);
    }

    /**
     *
     * @param row
     * @param rowData
     */
    @Override protected void putRowForcibly(int row, Number[] rowData) {
        if (rowData.length != this.getColumnSize())
            throw new IllegalArgumentException();

        int i = 0;
        for(Number cell: rowData)
            this.putCellForcibly(row, i++, cell);
    }

    /**
     *
     * @param column
     * @param columnData
     */
    @Override protected void putColumnForcibly(int column, Number[] columnData) {
        if (columnData.length != this.getRowSize())
            throw new IllegalArgumentException();

        int i = 0;
        for(Number cell: columnData)
            this.putCellForcibly(i++, column, cell);
    }

    /**
     *
     * @param row
     * @param column
     * @param cell
     */
    @Override public void putCellForcibly(int row, int column, Number cell) {
        this.getTable()[row][column] = cell;
        this.getSignatureTable()[row][column] = CONTAINS_SIGNATURE;
    }

    /**
     *
     * @param point
     */
    private void putCellForcibly(Triple<Integer, Integer, Number> point) {
        this.putCellForcibly(point.getLeft(), point.getMiddle(), point.getRight());
    }

    /**
     *
     * @param points
     */
    public void putCellsForcibly(Set<Triple<Integer, Integer, Number>> points) {
        for (Triple<Integer, Integer, Number> point: points)
            this.putCellForcibly(point);
    }

    /**
     *
     * @param row
     * @param column
     * @param cell
     * @return
     */
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
     *
     * @param point
     * @return
     */
    private boolean putCell(Triple<Integer, Integer, Number> point) {
        return this.putCell(point.getLeft(), point.getMiddle(), point.getRight());
    }

    /**
     *
     * @param points
     * @return
     */
    public boolean putCells(Set<Triple<Integer, Integer, Number>> points) {
        boolean status;
        for (Triple<Integer, Integer, Number> point: points) {
            status = this.putCell(point);
            if (!status)
                return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    private Class<? extends Number> getElementType() {
        final Number elm = this.getCell(0, 0);
        return elm.getClass();
    }

    /**
     *
     * @return
     */
    public Matrix sumOfRow() {
        final Number[] ary = this.sumArrayOfRow();
        final Number[][] table = new Number[this.getRowSize()][1];
        for (int i = 0; i < this.getRowSize(); i++)
            table[i][0] = ary[i];
        return Matrix.of(table);
    }

    /**
     *
     * @return
     */
    public Matrix averageOfRow() {
        final Number[] ary = this.averageArrayOfRow();
        final Number[][] table = new Number[this.getRowSize()][1];
        for (int i = 0; i < this.getRowSize(); i++)
            table[i][0] = ary[i];
        return Matrix.of(table);
    }

    /**
     *
     * @return
     */
    public Matrix sumOfColumn() {
        final Number[] ary = this.sumArrayOfColumn();
        final Number[][] table = new Number[1][this.getColumnSize()];
        System.arraycopy(ary, 0, table[0], 0, this.getColumnSize());
        return Matrix.of(table);
    }

    /**
     *
     * @return
     */
    public Matrix averageOfColumn() {
        final Number[] ary = this.averageArrayOfColumn();
        final Number[][] table = new Number[1][this.getColumnSize()];
        System.arraycopy(ary, 0, table[0], 0, this.getColumnSize());
        return Matrix.of(table);
    }
}
