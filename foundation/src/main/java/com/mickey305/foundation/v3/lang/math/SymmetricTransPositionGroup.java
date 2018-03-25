package com.mickey305.foundation.v3.lang.math;

public class SymmetricTransPositionGroup extends SymmetricCycleGroup {
    public static final int SYMMETRIC_GROUP_ID = 2;
    private static final long serialVersionUID = 8458215808962912686L;

    protected SymmetricTransPositionGroup(int row, int column) {
        super(row, column);
    }

    protected SymmetricTransPositionGroup(Number[][] initialTable) {
        super(initialTable);
    }

    protected SymmetricTransPositionGroup(SymmetricTransPositionGroup table) {
        super(table);
    }
}
