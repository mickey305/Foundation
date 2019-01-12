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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class LockManagerTest {
  private static final int LOCK_OBJECT_COUNT = 10;
  private static final int THREAD_PER_LOCK_OBJECT = 10;
  private List<Thread> threads;
  
  @Before
  public void setUp() throws Exception {
    Log.i("#####################################################################################################");
    threads = new ArrayList<>();
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void check_01_01() throws Exception {
    {
      LockManager<LockType> lm = new LockManager<>();
      ILockable<LockType> l = lm.makeDefault();
      l.lock(LockType.Read);
      l.unlock(LockType.Read);
    }
    {
      LockManager<LockType> lm = new LockManager<>();
      ILockable<LockType> l = lm.makeDefault();
      l.lock(LockType.Write);
      l.unlock(LockType.Write);
    }
    {
      LockManager<LockType> lm = new LockManager<>();
      ILockable<LockType> l1 = lm.make("lock1");
      ILockable<LockType> l2 = lm.make("lock2");
      l1.lock(LockType.Write);
      l2.lock(LockType.Write);
      l1.unlock(LockType.Write);
      l2.unlock(LockType.Write);
    }
    {
      LockManager<LockType> lm = new LockManager<>();
      ILockable<LockType> l1 = lm.make("lock1");
      ILockable<LockType> l2 = lm.make("lock2");
      l1.lock(LockType.Write);
      l2.lock(LockType.Write);
      l2.unlock(LockType.Write);
      l1.unlock(LockType.Write);
    }
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    for (int i = 0; i < LOCK_OBJECT_COUNT; i++) {
      final AbsLockSample sample = new LockDefault1();
      final int finalI = i;
      final Callback callback = new Callback() {
        @Override
        public void transact(String notify, long id) {
          Log.i(notify + " id[" + id + "] - test task 1-1 obj[" + finalI + "]");
        }
      };
      // create thread objects
      for (int j = 0; j < THREAD_PER_LOCK_OBJECT; j++) {
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.read(callback);
            }
          };
          threads.add(new Thread(task));
        }
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.write(callback);
            }
          };
          threads.add(new Thread(task));
        }
      }
    }
    // thread execute
    this.invoke(threads);
  }
  
  @Test
  public void testCase_01_02() throws Exception {
    for (int i = 0; i < LOCK_OBJECT_COUNT; i++) {
      final AbsLockSample sample = new LockDefault1();
      final int finalI = i;
      final Callback callback = new Callback() {
        @Override
        public void transact(String notify, long id) {
          Log.i(notify + " id[" + id + "] - test task 1-2 obj[" + finalI + "]");
        }
      };
      // create thread objects
      for (int j = 0; j < THREAD_PER_LOCK_OBJECT; j++) {
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.read(callback);
            }
          };
          threads.add(new Thread(task));
        }
      }
    }
    // thread execute
    this.invoke(threads);
  }
  
  @Test
  public void testCase_02_01() throws Exception {
    for (int i = 0; i < LOCK_OBJECT_COUNT; i++) {
      final AbsLockSample sample = new LockDefault2();
      final int finalI = i;
      final Callback callback = new Callback() {
        @Override
        public void transact(String notify, long id) {
          Log.i(notify + " id[" + id + "] - test task 2-1 obj[" + finalI + "]");
        }
      };
      // create thread objects
      for (int j = 0; j < THREAD_PER_LOCK_OBJECT; j++) {
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.read(callback);
            }
          };
          threads.add(new Thread(task));
        }
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.write(callback);
            }
          };
          threads.add(new Thread(task));
        }
      }
    }
    // thread execute
    this.invoke(threads);
  }
  
  @Test
  public void testCase_02_02() throws Exception {
    for (int i = 0; i < LOCK_OBJECT_COUNT; i++) {
      final AbsLockSample sample = new LockDefault2();
      final int finalI = i;
      final Callback callback = new Callback() {
        @Override
        public void transact(String notify, long id) {
          Log.i(notify + " id[" + id + "] - test task 2-2 obj[" + finalI + "]");
        }
      };
      // create thread objects
      for (int j = 0; j < THREAD_PER_LOCK_OBJECT; j++) {
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.read(callback);
            }
          };
          threads.add(new Thread(task));
        }
      }
    }
    // thread execute
    this.invoke(threads);
  }
  
  @Test
  public void testCase_03_01() throws Exception {
    for (int i = 0; i < LOCK_OBJECT_COUNT; i++) {
      final AbsLockSample sample = new LockSample1();
      final int finalI = i;
      final Callback callback = new Callback() {
        @Override
        public void transact(String notify, long id) {
          Log.i(notify +  " id[" + id + "] - test task 3-1 obj[" + finalI + "]");
        }
      };
      // create thread objects
      for (int j = 0; j < THREAD_PER_LOCK_OBJECT; j++) {
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.read(callback);
            }
          };
          threads.add(new Thread(task));
        }
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.write(callback);
            }
          };
          threads.add(new Thread(task));
        }
      }
    }
    // thread execute
    this.invoke(threads);
  }
  
  @Test
  public void testCase_03_02() throws Exception {
    for (int i = 0; i < LOCK_OBJECT_COUNT; i++) {
      final AbsLockSample sample = new LockSample1();
      final int finalI = i;
      final Callback callback = new Callback() {
        @Override
        public void transact(String notify, long id) {
          Log.i(notify + " id[" + id + "] - test task 3-2 obj[" + finalI + "]");
        }
      };
      // create thread objects
      for (int j = 0; j < THREAD_PER_LOCK_OBJECT; j++) {
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.read(callback);
            }
          };
          threads.add(new Thread(task));
        }
      }
    }
    // thread execute
    this.invoke(threads);
  }
  
  @Test
  public void testCase_04_01() throws Exception {
    for (int i = 0; i < LOCK_OBJECT_COUNT; i++) {
      final AbsLockSample sample = new LockSample2();
      final int finalI = i;
      final Callback callback = new Callback() {
        @Override
        public void transact(String notify, long id) {
          Log.i(notify + " id[" + id + "] - test task 4-1 obj[" + finalI + "]");
        }
      };
      // create thread objects
      for (int j = 0; j < THREAD_PER_LOCK_OBJECT; j++) {
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.read(callback);
            }
          };
          threads.add(new Thread(task));
        }
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.write(callback);
            }
          };
          threads.add(new Thread(task));
        }
      }
    }
    // thread execute
    this.invoke(threads);
  }
  
  @Test
  public void testCase_04_02() throws Exception {
    for (int i = 0; i < LOCK_OBJECT_COUNT; i++) {
      final AbsLockSample sample = new LockSample2();
      final int finalI = i;
      final Callback callback = new Callback() {
        @Override
        public void transact(String notify, long id) {
          Log.i(notify + " id[" + id + "] - test task 4-2 obj[" + finalI + "]");
        }
      };
      // create thread objects
      for (int j = 0; j < THREAD_PER_LOCK_OBJECT; j++) {
        {
          final Runnable task = new Runnable() {
            @Override
            public void run() {
              sample.read(callback);
            }
          };
          threads.add(new Thread(task));
        }
      }
    }
    // thread execute
    this.invoke(threads);
  }
  
  private void invoke(List<Thread> threads) throws Exception {
    Collections.shuffle(threads);
    for (Thread thread : threads) {
      thread.start();
    }
    for (Thread thread : threads) {
      thread.join();
    }
  }
  
  // test class ////////////////////////////////////////////////////////////////////////////////////////////////////////
  private class LockDefault1 extends AbsLockSample {
    public LockDefault1() {
      super();
      Log.i("created " + LockDefault1.class.getName());
    }
    
    @Override
    public ILockable<LockType> getLockObject() {
      ILockableCache<LockType> lm = LockManagerFactory.getInstance().get();
      return lm.makeDefault();
    }
  }
  
  private class LockDefault2 extends AbsLockSample {
    public LockDefault2() {
      super();
      Log.i("created " + LockDefault2.class.getName());
    }
    
    @Override
    public ILockable<LockType> getLockObject() {
      LockManager<LockType> lm = new LockManager<>();
      return lm.makeDefault();
    }
  }
  
  private class LockSample1 extends AbsLockSample {
    private final String id;
    
    public LockSample1() {
      super();
      id = NaturalInstanceId.gen(LockSample1.class);
      Log.i("created " + LockSample1.class.getName());
    }
    
    @Override
    public ILockable<LockType> getLockObject() {
      ILockableCache<LockType> lm = LockManagerFactory.getInstance().get();
      return lm.make(id);
    }
  }
  
  private class LockSample2 extends AbsLockSample {
    public LockSample2() {
      super();
      Log.i("created " + LockSample2.class.getName());
    }
    
    public String getId() {
      return NaturalObjectHashId.gen(this);
    }
    
    @Override
    public ILockable<LockType> getLockObject() {
      ILockableCache<LockType> lm = LockManagerFactory.getInstance().get();
      return lm.make(this.getId());
    }
  }
  
  private abstract static class AbsLockSample implements Transactional<Void> {
    private static final int FROM = 10;
    private static final int TO = 30;
    private final int sleep;
    private static final AtomicLong COUNT = new AtomicLong(0);
    private final long count;
    
    public AbsLockSample() {
      count = COUNT.getAndIncrement();
      sleep = (int) (Math.random() * (TO - FROM)) + FROM;
      Log.i("interval task sleep - id[" + count + "] = " + sleep + "(ms)");
    }
    
    public Void read(Callback callback) {
      ILockable<LockType> l = this.getLockObject();
      l.lock(LockType.Read);
      Log.d("R lock start id[" + count + "]");
      try {
        Thread.sleep(sleep);
        callback.transact("R", count);
        Thread.sleep(sleep);
      } catch (Exception e) {
        Log.e(e.getMessage());
        throw new RuntimeException(e);
      } finally {
        Log.d("R lock end id[" + count + "]");
        l.unlock(LockType.Read);
      }
      return null;
    }
    
    public Void write(Callback callback) {
      ILockable<LockType> l = this.getLockObject();
      l.lock(LockType.Write);
      Log.d("W lock start id[" + count + "]");
      try {
        Thread.sleep(sleep);
        callback.transact("W", count);
        Thread.sleep(sleep);
      } catch (Exception e) {
        Log.e(e.getMessage());
        throw new RuntimeException(e);
      } finally {
        Log.d("W lock end id[" + count + "]");
        l.unlock(LockType.Write);
      }
      return null;
    }
    
    public abstract ILockable<LockType> getLockObject();
  }
  
  private interface Transactional<T> {
    T read(Callback callback);
    T write(Callback callback);
  }
  private interface Callback {
    void transact(String notify, long id);
  }
}