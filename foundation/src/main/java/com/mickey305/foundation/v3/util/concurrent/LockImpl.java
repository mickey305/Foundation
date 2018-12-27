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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
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
  public void lock(T lockType) {
    this.getLock(lockType).lock();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void unlock(T lockType) {
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
    return new NonLock();
  }
  
  private static final class NonLock implements Lock {
    /**
     * {@inheritDoc}
     */
    @Override
    public void lock() {
      throw new RuntimeException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void lockInterruptibly() {
      throw new RuntimeException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryLock() {
      throw new RuntimeException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) {
      throw new RuntimeException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void unlock() {
      throw new RuntimeException();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Condition newCondition() {
      throw new RuntimeException();
    }
  }
  
}
