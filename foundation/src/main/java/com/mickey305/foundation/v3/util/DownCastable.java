package com.mickey305.foundation.v3.util;

public interface DownCastable {
    <T extends DownCastable> T downcastTo(Class<T> subClass);
}
