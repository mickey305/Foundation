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

package com.mickey305.foundation.v3.lang;

public interface IThrowableProxyObject<T extends Throwable> {
  /**
   * プロキシに包含する前のオブジェクト型が引数で与えられた型と一致しているかを検証する
   * @param throwableClass 検証で比較する{@link java.lang.Class}を設定する
   * @return 検証結果
   */
  boolean typeOfOriginalObjectEquals(Class<? extends T> throwableClass);
}
