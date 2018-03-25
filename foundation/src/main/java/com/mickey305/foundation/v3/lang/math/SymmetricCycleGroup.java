package com.mickey305.foundation.v3.lang.math;

public class SymmetricCycleGroup extends SymmetricPermutationGroup {
    public static final int SYMMETRIC_GROUP_ID = 1;

    protected SymmetricCycleGroup(int row, int column) {
        super(row, column);
    }

    protected SymmetricCycleGroup(Number[][] initialTable) {
        super(initialTable);
    }

    protected SymmetricCycleGroup(SymmetricCycleGroup table) {
        super(table);
    }
}
