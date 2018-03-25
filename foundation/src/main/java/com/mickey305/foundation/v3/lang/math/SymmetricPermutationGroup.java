package com.mickey305.foundation.v3.lang.math;

public class SymmetricPermutationGroup extends AbstractNumberTable {
    public static final int SYMMETRIC_GROUP_ID = 0;

    protected SymmetricPermutationGroup(int row, int column) {
        super(row, column);
        // permutation data-structure check
        if (!this.checkPermutation())
            throw new IllegalArgumentException();
    }

    protected SymmetricPermutationGroup(Number[][] initialTable) {
        super(initialTable);
        // permutation data-structure check
        if (!this.checkPermutation())
            throw new IllegalArgumentException();
    }

    protected SymmetricPermutationGroup(SymmetricPermutationGroup table) {
        super(table);
    }

    private boolean checkPermutation() {
        return this.getRowSize() == 2 && this.getColumnSize() >= 1;
    }
}
