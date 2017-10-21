package com.mickey305.foundation.v3.ansi.code;

import com.mickey305.foundation.v3.validation.annotation.EscapeReject;

abstract class AbstractAnsiStringBuilder implements AnsiAppendable, CharSequence{
    // nop
    AbstractAnsiStringBuilder() {}

    protected void validate(@EscapeReject Object object) {}

    protected void validate(@EscapeReject CharSequence charSequence) {}
}
