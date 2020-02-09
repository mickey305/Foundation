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

import javax.annotation.Nullable;

/**
 * Single parent and some children node interface
 * @param <T> node type
 */
public interface ChainedNode<T extends ChainedNode<T>> {
  /**
   * 親オブジェクト取得メソッド
   * @return 親オブジェクト
   */
  @Nullable T parent();
  
  /**
   * 子オブジェクト取得メソッド
   * @return 子オブジェクト
   */
  @Nullable T[] children();
}
