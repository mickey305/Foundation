/*
 * Copyright (c) 2019. K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mickey305.foundation.v3.lang.reflect;

import com.mickey305.foundation.v3.util.Assert;

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
    Assert.requireNonNull(callback);
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
    Assert.requireNonNull(callback);
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
    Assert.requireNonNull(callback);
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