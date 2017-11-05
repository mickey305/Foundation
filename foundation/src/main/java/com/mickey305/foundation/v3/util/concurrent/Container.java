package com.mickey305.foundation.v3.util.concurrent;

import com.mickey305.foundation.v3.util.Executable;
import org.apache.commons.lang3.tuple.Pair;

import javax.security.auth.Destroyable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Container implements Runnable, Killable, Destroyable {
    private volatile boolean doneSignal;
    private volatile boolean finish;
    private Collection<Executable> commands;
    private Collection<Pair<Executable, Object>> resultPool;
    private OnFinishEventListener onFinishEventListener;
    private OnStepFinishEventListener onStepFinishEventListener;
    private ResultManager resultManager;

    //===----------------------------------------------------------------------------------------------------------===//
    // Constructor                                                                                                    //
    //===----------------------------------------------------------------------------------------------------------===//
    public Container(Collection<? extends Executable> commands) {
        this.activation(commands);
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Methods                                                                                                        //
    //===----------------------------------------------------------------------------------------------------------===//
    public void activation(Collection<? extends Executable> commands) {
        this.setDoneSignal(false);
        this.setFinish(false);
        this.setCommands(Collections.synchronizedCollection(new LinkedList<>(commands)));
        this.setResultPool(Collections.synchronizedCollection(new LinkedList<Pair<Executable, Object>>()));
        this.setOnFinishEventListener(null);
        this.setOnStepFinishEventListener(null);
        this.setResultManager(null);
    }

    @Override
    public boolean isAlive() {
        return !this.isFinish();
    }

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
     */
    @Override
    public synchronized void run() {
        if (this.isFinish()) return;
        // ---> Main task
        final Iterator<Executable> commandItr = this.getCommands().iterator();
        final ResultManager resultManager = new ResultManager(null);
        final OnStepFinishEventListener stpFinListener = this.getOnStepFinishEventListener();
        boolean judge = true;
        while (judge && !this.isDoneSignal() && commandItr.hasNext()) {
            Executable command = commandItr.next();
            // invoke command
            final Object result = command.execute(); // Executable<R>#execute():R method calling
            this.getResultPool().add(Pair.of(command, result));
            commandItr.remove();
            // Step finish event task
            if (stpFinListener != null) {
                resultManager.setResultPool(this.getResultPool());
                judge = stpFinListener.nextStepExecutionJudge(resultManager);
            }
        }
        // ---> Finish event task
        final OnFinishEventListener finListener = this.getOnFinishEventListener();
        this.setResultManager(new ResultManager(this.getResultPool()));
        if (finListener != null && !this.getResultPool().isEmpty())
            finListener.onFinish(this.getCommands(), this.getResultManager());
        // ---> Signal update
        this.setFinish(true);
    }

    @Override
    public void destroy() {
        this.shutdown();
    }

    @Override
    public void shutdown() {
        this.setDoneSignal(true);
        while (!this.isFinish()) {
            // nop
        }
    }

    public Collection<Executable> timeOverCommands() {
        if (!this.isFinish())
            return new LinkedList<>();
        return this.getCommands();
    }

    public List<Executable> timeOverCommandsToList() {
        return Arrays.asList(this.timeOverCommands().toArray(new Executable[0]));
    }

    public ResultManager resultManager() {
        if (!this.isFinish())
            return new ResultManager(new LinkedList<Pair<Executable, Object>>());
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

    private Collection<Executable> getCommands() {
        return commands;
    }

    private void setCommands(Collection<Executable> commands) {
        this.commands = commands;
    }

    private Collection<Pair<Executable, Object>> getResultPool() {
        return resultPool;
    }

    private void setResultPool(Collection<Pair<Executable, Object>> resultPool) {
        this.resultPool = resultPool;
    }

    protected OnFinishEventListener getOnFinishEventListener() {
        return onFinishEventListener;
    }

    public void setOnFinishEventListener(OnFinishEventListener onFinishEventListener) {
        this.onFinishEventListener = onFinishEventListener;
    }

    protected OnStepFinishEventListener getOnStepFinishEventListener() {
        return onStepFinishEventListener;
    }

    public void setOnStepFinishEventListener(OnStepFinishEventListener onStepFinishEventListener) {
        this.onStepFinishEventListener = onStepFinishEventListener;
    }

    private ResultManager getResultManager() {
        return resultManager;
    }

    private void setResultManager(ResultManager resultManager) {
        this.resultManager = resultManager;
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Innerclass                                                                                                     //
    //===----------------------------------------------------------------------------------------------------------===//
    public interface OnFinishEventListener {
        void onFinish(Collection<Executable> timeOverCommands, ResultManager resultManager);
    }

    public interface OnStepFinishEventListener {
        boolean nextStepExecutionJudge(ResultManager resultManager);
    }

    public class ResultManager {
        private Collection<Pair<Executable, Object>> resultPool;

        ResultManager(Collection<Pair<Executable, Object>> resultPool) {
            this.setResultPool(resultPool);
        }

        public Object findBy(Executable key) {
            return this.getResultFirstValue(key);
        }

        public Object first(Executable key) {
            return this.getResultFirstValue(key);
        }

        public Object first() {
            return this.getResultFirstValue();
        }

        public List getResultValues(Executable key) {
            Collection<Pair<Executable, Object>> resultPool = this.getResultPool();
            List<Object> results = new LinkedList<>();
            for (Pair<Executable, Object> result: resultPool) {
                if (result.getKey().equals(key))
                    results.add(result.getValue());
            }
            return results;
        }

        public Object getResultFirstValue(Executable key) {
            List results = this.getResultValues(key);
            if (results.isEmpty())
                return null;
            return results.get(0);
        }

        public Object getResultFirstValue() {
            List<Pair<Executable, Object>> resultPool = this.getResultPoolToList();
            if (resultPool.isEmpty())
                return null;
            return resultPool.get(0).getValue();
        }

        public Object getResultLastValue(Executable key) {
            List results = this.getResultValues(key);
            if (results.isEmpty())
                return null;
            return results.get(results.size() - 1);
        }

        public Object getResultLastValue() {
            List<Pair<Executable, Object>> resultPool = this.getResultPoolToList();
            if (resultPool.isEmpty())
                return null;
            return resultPool.get(resultPool.size() - 1).getValue();
        }

        public List<Executable> keys() {
            Collection<Pair<Executable, Object>> resultPool = this.getResultPool();
            List<Executable> commands = new LinkedList<>();
            for (Pair<Executable, Object> result: resultPool)
                commands.add(result.getKey());
            return commands;
        }

        public Object last(Executable key) {
            return this.getResultLastValue(key);
        }

        public Object last() {
            return this.getResultLastValue();
        }

        public Object latest(Executable key) {
            return this.newest(key);
        }

        public Object latest() {
            return this.newest();
        }

        public Object newest(Executable key) {
            return this.getResultLastValue(key);
        }

        public Object newest() {
            return this.getResultLastValue();
        }

        public Object oldest() {
            return this.getResultFirstValue();
        }

        public Object oldest(Executable key) {
            return this.getResultFirstValue(key);
        }

        public List values() {
            Collection<Pair<Executable, Object>> resultPool = this.getResultPool();
            List<Object> values = new LinkedList<>();
            for (Pair<Executable, Object> result: resultPool)
                values.add(result.getValue());
            return values;
        }

        public List<Pair<Executable, Object>> getResultPoolToList() {
            Pair<Executable, Object>[] type = new Pair[0];
            return Arrays.asList(this.getResultPool().toArray(type));
        }

        public Collection<Pair<Executable, Object>> getResultPool() {
            return resultPool;
        }

        void setResultPool(Collection<Pair<Executable, Object>> resultPool) {
            this.resultPool = resultPool;
        }
    }
}
