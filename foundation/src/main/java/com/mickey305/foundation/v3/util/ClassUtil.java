/*
 * Copyright (c) 2018. K.Misaki
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

package com.mickey305.foundation.v3.util;

public class ClassUtil {
  
  private ClassUtil() {
  }
  
  /**
   * 判定対象クラスがボクシングクラス（プリミティブ型のラッパークラス）かを判定する
   *
   * @param target 判定対象クラス
   * @return ボクシングクラスの場合、true
   */
  public static boolean isBoxing(Class<?> target) {
    return target.equals(Boolean.class)
        || target.equals(Character.class)
        || target.equals(Byte.class)
        || target.equals(Short.class)
        || target.equals(Integer.class)
        || target.equals(Long.class)
        || target.equals(Float.class)
        || target.equals(Double.class);
  }
}
