package com.mickey305.foundation.v3.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LockManager<T extends LockType> extends AbstractLockableCache<T> implements ILockableCache<T> {
  private final Map<String, ILockable<T>> lockCache;
  
  public LockManager() {
    lockCache = new ConcurrentHashMap<>();
  }
  
  @Override
  public ILockable<T> make(String key) {
    ILockable<T> lock;
    synchronized (lockCache) {
      lock = lockCache.get(key);
      if (lock == null) {
        lock = new LockImpl<>(key);
        lockCache.put(key, lock); // return always null
      }
    }
    return lock;
  }
  
  @Override
  public ILockable<T> getAndRemove(String key) {
    return lockCache.remove(key);
  }
  
  @Override
  public int size() {
    return lockCache.size();
  }
  
}
