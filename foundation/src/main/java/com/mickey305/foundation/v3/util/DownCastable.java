package com.mickey305.foundation.v3.util;

public interface DownCastable {
    <T extends DownCastable> T downcast(Class<T> subClass);
}
