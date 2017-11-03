package com.mickey305.foundation.v3.util;

import java.util.ArrayDeque;
import java.util.Deque;

public class TaskManager<R, C extends Executable<R> & Cancelable<R>> implements BufferingInterface<R> {
    private Deque<C> commands;
    private Deque<C> trashCommands;

    //===----------------------------------------------------------------------------------------------------------===//
    // Constructor                                                                                                    //
    //===----------------------------------------------------------------------------------------------------------===//
    public TaskManager() {
        this.setCommands(new ArrayDeque<C>());
        this.setTrashCommands(new ArrayDeque<C>());
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Methods                                                                                                        //
    //===----------------------------------------------------------------------------------------------------------===//
    @Override
    public R undo() {
        if (!this.getCommands().isEmpty()) {
            C command = this.getCommands().pop();
            this.getTrashCommands().push(command);
            return command.cancel();
        }
        return null;
    }

    @Override
    public R redo() {
        if (!this.getTrashCommands().isEmpty()) {
            C command = this.getTrashCommands().pop();
            this.getCommands().push(command);
            return command.execute();
        }
        return null;
    }

    public R execute(C command) {
        if (!this.getTrashCommands().isEmpty())
            this.setTrashCommands(new ArrayDeque<C>());
        this.getCommands().push(command);
        return command.execute();
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Accessor                                                                                                       //
    //===----------------------------------------------------------------------------------------------------------===//
    protected Deque<C> getCommands() {
        return commands;
    }

    protected void setCommands(Deque<C> commands) {
        this.commands = commands;
    }

    protected Deque<C> getTrashCommands() {
        return trashCommands;
    }

    protected void setTrashCommands(Deque<C> trashCommands) {
        this.trashCommands = trashCommands;
    }
}
