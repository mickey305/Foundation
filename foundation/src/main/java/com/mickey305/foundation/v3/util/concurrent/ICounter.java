package com.mickey305.foundation.v3.util.concurrent;

public interface ICounter {
  /**
   * アトミックカウンタ実装メソッド
   *
   * @return アトミックID
   */
  long count();
}
