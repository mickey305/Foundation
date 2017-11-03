package com.mickey305.foundation.v3.util;

public abstract class AbstractCancelableCommand<R> implements Executable<R>, Cancelable<R> {

    public R trial(Callback<R> callback) {
        if (callback == null) return null;
        R result = this.execute();
        callback.onExecuted(result);
        return this.cancel();
    }

    @Override
    public abstract R execute();

    @Override
    public abstract R cancel();

    public interface Callback<R> {
        void onExecuted(R executionResult);
    }
}
