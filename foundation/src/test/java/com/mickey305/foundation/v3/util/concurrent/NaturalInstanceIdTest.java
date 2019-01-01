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
import java.util.List;

public class NaturalInstanceIdTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCse_01_01() throws Exception {
    final List<Thread> threads = new ArrayList<>();
    Runnable task;
    // task1
    task = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          final String id = new SampleA().id;
        }
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    threads.add(new Thread(task));
    // task 2
    task = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          final String id = new SampleA().id;
        }
      }
    };
    threads.add(new Thread(task));
    // task 3
    task = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 20; i++) {
          new SampleB();
        }
      }
    };
    threads.add(new Thread(task));
    // task 4
    task = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          final String id = new SampleA().id;
        }
      }
    };
    threads.add(new Thread(task));
    for (Thread thread : threads) {
      thread.start();
    }
    for (Thread thread : threads) {
      thread.join();
    }
  }
  
  private static class SampleA {
    public final String id;
    
    public SampleA() {
      id = NaturalInstanceId.gen(this);
      Log.i("instance id: " + id);
    }
  }
  
  private static class SampleB {
    public SampleB() {
      String id;
      id = NaturalInstanceId.gen(this);
      Log.i("instance id: " + id);
    }
  }
}
