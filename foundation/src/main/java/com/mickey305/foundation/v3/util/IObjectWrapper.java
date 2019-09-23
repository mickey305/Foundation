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

package com.mickey305.foundation.v3.util;

/**
 * オブジェクトラッピング用インターフェース
 * @param <T> 内部保持オブジェクトの総称型
 */
public interface IObjectWrapper<T> {
  /**
   * セッターメソッド
   * @param object 内部保持オブジェクト
   */
  void set(T object);
  
  /**
   * ゲッターメソッド
   * @return 内部保持オブジェクト
   */
  T get();
}
