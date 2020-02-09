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
import com.mickey305.foundation.v3.util.ListUtil;
import org.apache.commons.lang3.tuple.Pair;

import javax.security.auth.Destroyable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Container<R> implements Runnable, Killable, Destroyable {
  private volatile boolean doneSignal;
  private volatile boolean finish;
  private Collection<Executable<R>> commands;
  private Collection<Pair<Executable<R>, R>> resultPool;
  private OnFinishEventListener<R> onFinishEventListener;
  private OnStepFinishEventListener<R> onStepFinishEventListener;
  private ResultManager<R> resultManager;
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Constructor                                                                                                    //
  //===----------------------------------------------------------------------------------------------------------===//
  public Container(Collection<? extends Executable<R>> commands) {
    this.activation(commands);
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//
  public void activation(Collection<? extends Executable<R>> commands) {
    this.setDoneSignal(false);
    this.setFinish(false);
    this.setCommands(Collections.synchronizedCollection(new LinkedList<>(commands)));
    this.setResultPool(Collections.synchronizedCollection(new LinkedList<Pair<Executable<R>, R>>()));
    this.setOnFinishEventListener(null);
    this.setOnStepFinishEventListener(null);
    this.setResultManager(null);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAlive() {
    return !this.isFinish();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDestroyed() {
    return this.isFinish() && this.isDoneSignal();
  }
  
  public void reactivation() {
    this.activation(this.getCommands());
  }
  
  /**
   * 並列実行処理
   * <p>コレクションに格納されているコマンド群を順次実行する。呼び出し元などの他のスレッドから終了信号が通知された場合、
   * 通知時点で実行中のコマンドを最後として、並列処理を中断（強制終了）する。また、各実行コマンド終了時に発生する判定イベント
   * （デフォルトでは未実施）にて不合格（false）となった場合も、同様に並列処理を中断（強制終了）する。なお、当並列処理は
   * 正常終了・中断（強制終了）に関わらず、終了イベント（デフォルトでは未実施）を実行する</p>
   * <p>
   * {@inheritDoc}
   */
  @Override
  public synchronized void run() {
    if (this.isFinish()) return;
    // ---> Main task
    final Iterator<Executable<R>> commandItr = this.getCommands().iterator();
    final ResultManager<R> resultManager = new ResultManager<>(null);
    final OnStepFinishEventListener<R> stpFinListener = this.getOnStepFinishEventListener();
    boolean judge = true;
    while (judge && !this.isDoneSignal() && commandItr.hasNext()) {
      Executable<R> command = commandItr.next();
      // invoke command
      final R result = command.execute(); // Executable<R><R>#execute():R method calling
      this.getResultPool().add(Pair.of(command, result));
      commandItr.remove();
      // Step finish event task
      if (stpFinListener != null) {
        resultManager.setResultPool(this.getResultPool());
        judge = stpFinListener.nextStepExecutionJudge(resultManager);
      }
    }
    // ---> Finish event task
    final OnFinishEventListener<R> finListener = this.getOnFinishEventListener();
    this.setResultManager(new ResultManager<>(this.getResultPool()));
    if (finListener != null && !this.getResultPool().isEmpty())
      finListener.onFinish(this.getCommands(), this.getResultManager());
    // ---> Signal update
    this.setFinish(true);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void destroy() {
    this.shutdown();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void shutdown() {
    this.setDoneSignal(true);
    while (!this.isFinish()) {
      // nop
    }
  }
  
  public Collection<Executable<R>> timeOverCommands() {
    if (!this.isFinish())
      return new LinkedList<>();
    return this.getCommands();
  }
  
  public List<Executable<R>> timeOverCommandsToList() {
    return ListUtil.downCastFrom(this.timeOverCommands());
  }
  
  public ResultManager<R> resultManager() {
    if (!this.isFinish())
      return new ResultManager<>(new LinkedList<Pair<Executable<R>, R>>());
    return this.getResultManager();
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Accessor                                                                                                       //
  //===----------------------------------------------------------------------------------------------------------===//
  protected boolean isDoneSignal() {
    return doneSignal;
  }
  
  private void setDoneSignal(boolean doneSignal) {
    this.doneSignal = doneSignal;
  }
  
  protected boolean isFinish() {
    return finish;
  }
  
  private void setFinish(boolean finish) {
    this.finish = finish;
  }
  
  private Collection<Executable<R>> getCommands() {
    return commands;
  }
  
  private void setCommands(Collection<Executable<R>> commands) {
    this.commands = commands;
  }
  
  private Collection<Pair<Executable<R>, R>> getResultPool() {
    return resultPool;
  }
  
  private void setResultPool(Collection<Pair<Executable<R>, R>> resultPool) {
    this.resultPool = resultPool;
  }
  
  protected OnFinishEventListener<R> getOnFinishEventListener() {
    return onFinishEventListener;
  }
  
  public void setOnFinishEventListener(OnFinishEventListener<R> onFinishEventListener) {
    this.onFinishEventListener = onFinishEventListener;
  }
  
  protected OnStepFinishEventListener<R> getOnStepFinishEventListener() {
    return onStepFinishEventListener;
  }
  
  public void setOnStepFinishEventListener(OnStepFinishEventListener<R> onStepFinishEventListener) {
    this.onStepFinishEventListener = onStepFinishEventListener;
  }
  
  private ResultManager<R> getResultManager() {
    return resultManager;
  }
  
  private void setResultManager(ResultManager<R> resultManager) {
    this.resultManager = resultManager;
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Innerclass                                                                                                     //
  //===----------------------------------------------------------------------------------------------------------===//
  public interface OnFinishEventListener<R> {
    void onFinish(Collection<Executable<R>> timeOverCommands, ResultManager<R> resultManager);
  }
  
  public interface OnStepFinishEventListener<R> {
    boolean nextStepExecutionJudge(ResultManager<R> resultManager);
  }
  
  public static class ResultManager<R> {
    private Collection<Pair<Executable<R>, R>> resultPool;
    
    ResultManager(Collection<Pair<Executable<R>, R>> resultPool) {
      this.setResultPool(resultPool);
    }
    
    public R findBy(Executable<R> key) {
      return this.getResultFirstValue(key);
    }
    
    public R first(Executable<R> key) {
      return this.getResultFirstValue(key);
    }
    
    public R first() {
      return this.getResultFirstValue();
    }
    
    public List<R> getResultValues(Executable<R> key) {
      Collection<Pair<Executable<R>, R>> resultPool = this.getResultPool();
      List<R> results = new LinkedList<>();
      for (Pair<Executable<R>, R> result : resultPool) {
        if (result.getKey().equals(key))
          results.add(result.getValue());
      }
      return results;
    }
    
    public R getResultFirstValue(Executable<R> key) {
      List<R> results = this.getResultValues(key);
      if (results.isEmpty())
        return null;
      return results.get(0);
    }
    
    public R getResultFirstValue() {
      List<Pair<Executable<R>, R>> resultPool = this.getResultPoolToList();
      if (resultPool.isEmpty())
        return null;
      return resultPool.get(0).getValue();
    }
    
    public R getResultLastValue(Executable<R> key) {
      List<R> results = this.getResultValues(key);
      if (results.isEmpty())
        return null;
      return results.get(results.size() - 1);
    }
    
    public R getResultLastValue() {
      List<Pair<Executable<R>, R>> resultPool = this.getResultPoolToList();
      if (resultPool.isEmpty())
        return null;
      return resultPool.get(resultPool.size() - 1).getValue();
    }
    
    public List<Executable<R>> keys() {
      Collection<Pair<Executable<R>, R>> resultPool = this.getResultPool();
      List<Executable<R>> commands = new LinkedList<>();
      for (Pair<Executable<R>, R> result : resultPool)
        commands.add(result.getKey());
      return commands;
    }
    
    public R last(Executable<R> key) {
      return this.getResultLastValue(key);
    }
    
    public R last() {
      return this.getResultLastValue();
    }
    
    public R latest(Executable<R> key) {
      return this.newest(key);
    }
    
    public R latest() {
      return this.newest();
    }
    
    public R newest(Executable<R> key) {
      return this.getResultLastValue(key);
    }
    
    public R newest() {
      return this.getResultLastValue();
    }
    
    public R oldest() {
      return this.getResultFirstValue();
    }
    
    public R oldest(Executable<R> key) {
      return this.getResultFirstValue(key);
    }
    
    public List<R> values() {
      Collection<Pair<Executable<R>, R>> resultPool = this.getResultPool();
      List<R> values = new LinkedList<>();
      for (Pair<Executable<R>, R> result : resultPool)
        values.add(result.getValue());
      return values;
    }
    
    public List<Pair<Executable<R>, R>> getResultPoolToList() {
      return ListUtil.downCastFrom(this.getResultPool());
    }
    
    public Collection<Pair<Executable<R>, R>> getResultPool() {
      return resultPool;
    }
    
    void setResultPool(Collection<Pair<Executable<R>, R>> resultPool) {
      this.resultPool = resultPool;
    }
  }
}
