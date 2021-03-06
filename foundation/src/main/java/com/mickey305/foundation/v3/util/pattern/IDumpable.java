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

package com.mickey305.foundation.v3.util.pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IDumpable {
  /**
   * オブジェクトダンプ実行コールバック
   * @param targetObject 対象のオブジェクト
   * @param callback コールバック
   * @param <T> 対象のオブジェクト型
   */
  <T> void dump(@Nullable T targetObject, @Nullable Callback callback);
  
  interface Callback {
    /**
     * ダンプ処理実装メソッド
     * @param lineResult 一行分のダンプ対象文字列ビルダ
     */
    void impl(@Nonnull StringBuilder lineResult);
  }
}
