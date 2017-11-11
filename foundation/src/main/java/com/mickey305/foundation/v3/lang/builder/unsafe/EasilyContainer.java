package com.mickey305.foundation.v3.lang.builder.unsafe;

import com.mickey305.foundation.v3.lang.reflect.FieldMapCreator;

import java.util.Map;

public class EasilyContainer {
    private Map<Class<?>, Map<String, Object>> fieldDataMap;

    EasilyContainer(Object targetInstance) {
        this.setFieldDataMap(FieldMapCreator.get().createUntilAdam(targetInstance));
    }

    public static EasilyContainer of (Object targetInstance) {
        return new EasilyContainer(targetInstance);
    }

    public Map<Class<?>, Map<String, Object>> getFieldDataMap() {
        return fieldDataMap;
    }

    private void setFieldDataMap(Map<Class<?>, Map<String, Object>> fieldDataMap) {
        this.fieldDataMap = fieldDataMap;
    }
}