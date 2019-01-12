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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstanceHasOneTransactionTemplateTest {
  private List<Thread> threads;
  
  @Before
  public void setUp() throws Exception {
    threads = new ArrayList<>();
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    final int createCycle = 20;
    final SampleTransaction sample = new SampleTransaction();
    for (int i = 0; i < createCycle; i++) {
      final int finalI = i;
      {
        final Runnable task = new Runnable() {
          @Override
          public void run() {
            sample.methodW(finalI);
          }
        };
        threads.add(new Thread(task));
      }
      {
        final Runnable task = new Runnable() {
          @Override
          public void run() {
            sample.methodR(finalI);
          }
        };
        threads.add(new Thread(task));
      }
    }
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
  
  private static class SampleTransaction implements SampleListener {
    private final Transactional template;
    private final SampleListener threadUnsafeObj;
    
    public SampleTransaction() {
      template = new InstanceHasOneTransactionTemplate() {
        @Nonnull
        @Override
        protected String createTransactionId() {
          return NaturalInstanceId.gen(SampleTransaction.class);
        }
      };
      threadUnsafeObj = new ThreadUnsafeSampleTransaction();
    }
    
    public void methodR(final int count) {
      String result = template.lock(LockType.Read, new Transactional.Callback<String>() {
        @Nullable
        @Override
        public String transact(@Nonnull String id) {
          threadUnsafeObj.methodR(count);
          return "Success";
        }
      });
    }
  
    public void methodW(final int count) {
      template.lock(LockType.Write, new Transactional.Callback<Void>() {
        @Nullable
        @Override
        public Void transact(@Nonnull String id) {
          threadUnsafeObj.methodW(count);
          return null;
        }
      });
    }
  }
  
  private static class ThreadUnsafeSampleTransaction implements SampleListener {
    public void methodR(final int id) {
      try {
        Log.i("[" + String.format("%03d",id) + "] method [R] start.");
        Thread.sleep(10);
        Log.i("[" + String.format("%03d",id) + "] method [R] finished.");
      } catch (InterruptedException e) {
        Log.e(e);
        throw new RuntimeException(e);
      }
    }
    
    public void methodW(final int id) {
      try {
        Log.i("[" + String.format("%03d",id) + "] method [W] start.");
        Thread.sleep(10);
        Log.i("[" + String.format("%03d",id) + "] method [W] finished.");
      } catch (InterruptedException e) {
        Log.e(e);
        throw new RuntimeException(e);
      }
    }
  }
  
  private interface SampleListener {
    void methodR(final int id);
    void methodW(final int id);
  }
}