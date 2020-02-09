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

import com.mickey305.foundation.v3.util.Assert;

import javax.annotation.Nonnull;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class LockImpl<T extends LockType> implements ILockable<T> {
  private final ReentrantReadWriteLock lock;
  private final String key;
  
  public LockImpl(String key) {
    this.lock = new ReentrantReadWriteLock();
    this.key = key;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void lock(@Nonnull T lockType) {
    Assert.requireNonNull(lockType);
    
    this.getLock(lockType).lock();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void unlock(@Nonnull T lockType) {
    Assert.requireNonNull(lockType);
    
    this.getLock(lockType).unlock();
  }
  
  /**
   * @param lockType
   * @return
   */
  private Lock getLock(T lockType) {
    if (lockType == LockType.Read) return lock.readLock();
    if (lockType == LockType.Write) return lock.writeLock();
    
    // throwing RuntimeException
    throw new IllegalArgumentException("unsupported lockType-value.");
  }
}
