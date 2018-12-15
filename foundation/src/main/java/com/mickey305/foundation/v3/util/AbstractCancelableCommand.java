package com.mickey305.foundation.v3.util;

public abstract class AbstractCancelableCommand<R> implements Executable<R>, Cancelable<R> {
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//
  
  /**
   * 試行用ロジックを実行
   *
   * @param callback 試行用ロジック実装インターフェース
   * @return 当コマンド取り消し実行結果
   */
  public R trial(Callback<R> callback) {
    if (callback == null) {
      return null;
    }
    R result = this.execute();
    callback.onExecuted(result);
    return this.cancel();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public abstract R execute();
  
  /**
   * {@inheritDoc}
   */
  @Override
  public abstract R cancel();
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Innerclass                                                                                                     //
  //===----------------------------------------------------------------------------------------------------------===//
  public interface Callback<R> {
    /**
     * 試行用ロジック実装メソッド
     *
     * @param executionResult 当コマンド実行結果
     */
    void onExecuted(R executionResult);
  }
}
