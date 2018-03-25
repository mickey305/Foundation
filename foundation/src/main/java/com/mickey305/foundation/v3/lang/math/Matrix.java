package com.mickey305.foundation.v3.lang.math;

import org.apache.commons.lang3.tuple.Triple;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Matrix extends AbstractNumberTable {
    private static final long serialVersionUID = 1425558936393536592L;
    private final Set<Metadata> rowMetadataSet;
    private final Set<Metadata> columnMetadataSet;

    protected Matrix(int row, int column) {
        super(row, column);
        // initialize
        this.rowMetadataSet = new HashSet<>(row);
        this.columnMetadataSet = new HashSet<>(column);
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
        // create metadata
        for (int i = 0; i < this.getRowSize(); i++)
            this.rowMetadataSet.add(new Metadata(i));
        for (int i = 0; i < this.getColumnSize(); i++)
            this.columnMetadataSet.add(new Metadata(i));
    }

    protected Matrix(Matrix matrix) {
        this(matrix.getTable());
        // recreate metadata
        this.rowMetadataSet.retainAll(Collections.emptySet());
        this.columnMetadataSet.retainAll(Collections.emptySet());
        this.rowMetadataSet.addAll(matrix.rowMetadataSet);
        this.columnMetadataSet.addAll(matrix.columnMetadataSet);
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
        resultMatrix.rowMetadataSet.retainAll(Collections.emptySet());
        resultMatrix.columnMetadataSet.retainAll(Collections.emptySet());
        resultMatrix.rowMetadataSet.addAll(leftMatrix.rowMetadataSet);
        resultMatrix.columnMetadataSet.addAll(rightMatrix.columnMetadataSet);
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
        final Set<Metadata> newColumnMetadataSet = new HashSet<>(r.getColumnSize());
        for (Metadata metadata: r.columnMetadataSet)
            newColumnMetadataSet.add(Metadata.of(metadata.getIndex() + l.getColumnSize(), metadata.getTitle()));
        resultMatrix.rowMetadataSet.retainAll(Collections.emptySet());
        resultMatrix.rowMetadataSet.addAll(l.rowMetadataSet);
        resultMatrix.columnMetadataSet.retainAll(Collections.emptySet());
        resultMatrix.columnMetadataSet.addAll(l.columnMetadataSet);
        resultMatrix.columnMetadataSet.addAll(newColumnMetadataSet);
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
        final Set<Metadata> newRowMetadataSet = new HashSet<>(b.getRowSize());
        for (Metadata metadata: b.rowMetadataSet)
            newRowMetadataSet.add(Metadata.of(metadata.getIndex() + t.getRowSize(), metadata.getTitle()));
        resultMatrix.rowMetadataSet.retainAll(Collections.emptySet());
        resultMatrix.rowMetadataSet.addAll(t.rowMetadataSet);
        resultMatrix.rowMetadataSet.addAll(newRowMetadataSet);
        resultMatrix.columnMetadataSet.retainAll(Collections.emptySet());
        resultMatrix.columnMetadataSet.addAll(t.columnMetadataSet);
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
        for(int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < this.getColumnSize(); j++) {
                matrix.putCellForcibly(i, j ,0);
            }
        }
        return matrix;
    }

    // todo
    /**
     *
     * @return
     */
    public Matrix createInverseMatrix() {
        if(!this.isSquare())
            throw new UnsupportedOperationException();

        final Matrix resultMatrix = Matrix.of(this);
        final Matrix extensionMatrix = Matrix.horizontalBind(this, this.createIdentityMatrix());
        return resultMatrix;
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
     * @param rowMetadata
     * @return
     */
    public boolean addRowMetadata(Metadata rowMetadata) {
        return this.rowMetadataSet.add(rowMetadata);
    }

    /**
     *
     * @param columnMetadata
     * @return
     */
    public boolean addColumnMetadata(Metadata columnMetadata) {
        return this.columnMetadataSet.add(columnMetadata);
    }

    /**
     *
     * @param rowMetadataCollection
     * @return
     */
    public boolean addRowMetadataCollection(Collection<? extends Metadata> rowMetadataCollection) {
        return this.rowMetadataSet.addAll(rowMetadataCollection);
    }

    /**
     *
     * @param columnMetadataCollection
     * @return
     */
    public boolean addColumnMetadataCollection(Collection<? extends Metadata> columnMetadataCollection) {
        return this.columnMetadataSet.addAll(columnMetadataCollection);
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
        this.findRowMetadata(row1).index = row2;
        this.findRowMetadata(row2).index = row1;
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
        this.findColumnMetadata(column1).index = column2;
        this.findColumnMetadata(column2).index = column1;
    }

    /**
     * elementary transformation method - 3 (row method - 2)
     * @param scalar
     * @param row
     * @return
     */
    public Number[] multiRow(int scalar, int row) {
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
    public Number[] multiColumn(int scalar, int column) {
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
    public void multiAndAddRow(int scalar, int multiRow, int addRow) {
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
    public void multiAndAddColumn(int scalar, int multiColumn, int addColumn) {
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
        this.putRowForcibly(new Metadata(row), rowData);
    }

    /**
     *
     * @param row
     * @param rowData
     */
    protected void putRowForcibly(Metadata row, Number[] rowData) {
        if (rowData.length != this.getRowSize())
            throw new IllegalArgumentException();

        int i = 0;
        for(Number cell: rowData)
            this.putCellForcibly(row, new Metadata(i++), cell);
    }

    /**
     *
     * @param column
     * @param columnData
     */
    @Override protected void putColumnForcibly(int column, Number[] columnData) {
        this.putColumnForcibly(new Metadata(column), columnData);
    }

    /**
     *
     * @param column
     * @param columnData
     */
    protected void putColumnForcibly(Metadata column, Number[] columnData) {
        if (columnData.length != this.getColumnSize())
            throw new IllegalArgumentException();

        int i = 0;
        for(Number cell: columnData)
            this.putCellForcibly(new Metadata(i++), column, cell);
    }

    /**
     *
     * @param row
     * @param column
     * @param cell
     */
    @Override public void putCellForcibly(int row, int column, Number cell) {
        this.putCellForcibly(new Metadata(row), new Metadata(column), cell);
    }

    /**
     *
     * @param row
     * @param column
     * @param cell
     */
    public void putCellForcibly(Metadata row, Metadata column, Number cell) {
        this.getTable()[row.getIndex()][column.getIndex()] = cell;
        this.getSignatureTable()[row.getIndex()][column.getIndex()] = CONTAINS_SIGNATURE;
        this.rowMetadataSet.add(row);
        this.columnMetadataSet.add(column);
    }

    /**
     *
     * @param point
     */
    private void putCellForcibly(Triple<Metadata, Metadata, Number> point) {
        this.putCellForcibly(point.getLeft(), point.getMiddle(), point.getRight());
    }

    /**
     *
     * @param point
     */
    private void putCellForciblyWithIndex(Triple<Integer, Integer, Number> point) {
        this.putCellForcibly(point.getLeft(), point.getMiddle(), point.getRight());
    }

    /**
     *
     * @param points
     */
    public void putCellsForcibly(Set<Triple<Metadata, Metadata, Number>> points) {
        for (Triple<Metadata, Metadata, Number> point: points)
            this.putCellForcibly(point);
    }

    /**
     *
     * @param points
     */
    public void putCellsForciblyWithIndex(Set<Triple<Integer, Integer, Number>> points) {
        for (Triple<Integer, Integer, Number> point: points)
            this.putCellForciblyWithIndex(point);
    }

    /**
     *
     * @param row
     * @param column
     * @param cell
     * @return
     */
    @Override public boolean putCell(int row, int column, Number cell) {
        return this.putCell(new Metadata(row), new Metadata(column), cell);
    }

    /**
     *
     * @param row
     * @param column
     * @param cell
     * @return
     */
    public boolean putCell(Metadata row, Metadata column, Number cell) {
        final Number targetCell = this.getCell(row, column);
        if (RelationalOperator.EQ.f.apply(targetCell, 0)
                && this.getSignatureTable()[row.index][column.index] == NULL_SIGNATURE) {
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
    private boolean putCell(Triple<Metadata, Metadata, Number> point) {
        return this.putCell(point.getLeft(), point.getMiddle(), point.getRight());
    }

    /**
     *
     * @param point
     * @return
     */
    private boolean putCellWithIndex(Triple<Integer, Integer, Number> point) {
        return this.putCell(point.getLeft(), point.getMiddle(), point.getRight());
    }

    /**
     *
     * @param points
     * @return
     */
    public boolean putCells(Set<Triple<Metadata, Metadata, Number>> points) {
        boolean status;
        for (Triple<Metadata, Metadata, Number> point: points) {
            status = this.putCell(point);
            if (!status)
                return false;
        }
        return true;
    }

    /**
     *
     * @param points
     * @return
     */
    public boolean putCellsWithIndex(Set<Triple<Integer, Integer, Number>> points) {
        boolean status;
        for (Triple<Integer, Integer, Number> point: points) {
            status = this.putCellWithIndex(point);
            if (!status)
                return false;
        }
        return true;
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public Number getCell(Metadata row, Metadata column) {
        return this.getCell(row.getIndex(), column.getIndex());
    }

    /**
     *
     * @param row
     * @return
     */
    public Number[] getHorizontalArray(Metadata row) {
        return this.getHorizontalArray(row.getIndex());
    }

    /**
     *
     * @param column
     * @return
     */
    public Number[] getVerticalArray(Metadata column) {
        return this.getVerticalArray(column.getIndex());
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
        Matrix matrix = Matrix.of(table);
        matrix.rowMetadataSet.retainAll(Collections.emptySet());
        matrix.rowMetadataSet.addAll(this.rowMetadataSet);
        return matrix;
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
        Matrix matrix = Matrix.of(table);
        matrix.rowMetadataSet.retainAll(Collections.emptySet());
        matrix.rowMetadataSet.addAll(this.rowMetadataSet);
        return matrix;
    }

    /**
     *
     * @return
     */
    public Matrix sumOfColumn() {
        final Number[] ary = this.sumArrayOfColumn();
        final Number[][] table = new Number[1][this.getColumnSize()];
        System.arraycopy(ary, 0, table[0], 0, this.getColumnSize());
        Matrix matrix = Matrix.of(table);
        matrix.columnMetadataSet.retainAll(Collections.emptySet());
        matrix.columnMetadataSet.addAll(this.columnMetadataSet);
        return matrix;
    }

    /**
     *
     * @return
     */
    public Matrix averageOfColumn() {
        final Number[] ary = this.averageArrayOfColumn();
        final Number[][] table = new Number[1][this.getColumnSize()];
        System.arraycopy(ary, 0, table[0], 0, this.getColumnSize());
        Matrix matrix = Matrix.of(table);
        matrix.columnMetadataSet.retainAll(Collections.emptySet());
        matrix.columnMetadataSet.addAll(this.columnMetadataSet);
        return matrix;
    }

    /**
     *
     * @param row
     * @return
     */
    public Metadata findRowMetadata(int row) {
        Iterator<Metadata> rows = this.rowMetadataSet.iterator();
        Metadata result;
        while (rows.hasNext()) {
            result = rows.next();
            if (result.index == row)
                return result;
        }
        return null;
    }

    /**
     *
     * @param column
     * @return
     */
    public Metadata findColumnMetadata(int column) {
        Iterator<Metadata> columns = this.columnMetadataSet.iterator();
        Metadata result;
        while (columns.hasNext()) {
            result = columns.next();
            if (result.index == column)
                return result;
        }
        return null;
    }

    public static class Metadata {
        private int index;
        private String title;

        public Metadata(int index, String title) {
            this.setIndex(index);
            this.setTitle(title);
        }

        public Metadata(int index) {
            this(index, "");
        }

        public static Metadata of (int index, String title) {
            return new Metadata(index, title);
        }

        public int getIndex() {
            return index;
        }

        private void setIndex(int index) {
            this.index = index;
        }

        public String getTitle() {
            return title;
        }

        private void setTitle(String title) {
            this.title = title;
        }

        @Override public int hashCode() {
            return this.getIndex();
        }

        @Override public boolean equals(Object target) {
            if (target instanceof Metadata)
                return this.index == ((Metadata) target).index;
            return false;
        }
    }
}
