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

package com.mickey305.foundation.v3.util.collections;

public interface IRefreshable<K> {
  /**
   * 要素リフレッシュ処理
   * <p>キー情報をもとに、LRUデータ構造などでの要素アクセス処理を実装する
   * </p>
   * <p>このメソッドでアクセスされたキー情報およびキーに紐づく情報は、LRUキャッシュ上で削除対象の優先度が下がります
   * （LRUキャッシュ上から、キー情報およびキーに紐づく情報が削除されにくくなります）
   * </p>
   * @param key 対象要素のキー
   */
  void refreshData(K key);
}
