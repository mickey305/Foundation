package com.mickey305.foundation.v3.util.concurrent;

public interface ILockable<L extends LockType> {
  /**
   * @param locktype
   */
  void lock(L locktype);
  
  /**
   * @param locktype
   */
  void unlock(L locktype);
}
