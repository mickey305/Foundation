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

package com.mickey305.foundation.v3.util;

public abstract class AbstractCancelableCommand<R> implements Executable<R>, Cancelable<R> {
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//
  
  /**
   * 試行用ロジックを実行
   *
   * @param callback 試行用ロジック実装インターフェース
   * @return 当コマンド取り消し実行結果
   */
  public R trial(Callback<R> callback) {
    if (callback == null) {
      return null;
    }
    R result = this.execute();
    callback.onExecuted(result);
    return this.cancel();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public abstract R execute();
  
  /**
   * {@inheritDoc}
   */
  @Override
  public abstract R cancel();
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Innerclass                                                                                                     //
  //===----------------------------------------------------------------------------------------------------------===//
  public interface Callback<R> {
    /**
     * 試行用ロジック実装メソッド
     *
     * @param executionResult 当コマンド実行結果
     */
    void onExecuted(R executionResult);
  }
}
