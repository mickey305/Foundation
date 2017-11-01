package com.mickey305.foundation.v3.util;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Container<R> implements Runnable, Killable {
    private volatile boolean done;
    private Collection<Executable<R>> commands;
    private Collection<Executable<R>> executedCommands;
    private Map<Integer, R> resultPool;
    private HashCodeCreator<R> hashCodeCreator;

    public Container(Collection<Executable<R>> commands) {
        this.setDone(false);
        this.setCommands(commands);
        this.setExecutedCommands(new ArrayDeque<Executable<R>>(commands.size()));
        this.setResultPool(new HashMap<Integer, R>());
        this.setHashCodeCreator(null);
    }

    @Override
    public void shutdown() {
        this.setDone(true);
    }

    @Override
    public boolean isAlive() {
        return !this.isDone();
    }

    @Override
    public synchronized void run() {
        Iterator<Executable<R>> commandItr = this.getCommands().iterator();
        while (this.isAlive() && commandItr.hasNext()) {
            Executable<R> command = commandItr.next();
            this.getResultPool().put(this.createHashCode(command), command.execute());
            boolean added = this.getExecutedCommands().add(command);
            assert added;
            commandItr.remove();
        }
    }

    private int createHashCode(Executable<R> command) {
        return (this.getHashCodeCreator() == null)
                ? command.hashCode()
                : this.getHashCodeCreator().create(command);
    }

    private boolean isDone() {
        return done;
    }

    private void setDone(boolean done) {
        this.done = done;
    }

    public Collection<Executable<R>> getCommands() {
        return commands;
    }

    private void setCommands(Collection<Executable<R>> commands) {
        this.commands = commands;
    }

    public Collection<Executable<R>> getExecutedCommands() {
        return executedCommands;
    }

    private void setExecutedCommands(Collection<Executable<R>> executedCommands) {
        this.executedCommands = executedCommands;
    }

    public Map<Integer, R> getResultPool() {
        return resultPool;
    }

    private void setResultPool(Map<Integer, R> resultPool) {
        this.resultPool = resultPool;
    }

    private HashCodeCreator<R> getHashCodeCreator() {
        return hashCodeCreator;
    }

    public void setHashCodeCreator(HashCodeCreator<R> hashCodeCreator) {
        this.hashCodeCreator = hashCodeCreator;
    }

    public interface HashCodeCreator<R> {
        int create(Executable<R> command);
    }
}
