/*
 * Copyright (c) 2017 - 2020 K.Misaki
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

import java.io.IOException;
import java.net.URLConnection;
import java.util.Date;

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
  
  /**
   * ビルド日時取得処理
   *
   * @param instance 解析対象インスタンス
   * @return ビルド日時
   * @throws RuntimeException 実行時例外。{@link Class}リソースのオープンに失敗した場合に投げられる。
   */
  public static <T> Date getBuildDate(final T instance) {
    return getBuildDate(instance.getClass());
  }
  
  /**
   * ビルド日時取得処理
   *
   * @param targetClass 解析対象クラス
   * @return ビルド日時
   * @throws RuntimeException 実行時例外。{@link Class}リソースのオープンに失敗した場合に投げられる。
   */
  public static Date getBuildDate(final Class<?> targetClass) {
    try {
      URLConnection conn = targetClass.getResource(
          targetClass.getSimpleName() + ".class").openConnection();
      return new Date(conn.getLastModified());
    } catch (IOException e) {
      Log.e(e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
