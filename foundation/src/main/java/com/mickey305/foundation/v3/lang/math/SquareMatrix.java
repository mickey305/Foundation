package com.mickey305.foundation.v3.lang.math;

import com.mickey305.foundation.v3.util.Permutation;
import org.apache.commons.math3.fraction.BigFraction;

@Deprecated
public class SquareMatrix extends Matrix {
    private static final long serialVersionUID = 1206582462762106739L;

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
     * べき乗処理
     * @param matrix 行列
     * @param index 指数（自然数）
     * @return 演算結果行列
     */
    public static SquareMatrix exp(SquareMatrix matrix, int index) {
        if (index == 0)
            return matrix.createIdentityMatrix();

        return SquareMatrix.of(Matrix.exp(matrix, index).getTable());
    }

    /**
     * 行列サイズ取得処理
     * @return 行列サイズ
     */
    public int getSize() {
        return super.getRowSize();
    }

    /**
     * 単位行列取得メソッド
     * @return 単位行列
     */
    public SquareMatrix createIdentityMatrix() {
        final SquareMatrix matrix = SquareMatrix.of(this);
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
     * 逆行列取得メソッド
     * @return 逆行列
     */
    public SquareMatrix createInverseMatrix() {
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

        final SquareMatrix result = SquareMatrix.of(this);
        for (int i = 0; i < result.getRowSize(); i++)
            for (int j = 0; j < result.getColumnSize(); j++)
                result.putCellForcibly(i, j, em.getCell(i, this.getColumnSize() + j));
        return result;
    }

    /**
     * 正則行列判定メソッド
     * @return 判定結果
     */
    public boolean isRegular() {
        return RelationalOperator.NE.f.apply(this.determinant(), 0);
    }

    /**
     * 行列式取得メソッド
     * @return 計算結果
     */
    public Number determinant() {
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
     * トレース取得メソッド
     * @return 計算結果
     */
    public Number trace() {
        Number result = 0;
        for (int i = 0; i < this.getSize(); i++)
            result = Operator.ADD.f.apply(this.getCell(i, i), result);

        return result;
    }
}
