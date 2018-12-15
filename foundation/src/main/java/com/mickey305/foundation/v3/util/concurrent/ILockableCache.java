package com.mickey305.foundation.v3.util.concurrent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ILockableCache<T extends LockType> {
  /**
   * @param key
   * @return
   */
  @Nonnull
  ILockable<T> make(@Nonnull String key);
  
  /**
   * @param key
   * @return
   */
  @Nullable
  ILockable<T> getAndRemove(@Nonnull String key);
  
  /**
   * @param key
   * @return
   */
  boolean remove(@Nonnull String key);
  
  /**
   * @param key
   * @return
   */
  @Nullable
  ILockable<T> get(@Nonnull String key);
  
  /**
   * @return
   */
  @Nonnull
  ILockable<T> makeDefault();
  
  /**
   * @return
   */
  @Nullable
  ILockable<T> getAndRemoveDefault();
  
  /**
   * @return
   */
  boolean removeDefault();
  
  /**
   * @return
   */
  @Nullable
  ILockable<T> getDefault();
  
  /**
   * @return
   */
  int size();
}
