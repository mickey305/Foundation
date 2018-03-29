package com.mickey305.foundation.v3.lang.math;

public class SymmetricTransPositionGroup extends SymmetricCycleGroup {
    public static final int SYMMETRIC_GROUP_ID = 2;
    private static final long serialVersionUID = 3332933256975814569L;

    protected SymmetricTransPositionGroup(Number[][] initialTable) {
        super(initialTable);
    }

    protected SymmetricTransPositionGroup(SymmetricTransPositionGroup table) {
        super(table);
    }
}
