package com.mickey305.foundation.v3.lang.math;

public class SymmetricCycleGroup extends SymmetricPermutationGroup {
    public static final int SYMMETRIC_GROUP_ID = 1;
    private static final long serialVersionUID = -7117314018319561496L;

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
