package com.mickey305.foundation.v3.util;

public interface ILockableCache<T extends LockType> {
  
  ILockable<T> make(String key);
  
  ILockable<T> getAndRemove(String key);
  
  boolean remove(String key);
  
  ILockable<T> makeDefault();
  
  ILockable<T> getAndRemoveDefault();
  
  boolean removeDefault();
  
  int size();
}
