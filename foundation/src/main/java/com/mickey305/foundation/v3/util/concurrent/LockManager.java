package com.mickey305.foundation.v3.util.concurrent;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LockManager<T extends LockType> extends AbstractLockableCache<T> implements ILockableCache<T> {
  private final Map<String, ILockable<T>> lockCache;
  
  public LockManager() {
    lockCache = new ConcurrentHashMap<>();
  }
  
  @Nonnull
  @Override
  public ILockable<T> make(@Nonnull String key) {
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
  public ILockable<T> getAndRemove(@Nonnull String key) {
    ILockable<T> lock;
    synchronized (lockCache) {
      lock = lockCache.remove(key);
    }
    return lock;
  }
  
  @Override
  public ILockable<T> get(@Nonnull String key) {
    return lockCache.get(key);
  }
  
  @Override
  public int size() {
    return lockCache.size();
  }
  
}
