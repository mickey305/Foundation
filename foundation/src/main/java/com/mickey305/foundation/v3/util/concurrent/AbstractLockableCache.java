package com.mickey305.foundation.v3.util.concurrent;

import javax.annotation.Nonnull;

public abstract class AbstractLockableCache<T extends LockType> implements ILockableCache<T> {
  private static final String TAG = AbstractLockableCache.class.getName();
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(@Nonnull String key) {
    return this.getAndRemove(key) != null;
  }
  
  /*
   * default method implementation
   *
   */
  
  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public ILockable<T> makeDefault() {
    return this.make(TAG);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public ILockable<T> getAndRemoveDefault() {
    return this.getAndRemove(TAG);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean removeDefault() {
    return this.remove(TAG);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public ILockable<T> getDefault() {
    return this.get(TAG);
  }
}
