package com.mickey305.foundation.v3.lang.math;

public class SquareMatrix extends Matrix {
    protected SquareMatrix(int size) {
        super(size, size);
    }

    protected SquareMatrix(Number[][] initialTable) {
        super(initialTable);
        if (!super.isSquare())
            throw new UnsupportedOperationException();
    }

    protected SquareMatrix(SquareMatrix matrix) {
        super(matrix);
    }

    /**
     *
     * @param size
     * @return
     */
    public static SquareMatrix of(int size) {
        return new SquareMatrix(size);
    }

    /**
     *
     * @param initialTable
     * @return
     */
    public static SquareMatrix of(Number[][] initialTable) {
        return new SquareMatrix(initialTable);
    }

    /**
     *
     * @param matrix
     * @return
     */
    public static SquareMatrix of(SquareMatrix matrix) {
        return new SquareMatrix(matrix);
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return super.getRowSize();
    }
}
