package com.mickey305.foundation.v3.util;

import java.util.ArrayDeque;
import java.util.Deque;

public class TaskManager<R, C extends Executable<R> & Cancelable<R>> implements BufferingInterface<R> {
    private Deque<C> commands;
    private Deque<C> trashCommands;

    //===----------------------------------------------------------------------------------------------------------===//
    // Constructor                                                                                                    //
    //===----------------------------------------------------------------------------------------------------------===//
    /**
     * コンストラクタ
     */
    public TaskManager() {
        this.setCommands(new ArrayDeque<C>());
        this.setTrashCommands(new ArrayDeque<C>());
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Methods                                                                                                        //
    //===----------------------------------------------------------------------------------------------------------===//
    /**
     * 直前のコマンドを取り消す
     * @return 処理結果
     */
    @Override
    public R undo() {
        if (!this.getCommands().isEmpty()) {
            C command = this.getCommands().pop();
            this.getTrashCommands().push(command);
            return command.cancel();
        }
        return null;
    }

    /**
     * 取り消した直後のコマンドを再実行する
     * @return 処理結果
     */
    @Override
    public R redo() {
        if (!this.getTrashCommands().isEmpty()) {
            C command = this.getTrashCommands().pop();
            this.getCommands().push(command);
            return command.execute();
        }
        return null;
    }

    /**
     * コマンド実行メソッド
     * <p>実行対象コマンド内の実行メソッド{@link Executable#execute()}を実行し、処理結果を返却する。
     * 実行コマンドを内部プールに保管する</p>
     * @param command 実行対象コマンド
     * @return 処理結果
     */
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
