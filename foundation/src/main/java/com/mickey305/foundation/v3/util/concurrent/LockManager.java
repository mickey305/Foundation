/*
 * Copyright (c) 2018. K.Misaki
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

import com.mickey305.foundation.v3.util.Assert;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LockManager<T extends LockType> extends AbstractLockableCache<T> implements ILockableCache<T> {
  private final Map<String, ILockable<T>> lockCache;
  
  public LockManager() {
    lockCache = new ConcurrentHashMap<>();
  }
  
  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public ILockable<T> make(@Nonnull String key) {
    Assert.requireNonNull(key);
    ILockable<T> lock;
    synchronized (lockCache) {
      lock = lockCache.get(key);
      if (lock == null) {
        lock = new LockImpl<>(key);
        lockCache.put(key, lock); // return always null
      }
    }
    Assert.requireNonNull(lock);
    return lock;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public ILockable<T> getAndRemove(@Nonnull String key) {
    Assert.requireNonNull(key);
    ILockable<T> lock;
    synchronized (lockCache) {
      lock = lockCache.remove(key);
    }
    return lock;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public ILockable<T> get(@Nonnull String key) {
    Assert.requireNonNull(key);
    return lockCache.get(key);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int size() {
    return lockCache.size();
  }
  
}
