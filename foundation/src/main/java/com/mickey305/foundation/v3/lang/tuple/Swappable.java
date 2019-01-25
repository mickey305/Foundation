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

package com.mickey305.foundation.v3.lang.tuple;

import org.apache.commons.lang3.tuple.Pair;

public interface Swappable<L, R> {
  /**
   * 要素入れ替え処理
   * <p>要素を入れ替えたオブジェクトを返却する</p>
   *
   * @return 入れ替え後のオブジェクト
   */
  <T extends Pair<R, L> & Swappable<R, L>> T swap();
}
