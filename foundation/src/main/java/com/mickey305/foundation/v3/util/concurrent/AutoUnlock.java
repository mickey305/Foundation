/*
 * Copyright (c) 2019. K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mickey305.foundation.v3.util.concurrent;

import com.mickey305.foundation.v3.util.Log;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

/**
 * auto unlock class on the try-with-resources grammar
 * <pre>{@code
 * // create lock object
 * ILockable<LockType> lockObject = ...
 * ...
 * // read lock
 * try(AutoUnlock ignored = new AutoUnlock.ReadLock(lockObject)) {
 *   // locked block of read-lock
 * }
 * ...
 * // write lock
 * try(AutoUnlock ignored = new AutoUnlock.WriteLock(lockObject)) {
 *   // locked block of write-lock
 * }
 * ...
 * }</pre>
 */
public abstract class AutoUnlock implements AutoCloseable {
  private final LockType type;
  private final ILockable<LockType> lockable;
  
  public static final class ReadLock extends AutoUnlock {
    public ReadLock(ILockable<LockType> lockable) { super(LockType.Read, lockable); }
  }
  
  public static final class WriteLock extends AutoUnlock {
    public WriteLock(ILockable<LockType> lockable) { super(LockType.Write, lockable); }
  }
  
  protected AutoUnlock(LockType type, ILockable<LockType> lockable) {
    this.lockable = lockable;
    this.type = type;
    
    // lock start
    this.lockable.lock(this.type);
    
    if (IS_DEBUG_MODE) {
      Log.d(((type == LockType.Read)? "[R]" : "[W]") + " locked");
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    try {
      // lock end
      lockable.unlock(type);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  
    if (IS_DEBUG_MODE) {
      Log.d(((type == LockType.Read)? "[R]" : "[W]") + " unlocked");
    }
  }
}
