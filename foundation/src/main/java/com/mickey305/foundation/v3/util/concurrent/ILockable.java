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

public interface ILockable<L extends LockType> {
  /**
   * ロック開始処理
   * <p>入力されたロック方法で並列処理時のロック処理のエントリポイントとして実行する
   * </p>
   * @param lockType ロック方法
   */
  void lock(@Nonnull L lockType);
  
  /**
   * ロック解除処理
   * <p>入力されたロック方法でロックされている処理の解除を実行する
   * </p>
   * @param lockType ロック方法
   */
  void unlock(@Nonnull L lockType);
}
