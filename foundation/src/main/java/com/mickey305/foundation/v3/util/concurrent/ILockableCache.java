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

package com.mickey305.foundation.v3.util.concurrent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ILockableCache<T extends LockType> {
  /**
   * 入力されたキーのロックオブジェクトを返却する
   * <p>すでにキャッシュに登録されている場合は、登録オブジェクトを返却する
   * </p>
   * <p>キャッシュに登録されていない場合は、ロックオブジェクトを生成、登録後に登録オブジェクトを返却する
   * </p>
   * @param key キー
   * @return ロックオブジェクト
   */
  @Nonnull
  ILockable<T> make(@Nonnull String key);
  
  /**
   * 入力されたキーのロックオブジェクトを取得し、削除する
   * @param key キー
   * @return ロックオブジェクト
   */
  @Nullable
  ILockable<T> getAndRemove(@Nonnull String key);
  
  /**
   * 入力されたキーのロックオブジェクトを削除し、削除結果を返却する
   * @param key キー
   * @return 削除結果。削除に成功した場合{@code true}、失敗した場合{@code false}を返却する
   */
  boolean remove(@Nonnull String key);
  
  /**
   * 入力されたキーのロックオブジェクトを返却する
   * @param key キー
   * @return ロックオブジェクト。キャッシュに対象のロックオブジェクトが存在しない場合は、{@code null}を返却する
   */
  @Nullable
  ILockable<T> get(@Nonnull String key);
  
  /**
   * デフォルトのロックオブジェクトを返却する
   * <p>すでにキャッシュに登録されている場合は、登録オブジェクトを返却する
   * </p>
   * <p>キャッシュに登録されていない場合は、ロックオブジェクトを生成、登録後に登録オブジェクトを返却する
   * </p>
   * @return ロックオブジェクト
   */
  @Nonnull
  ILockable<T> makeDefault();
  
  /**
   * デフォルトのロックオブジェクトを取得し、削除する
   * @return ロックオブジェクト
   */
  @Nullable
  ILockable<T> getAndRemoveDefault();
  
  /**
   * デフォルトのロックオブジェクトを削除し、削除結果を返却する
   * @return 削除結果。削除に成功した場合{@code true}、失敗した場合{@code false}を返却する
   */
  boolean removeDefault();
  
  /**
   * デフォルトのロックオブジェクトを返却する
   * @return ロックオブジェクト。キャッシュに対象のロックオブジェクトが存在しない場合は、{@code null}を返却する
   */
  @Nullable
  ILockable<T> getDefault();
  
  /**
   * キャッシュサイズ取得処理
   * @return キャッシュサイズ
   */
  int size();
}
