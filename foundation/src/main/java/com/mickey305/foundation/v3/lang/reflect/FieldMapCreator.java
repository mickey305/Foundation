package com.mickey305.foundation.v3.lang.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldMapCreator {

    //===----------------------------------------------------------------------------------------------------------===//
    // Constructor                                                                                                    //
    //===----------------------------------------------------------------------------------------------------------===//
    private FieldMapCreator() {}

    //===----------------------------------------------------------------------------------------------------------===//
    // Methods                                                                                                        //
    //===----------------------------------------------------------------------------------------------------------===//
    public static FieldMapCreator get() {
        return FieldMapCreatorHolder.INSTANCE;
    }

    /**
     * フィールドデータ参照用マップを作成する
     * <p>開始インスタンスと終了インスタンス間とこれら自身の全フィールドデータの参照用マップを生成する。
     * 継承関係が存在しない場合は、開始インスタンスから{@link Object}までの全フィールドが生成対象となる</p>
     * @param fromInstance 開始インスタンス
     * @param toInstance 終了インスタンス
     * @return クラス毎のフィールドデータ参照用マップ
     */
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

    /**
     * フィールドデータ参照用マップを作成する
     * <p>インスタンスの全フィールドデータの参照用マップを生成する</p>
     * @param instance インスタンス
     * @return フィールドデータ参照用マップ
     */
    public Map<String, Object> create(Object instance) {
        return this.create(instance, instance.getClass());
    }

    /**
     * フィールドデータ参照用マップを作成する
     * <p>マップ生成対象クラスの全フィールドデータの参照用マップを生成する。
     * インスタンスはマップ生成対象クラスもしくは、その子クラスのオブジェクトとする</p>
     * @param instance インスタンス
     * @param clazz マップ生成対象クラス
     * @return フィールドデータ参照用マップ
     */
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

    /**
     * フィールドデータ参照用マップを作成する
     * <p>開始インスタンスから{@link Object}までとこれら自身の全フィールドデータの参照用マップを生成する</p>
     * @param fromInstance 開始インスタンス
     * @return クラス毎のフィールドデータ参照用マップ
     */
    public Map<Class<?>, Map<String, Object>> createUntilAdam(Object fromInstance) {
        return this.create(fromInstance, new Object());
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Innerclass                                                                                                     //
    //===----------------------------------------------------------------------------------------------------------===//
    private static class FieldMapCreatorHolder {
        private static final FieldMapCreator INSTANCE = new FieldMapCreator();
    }
}
