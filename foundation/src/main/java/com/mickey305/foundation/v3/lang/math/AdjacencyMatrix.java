package com.mickey305.foundation.v3.lang.math;

public class AdjacencyMatrix extends SquareMatrix {
    private static final long serialVersionUID = 5050943778982300438L;

    protected AdjacencyMatrix(int size) {
        super(size);
    }

    protected AdjacencyMatrix(Number[][] initialTable) {
        super(initialTable);
    }

    protected AdjacencyMatrix(AdjacencyMatrix matrix) {
        super(matrix);
    }

    /**
     *
     * @param size
     * @return
     */
    public static AdjacencyMatrix of(int size) {
        return new AdjacencyMatrix(size);
    }

    /**
     *
     * @param initialTable
     * @return
     */
    public static AdjacencyMatrix of(Number[][] initialTable) {
        return new AdjacencyMatrix(initialTable);
    }

    /**
     *
     * @param matrix
     * @return
     */
    public static AdjacencyMatrix of(AdjacencyMatrix matrix) {
        return new AdjacencyMatrix(matrix);
    }

    // todo
    /**
     *
     * @param matrix
     * @return
     */
    public static boolean isTree(AdjacencyMatrix matrix) {
        final Number[][] table = matrix.getTable();
        final int size = matrix.getSize();
        return false;
    }
}
