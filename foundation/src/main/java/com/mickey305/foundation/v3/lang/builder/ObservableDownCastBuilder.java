package com.mickey305.foundation.v3.lang.builder;

import com.mickey305.foundation.v3.lang.reflect.FieldMapCreator;

import java.lang.reflect.Field;
import java.util.Map;

public class ObservableDownCastBuilder {

    /**
     * ダウンキャスト実装用のリフレクションメソッド
     * @param destClass キャスト先のクラス
     * @param srcInstance キャスト元（キャスト対象）のインスタンス
     * @param listener インジェクションイベントリスナー
     * @param <S> 移動元の総称型
     * @param <D> 移動先の総称型
     * @return キャスト先（ダウンキャスト後）のインスタンス
     */
    public static <S, D> D reflectionDownCast(Class<D> destClass, S srcInstance, InjectionEventListener listener) {
        // ---> Input data check
        if (!srcInstance.getClass().isAssignableFrom(destClass) || srcInstance.getClass().equals(destClass))
            return null;

        // ---> Dest-Instance creation
        D subInstance = DownCastBuilder.createInstanceChallenge(destClass);
        // ---> Dest-Instance injection of Src fields
        if (subInstance != null) {
            subInstance = reflectionDownCast(subInstance, srcInstance, listener);
        }
        return subInstance;
    }

    /**
     * ダウンキャスト実装用のリフレクションメソッド
     * @param destInstance キャスト先のインスタンス
     * @param srcInstance キャスト元（キャスト対象）のインスタンス
     * @param listener インジェクションイベントリスナー
     * @param <S> 移動元の総称型
     * @param <D> 移動先の総称型
     * @return キャスト先（ダウンキャスト後）のインスタンス
     */
    public static <S, D> D reflectionDownCast(D destInstance, S srcInstance, InjectionEventListener listener) {
        FieldMapCreator fieldMapCreator = new FieldMapCreator();
        Object adam = new Object();
        // ---> Input data check
        if (!srcInstance.getClass().isAssignableFrom(destInstance.getClass())
                || srcInstance.getClass().equals(destInstance.getClass()))
            return null;

        // ---> Dest-Instance injection of Src fields
        Map<Class<?>, Map<String, Object>> superFieldsMap = fieldMapCreator.createUntilAdam(srcInstance);
        // before injection
        if (listener != null)
            listener.before(fieldMapCreator.create(destInstance, adam), superFieldsMap);
        Class<?> injectionTargetClass = srcInstance.getClass();
        // e.g. expected situation
        //+-----+------+---------------+------+--------------------+
        // cast |      |               |      |
        // flow |dest  |field          |src   |others
        //+-----+------+---------------+------+--------------------+
        //      |Object|<--copy&check--|Object|
        //      |ClassA|<--copy&check--|ClassA|
        //      |ClassB|<--copy&check--|ClassB|
        //   v  |ClassC|<--copy&check--|ClassC|<-----srcInstance
        //   v  |ClassD|<--check       |      |
        //   v  |ClassE|<--check       |      |
        //   V  |ClassF|<--check       |      |
        //   V  |ClassG|<--check       |      |
        //   V  |ClassH|<--check       |      |<-----destInstance
        //+-----+------+---------------+------+--------------------+
        // - copy is sallow-copy
        // - check logic impl with InjectionEventListener
        for (;;) {
            Field[] injectionTargetFields = injectionTargetClass.getDeclaredFields();
            // do injection
            for (Field injectionTargetField : injectionTargetFields) {
                injectionTargetField.setAccessible(true);
                String fieldName = injectionTargetField.getName();
//                if (fieldName.equals("this$0")) continue;
                try {
                    // Sallow Copy: from srcInstance(superInstance) to destInstance(destInstance)
                    injectionTargetField.set(destInstance, superFieldsMap.get(injectionTargetClass).get(fieldName));
                } catch (IllegalAccessException ignored) {}
            }
            injectionTargetClass = injectionTargetClass.getSuperclass();
            if (injectionTargetClass == null) break;
        }
        // after injection
        if (listener != null)
            listener.after(fieldMapCreator.create(destInstance, adam), superFieldsMap);
        return destInstance;
    }
}
