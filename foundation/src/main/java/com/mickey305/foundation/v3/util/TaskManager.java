package com.mickey305.foundation.v3.util;

import java.util.Stack;

public class TaskManager<R, C extends Executable<R>> implements BufferingInterface<R> {
    private Stack<C> commands;
    private Stack<C> trashCommands;

    //===----------------------------------------------------------------------------------------------------------===//
    // Constructor                                                                                                    //
    //===----------------------------------------------------------------------------------------------------------===//
    public TaskManager() {
        this.setCommands(new Stack<C>());
        this.setTrashCommands(new Stack<C>());
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Methods                                                                                                        //
    //===----------------------------------------------------------------------------------------------------------===//
    @Override
    public R undo() {
        if (!this.getCommands().isEmpty()) {
            C command = this.getCommands().pop();
            this.getTrashCommands().push(command);
            return command.execute();
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

    @Override
    public R execute() {
        if (!this.getCommands().isEmpty()) {
            C command = this.getCommands().peek();
            return command.execute();
        }
        return null;
    }

    public R execute(C command) {
        if (!this.getTrashCommands().isEmpty())
            this.setTrashCommands(new Stack<C>());
        this.getCommands().push(command);
        return command.execute();
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Accessor                                                                                                       //
    //===----------------------------------------------------------------------------------------------------------===//
    protected Stack<C> getCommands() {
        return commands;
    }

    protected void setCommands(Stack<C> commands) {
        this.commands = commands;
    }

    protected Stack<C> getTrashCommands() {
        return trashCommands;
    }

    protected void setTrashCommands(Stack<C> trashCommands) {
        this.trashCommands = trashCommands;
    }
}
