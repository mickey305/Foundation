package com.mickey305.foundation.v3.util.concurrent;

public class LockManagerFactory {
  private final ILockableCache<LockType> cache;
  
  private LockManagerFactory() {
    //// initialize component task
    cache = new LockManager<>();
    //// switching task
    //*
    
    // default lock object creation
    cache.makeDefault();
    
    //*/
  }
  
  private static final class LockManagerFactoryHolder {
    private static final LockManagerFactory Instance = new LockManagerFactory();
  }
  
  public static LockManagerFactory getInstance() {
    return LockManagerFactoryHolder.Instance;
  }
  
  public ILockableCache<LockType> get() {
    return cache;
  }
}
