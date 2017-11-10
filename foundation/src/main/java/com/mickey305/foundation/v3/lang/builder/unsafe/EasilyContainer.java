package com.mickey305.foundation.v3.lang.builder.unsafe;

import com.mickey305.foundation.v3.lang.reflect.FieldMapCreator;

import java.util.Map;

public class EasilyContainer {
    private Map<Class<?>, Map<String, Object>> fieldDataMap;

    public EasilyContainer(Object targetInstance) {
        this.setFieldDataMap(new FieldMapCreator().createUntilAdam(targetInstance));
    }

    public Map<Class<?>, Map<String, Object>> getFieldDataMap() {
        return fieldDataMap;
    }

    private void setFieldDataMap(Map<Class<?>, Map<String, Object>> fieldDataMap) {
        this.fieldDataMap = fieldDataMap;
    }
}
