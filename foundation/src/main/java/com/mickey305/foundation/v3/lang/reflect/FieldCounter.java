package com.mickey305.foundation.v3.lang.reflect;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.lang.reflect.Field;

public class FieldCounter implements Serializable {
  /**
   * use serialVersionUID for interoperability
   */
  private static final long serialVersionUID = -6854747043222633691L;

  private Class<?> target;

  public interface ModifierCallback {
    boolean onModifierIf(int modifier);
  }

  public Class<?> getTarget() {
    return target;
  }

  public void setTarget(Class<?> target) {
    this.target = target;
  }

  public FieldCounter(Class target) {
    setTarget(target);
  }

  /**
   * フィールドをカウントする
   *
   * @param prefix   接頭辞
   * @param callback コールバックインタフェース（修飾子判定用）
   * @return 該当フィールド数
   */
  public int countByPrefix(String prefix, @Nonnull ModifierCallback callback) {
    int fieldCount = 0;
    for (Field field : getTarget().getDeclaredFields()) {
      if (!callback.onModifierIf(field.getModifiers()))
        continue;
      if (field.getName().startsWith(prefix))
        fieldCount++;
    }
    return fieldCount;
  }

  /**
   * フィールドをカウントする
   *
   * @param suffix   接尾辞
   * @param callback コールバックインタフェース（修飾子判定用）
   * @return 該当フィールド数
   */
  public int countBySuffix(String suffix, @Nonnull ModifierCallback callback) {
    int fieldCount = 0;
    for (Field field : getTarget().getDeclaredFields()) {
      if (!callback.onModifierIf(field.getModifiers()))
        continue;
      if (field.getName().endsWith(suffix))
        fieldCount++;
    }
    return fieldCount;
  }

  /**
   * フィールドをカウントする
   *
   * @param contains フィールド名（一部）
   * @param callback コールバックインタフェース（修飾子判定用）
   * @return 該当フィールド数
   */
  public int countContains(String contains, @Nonnull ModifierCallback callback) {
    int fieldCount = 0;
    for (Field field : getTarget().getDeclaredFields()) {
      if (!callback.onModifierIf(field.getModifiers()))
        continue;
      if (field.getName().contains(contains))
        fieldCount++;
    }
    return fieldCount;
  }
}