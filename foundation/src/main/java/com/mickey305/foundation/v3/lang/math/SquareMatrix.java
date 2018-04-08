package com.mickey305.foundation.v3.lang.math;

public class SquareMatrix extends Matrix {
    private static final long serialVersionUID = 8693082481180061222L;

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
     * インスタンス生成メソッド
     * @param size 行・列サイズ
     * @return インスタンス
     */
    public static SquareMatrix of(int size) {
        return new SquareMatrix(size);
    }

    /**
     * インスタンス生成メソッド
     * @param initialTable 初期化テーブル
     * @return インスタンス
     */
    public static SquareMatrix of(Number[][] initialTable) {
        return new SquareMatrix(initialTable);
    }

    /**
     * インスタンス生成メソッド
     * @param matrix 初期化インスタンス
     * @return インスタンス
     */
    public static SquareMatrix of(SquareMatrix matrix) {
        return new SquareMatrix(matrix);
    }

    /**
     * 行列サイズ取得処理
     * @return 行列サイズ
     */
    public int getSize() {
        return super.getRowSize();
    }
}
