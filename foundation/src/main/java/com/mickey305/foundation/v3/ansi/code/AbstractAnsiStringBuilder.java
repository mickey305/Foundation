package com.mickey305.foundation.v3.ansi.code;

abstract class AbstractAnsiStringBuilder implements AnsiAppendable, CharSequence {
    protected static final String ANSI_CODE_REGEX = "\u001b\\[[;\\d]*m";

    // nop
    AbstractAnsiStringBuilder() {}
}
