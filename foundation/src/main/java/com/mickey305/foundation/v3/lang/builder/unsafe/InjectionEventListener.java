package com.mickey305.foundation.v3.lang.builder.unsafe;

public interface InjectionEventListener {
    void before(EasilyAccessibleContainer dest, EasilyContainer src);
    void after(EasilyAccessibleContainer dest, EasilyContainer src);
}
