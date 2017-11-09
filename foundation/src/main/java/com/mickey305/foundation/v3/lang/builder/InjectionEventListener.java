package com.mickey305.foundation.v3.lang.builder;

import java.util.Map;

public interface InjectionEventListener {
    void before(Map<Class<?>, Map<String, Object>> dest, Map<Class<?>, Map<String, Object>> src);
    void after(Map<Class<?>, Map<String, Object>> dest, Map<Class<?>, Map<String, Object>> src);
}
