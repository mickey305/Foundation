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

import com.mickey305.foundation.v3.lang.annotation.marker.Template;
import com.mickey305.foundation.v3.util.Assert;
import com.mickey305.foundation.v3.util.Log;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

@Template
public abstract class InstanceHasOneTransactionTemplate implements Transactional {
  private final ILockableCache<LockType> cache;
  private final String id;
  
  /**
   * インスタンス：トランザクション＝１：１のテンプレートオブジェクトを生成する
   * <p>
   * {@link #createTransactionId()}; This method needs to be called only once in the constructor.</p>
   * <p>Implement the logic to create an instance-specific ID in the constructor block, as in the following example:</p>
   * <pre>{@code
   * public class SampleObject implements SampleListener {
   *   // member
   *   private final Transactional template;
   *   private final SampleListener listener;
   *
   *   // constructor implementation sample
   *   public SampleObject(SampleListener listener) {
   *     ...
   *     template = new InstanceHasOneTransactionTemplate() {
   *       protected String createTransactionId() {
   *         return NaturalInstanceId.gen(SampleObject.class);
   *       }
   *     };
   *     ...
   *     this.listener = listener;
   *   }
   *
   *   ...
   * }}</pre>
   */
  public InstanceHasOneTransactionTemplate() {
    cache = LockManagerFactory.getInstance().get();
    id = this.createTransactionId();
    Assert.requireNonNull(cache);
    Assert.requireNonNull(id);
  }
  
  /**
   * {@inheritDoc}
   */
  @Nullable
  @Override
  public <T> T lock(@Nonnull LockType type, @Nonnull Callback<T> callback) {
    Assert.requireNonNull(type);
    Assert.requireNonNull(callback);
    
    final ILockable<LockType> l = cache.make(id);
    T result;
    
    l.lock(type);
    try {
      result = callback.transact(id);
    } catch (Exception e) {
      if (IS_DEBUG_MODE) {
        Log.e(e.getMessage());
      }
      throw e;
    } finally {
      l.unlock(type);
    }
    
    return result;
  }
  
  /**
   *
   * @return
   */
  @Nonnull
  protected abstract String createTransactionId();
}
