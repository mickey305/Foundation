package com.mickey305.foundation.v3.util;

public interface ILockable<L extends LockType> {
  
  void lock(L locktype);
  
  void unlock(L locktype);
}
