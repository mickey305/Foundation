package com.mickey305.foundation.v3.util;

import javax.annotation.Nonnull;

public abstract class AbstractLockableCache<T extends LockType> implements ILockableCache<T> {
  private static final String TAG = AbstractLockableCache.class.getName();
  
  @Override
  public boolean remove(@Nonnull String key) {
    return this.getAndRemove(key) != null;
  }
  
  /*
   * default method implementation
   *
   */
  
  @Nonnull
  @Override
  public ILockable<T> makeDefault() {
    return this.make(TAG);
  }
  
  @Override
  public ILockable<T> getAndRemoveDefault() {
    return this.getAndRemove(TAG);
  }
  
  @Override
  public boolean removeDefault() {
    return this.remove(TAG);
  }
  
  @Override
  public ILockable<T> getDefault() {
    return this.get(TAG);
  }
}
