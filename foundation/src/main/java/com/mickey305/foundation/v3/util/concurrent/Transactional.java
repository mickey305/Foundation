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

/**
 * ロック制御用インターインターフェース
 * @deprecated ネーミングを修正する予定のため、近い将来使用不可になる予定です。
 */
public interface Transactional {
  /**
   * ロックコールバック処理実行メソッド
   * lock interface
   * @param type lock type object
   * @param callback transactional callback
   * @param <T> return object type
   * @return result
   */
  @Nullable <T> T lock(@Nonnull LockType type, @Nonnull Callback<T> callback);
  
  interface Callback<T> {
    /**
     * ロックコールバック処理
     * <p>入力されたキーに紐づくロックオブジェクトを使用し、ロックしている間に必要な処理を実装する</p>
     * transaction method
     * @param id transaction-id
     * @return result
     */
    @Nullable T transact(@Nonnull String id);
  }
}
