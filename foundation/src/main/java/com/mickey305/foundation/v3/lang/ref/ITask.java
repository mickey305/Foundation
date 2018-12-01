package com.mickey305.foundation.v3.lang.ref;

public interface ITask<I, O> {
  O impl(I input);
}
