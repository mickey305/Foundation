package com.mickey305.foundation.v3.lang.math;

import java.util.Arrays;

public class AdjacencyMatrix extends SquareMatrix {
    private static final long serialVersionUID = -8268567830873490892L;

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
     * インスタンス生成メソッド
     * @param size 行・列サイズ
     * @return インスタンス
     */
    public static AdjacencyMatrix of(int size) {
        return new AdjacencyMatrix(size);
    }

    /**
     * インスタンス生成メソッド
     * @param initialTable 初期化テーブル
     * @return インスタンス
     */
    public static AdjacencyMatrix of(Number[][] initialTable) {
        return new AdjacencyMatrix(initialTable);
    }

    /**
     * インスタンス生成メソッド
     * @param matrix 初期化インスタンス
     * @return インスタンス
     */
    public static AdjacencyMatrix of(AdjacencyMatrix matrix) {
        return new AdjacencyMatrix(matrix);
    }

    // todo
    /**
     * 木判定メソッド
     * @param matrix 判定対象の隣接行列
     * @return 判定結果
     */
    public static boolean isTree(AdjacencyMatrix matrix) {
        final Number[][] table = matrix.getTable();
        final int size = matrix.getSize();
        return false;
    }

    /**
     * 有向グラフ判定メソッド
     * @param matrix 判定対象の隣接行列
     * @return 判定結果
     */
    public static boolean isDirectedGraph(AdjacencyMatrix matrix) {
        for (int i = 0; i < matrix.getSize(); i++)
            if (!Arrays.equals(matrix.getRow(i), matrix.getColumn(i)))
                return false;

        return true;
    }
}
