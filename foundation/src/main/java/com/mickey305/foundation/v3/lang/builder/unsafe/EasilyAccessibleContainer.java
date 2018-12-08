package com.mickey305.foundation.v3.lang.builder.unsafe;

import com.mickey305.foundation.v3.util.ClassCollections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EasilyAccessibleContainer extends EasilyContainer {
  private Map<Class<?>, Field[]> fieldMap;
  private Object targetInstance;

  //===----------------------------------------------------------------------------------------------------------===//
  // Constructor                                                                                                    //
  //===----------------------------------------------------------------------------------------------------------===//
  EasilyAccessibleContainer(Object targetInstance) {
    super(targetInstance);
    this.setTargetInstance(targetInstance);
    this.setFieldMap(new HashMap<Class<?>, Field[]>());
    for (Class<?> targetClass : ClassCollections.untilAdam(targetInstance.getClass()))
      this.getFieldMap().put(targetClass, targetClass.getDeclaredFields());
  }

  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//
  public static EasilyAccessibleContainer of(Object targetInstance) {
    return new EasilyAccessibleContainer(targetInstance);
  }

  /**
   * フィールド更新用パイプメソッド
   * <p>引数で与えられたクラスに更新対象のフィールドが存在しない場合、何も処理をしない</p>
   *
   * @param targetClass     更新対象のフィールドが定義されているクラス
   * @param targetFieldName 更新対象のフィールド名
   * @param data            入力データ
   */
  public void updateField(Class<?> targetClass, String targetFieldName, Object data) {
    Field[] targetFields = this.getFieldMap().get(targetClass);
    if (targetFields == null)
      return;
    for (Field field : targetFields) {
      field.setAccessible(true);
      if (field.getName().equals(targetFieldName)) {
        try {
          field.set(this.getTargetInstance(), data);
          return;
        } catch (IllegalAccessException ignore) {
        }
      }
    }
  }

  //===----------------------------------------------------------------------------------------------------------===//
  // Accessor                                                                                                       //
  //===----------------------------------------------------------------------------------------------------------===//
  private Map<Class<?>, Field[]> getFieldMap() {
    return fieldMap;
  }

  private void setFieldMap(Map<Class<?>, Field[]> fieldMap) {
    this.fieldMap = fieldMap;
  }

  private Object getTargetInstance() {
    return targetInstance;
  }

  private void setTargetInstance(Object targetInstance) {
    this.targetInstance = targetInstance;
  }
}
