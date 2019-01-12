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

package com.mickey305.foundation.v3.util.concurrent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface Transactional {
  /**
   * lock interface
   * @param type lock type object
   * @param callback transactional callback
   * @param <T> return object type
   * @return result
   */
  @Nullable <T> T lock(@Nonnull LockType type, @Nonnull Callback<T> callback);
  
  interface Callback<T> {
    /**
     * transaction method
     * @param id transaction-id
     * @return result
     */
    @Nullable T transact(@Nonnull String id);
  }
}