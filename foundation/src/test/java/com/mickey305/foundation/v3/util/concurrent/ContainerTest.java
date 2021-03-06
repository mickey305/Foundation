/*
 * Copyright (c) 2017 - 2020 K.Misaki
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

import com.mickey305.foundation.v3.util.Executable;
import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ContainerTest {
  private static final int CMD_CNT = 100;
  private List<Executable<Integer>> commands;
  
  @Before
  public void setUp() throws Exception {
    commands = new ArrayList<>();
    for (int i = 0; i < CMD_CNT; i++)
      commands.add(new TestCommand(i));
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    Container<Integer> container = new Container<>(commands);
    container.setOnFinishEventListener((timeOverCommands, resultManager) -> {
      Log.i("main: result1...");
      for (Pair<Executable<Integer>, Integer> entry : resultManager.getResultPool()) {
        Log.i("key=" + entry.getKey() + ", value=" + entry.getValue());
      }
      Assert.assertEquals(CMD_CNT, timeOverCommands.size() + resultManager.getResultPool().size());
      Assert.assertEquals(Integer.valueOf(0), resultManager.findBy(commands.get(0)));
      Assert.assertEquals(Integer.valueOf(1), resultManager.findBy(commands.get(1)));
      Assert.assertEquals(Integer.valueOf(0), resultManager.findBy(commands.get(2)));
    });
    Thread th = new Thread(container);
    Log.i("main: tread1 start");
    th.start();
    Thread.sleep((long) (2.5 * 1000));
    container.shutdown();
    final int execCnt = container.resultManager().getResultPool().size();
    th.join();
    Log.i("main: tread1 end");
    th = new Thread(container);
    container.reactivation();
    container.setOnStepFinishEventListener(results -> results.last().equals(1));
    container.setOnFinishEventListener((timeOverCommands, resultManager) -> {
      Log.i("main: result2...");
      for (Pair<Executable<Integer>, Integer> entry : resultManager.getResultPool()) {
        Log.i("key=" + entry.getKey() + ", value=" + entry.getValue());
      }
      Assert.assertEquals(CMD_CNT - execCnt,
          timeOverCommands.size() + resultManager.getResultPool().size());
      Assert.assertEquals(Integer.valueOf(execCnt % 2), resultManager.findBy(commands.get(execCnt)));
    });
    Log.i("main: tread2 start");
    th.start();
    Thread.sleep((long) (3.7 * 1000));
    container.shutdown();
    th.join();
    Log.i("main: tread2 end");
  }
  
  private class TestCommand implements Executable<Integer> {
    private int id;
    private long millis;
    
    public TestCommand(int id) {
      this.setId(id);
      this.setMillis((long) (Math.random() * 400) + 100);
    }
    
    @Override
    public Integer execute() {
      try {
        Thread.sleep(this.getMillis());
        Log.i("command invoked! id=[" + this.getId() + "]");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (id % 2 == 1)
        return 1;
      return 0;
    }
    
    public int getId() {
      return id;
    }
    
    public void setId(int id) {
      this.id = id;
    }
    
    public long getMillis() {
      return millis;
    }
    
    public void setMillis(long millis) {
      this.millis = millis;
    }
    
    @Override
    public int hashCode() {
      return HashCodeBuilder.reflectionHashCode(this);
    }
  }
}