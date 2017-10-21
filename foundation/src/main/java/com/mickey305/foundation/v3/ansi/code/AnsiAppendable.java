package com.mickey305.foundation.v3.ansi.code;

public interface AnsiAppendable extends Appendable {
    Appendable append(Escape escape);
}
