package com.mickey305.foundation.v3.lang.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldMapCreator {

    public FieldMapCreator() {}

    public Map<Class<?>, Map<String, Object>> create(Object fromInstance, Object toInstance) {
        Map<String, Object> fieldMap;
        Map<Class<?>, Map<String, Object>> result = new HashMap<>();
        Object superInstance = fromInstance;
        Object subInstance = toInstance;
        if (toInstance.getClass().isAssignableFrom(fromInstance.getClass())) {
            superInstance = toInstance;
            subInstance = fromInstance;
        }
        Class<?> tmpClass = subInstance.getClass();
        while (!tmpClass.equals(superInstance.getClass())) {
            fieldMap = this.create(subInstance, tmpClass);
            result.put(tmpClass, fieldMap);
            tmpClass = tmpClass.getSuperclass();
            if (tmpClass == null) break;
        }
        if (tmpClass != null) {
            fieldMap = this.create(subInstance, tmpClass);
            result.put(tmpClass, fieldMap);
        }
        return result;
    }

    public Map<String, Object> create(Object instance) {
        return this.create(instance, instance.getClass());
    }

    public Map<String, Object> create(Object instance, Class<?> clazz) {
        Map<String, Object> fieldMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            try {
                fieldMap.put(field.getName(), field.get(instance));
            } catch (IllegalAccessException ignored) {}
        }
        return fieldMap;
    }

    public Map<Class<?>, Map<String, Object>> createUntilAdam(Object fromInstance) {
        return this.create(fromInstance, new Object());
    }
}
