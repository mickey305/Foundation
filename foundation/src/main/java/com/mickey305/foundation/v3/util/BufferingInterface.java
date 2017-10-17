package com.mickey305.foundation.v3.util;

public interface BufferingInterface<R> extends Executable<R> {
    R undo();
    R redo();
}
