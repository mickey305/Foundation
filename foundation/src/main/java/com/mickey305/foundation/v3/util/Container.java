package com.mickey305.foundation.v3.util;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Container implements Runnable, Killable {
    private volatile boolean doneSignal;
    private volatile boolean finish;
    private Collection<Executable> commands;
    private Collection<Pair<Executable, Object>> resultPool;
    private OnFinishEventListener onFinishEventListener;
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
        this.setResultManager(null);
    }

    @Override
    public boolean isAlive() {
        return !this.isDoneSignal() && !this.isFinish();
    }

    public void reactivation() {
        this.activation(this.getCommands());
    }

    @Override
    public synchronized void run() {
        if (this.isFinish()) return;
        // ---> Main task
        Iterator<Executable> commandItr = this.getCommands().iterator();
        while (!this.isDoneSignal() && commandItr.hasNext()) {
            Executable command = commandItr.next();
            this.getResultPool().add(Pair.of(command, command.execute()));
            commandItr.remove();
        }
        // ---> Finish event task
        OnFinishEventListener listener = this.getOnFinishEventListener();
        this.setResultManager(new ResultManager(this.getResultPool()));
        if (listener != null && !this.getResultPool().isEmpty())
            listener.onFinish(this.getCommands(), this.getResultManager());
        // ---> Signal update
        this.setFinish(true);
    }

    @Override
    public void shutdown() {
        this.setDoneSignal(true);
        while (!this.isFinish()) {
            // nop
        }
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Accessor                                                                                                       //
    //===----------------------------------------------------------------------------------------------------------===//
    private boolean isDoneSignal() {
        return doneSignal;
    }

    private void setDoneSignal(boolean doneSignal) {
        this.doneSignal = doneSignal;
    }

    private boolean isFinish() {
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

    private OnFinishEventListener getOnFinishEventListener() {
        return onFinishEventListener;
    }

    public void setOnFinishEventListener(OnFinishEventListener onFinishEventListener) {
        this.onFinishEventListener = onFinishEventListener;
    }

    public ResultManager getResultManager() {
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

    public class ResultManager {
        private Collection<Pair<Executable, Object>> resultPool;

        ResultManager(Collection<Pair<Executable, Object>> resultPool) {
            this.setResultPool(resultPool);
        }

        public Object findResultBy(Executable key) {
            return this.getResultFirstValue(key);
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

        public Collection<Pair<Executable, Object>> getResultPool() {
            return resultPool;
        }

        private void setResultPool(Collection<Pair<Executable, Object>> resultPool) {
            this.resultPool = resultPool;
        }
    }
}
