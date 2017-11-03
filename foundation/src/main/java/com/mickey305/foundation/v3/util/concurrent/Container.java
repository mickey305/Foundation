package com.mickey305.foundation.v3.util.concurrent;

import com.mickey305.foundation.v3.util.Executable;
import org.apache.commons.lang3.tuple.Pair;

import javax.security.auth.Destroyable;
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
        return !this.isFinish();
    }

    @Override
    public boolean isDestroyed() {
        return this.isFinish() && this.isDoneSignal();
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
            // invoke command
            final Object result = command.execute();
            this.getResultPool().add(Pair.of(command, result));
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
        return (List<Executable>) this.timeOverCommands();
    }

    public ResultManager resultManager() {
        if (!this.isFinish())
            return new ResultManager(new LinkedList<Pair<Executable, Object>>());
        return this.getResultManager();
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

    public class ResultManager {
        private Collection<Pair<Executable, Object>> resultPool;

        ResultManager(Collection<Pair<Executable, Object>> resultPool) {
            this.setResultPool(resultPool);
        }

        public Object findBy(Executable key) {
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

        public Object getResultFirstValue() {
            List<Pair<Executable, Object>> resultPool = this.getResultPoolToList();
            if (resultPool.isEmpty())
                return null;
            return resultPool.get(0);
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
            return resultPool.get(resultPool.size() - 1);
        }

        public List<Pair<Executable, Object>> getResultPoolToList() {
            return (List<Pair<Executable, Object>>) this.getResultPool();
        }

        public Collection<Pair<Executable, Object>> getResultPool() {
            return resultPool;
        }

        private void setResultPool(Collection<Pair<Executable, Object>> resultPool) {
            this.resultPool = resultPool;
        }
    }
}
