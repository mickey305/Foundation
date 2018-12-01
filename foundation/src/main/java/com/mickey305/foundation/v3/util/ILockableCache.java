package com.mickey305.foundation.v3.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ILockableCache<T extends LockType> {
  
  @Nonnull
  ILockable<T> make(@Nonnull String key);
  
  @Nullable
  ILockable<T> getAndRemove(@Nonnull String key);
  
  boolean remove(@Nonnull String key);
  
  @Nullable
  ILockable<T> get(@Nonnull String key);
  
  @Nonnull
  ILockable<T> makeDefault();
  
  @Nullable
  ILockable<T> getAndRemoveDefault();
  
  boolean removeDefault();
  
  @Nullable
  ILockable<T> getDefault();
  
  int size();
}
