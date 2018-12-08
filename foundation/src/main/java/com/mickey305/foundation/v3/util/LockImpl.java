package com.mickey305.foundation.v3.util;

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
  
  @Override
  public void lock(T lockType) {
    this.getLock(lockType).lock();
  }
  
  @Override
  public void unlock(T lockType) {
    this.getLock(lockType).unlock();
  }
  
  /**
   *
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
    @Override
    public void lock() {
      throw new RuntimeException();
    }
    
    @Override
    public void lockInterruptibly() {
      throw new RuntimeException();
    }
    
    @Override
    public boolean tryLock() {
      throw new RuntimeException();
    }
    
    @Override
    public boolean tryLock(long time, TimeUnit unit) {
      throw new RuntimeException();
    }
    
    @Override
    public void unlock() {
      throw new RuntimeException();
    }
    
    @Override
    public Condition newCondition() {
      throw new RuntimeException();
    }
  }
  
}
