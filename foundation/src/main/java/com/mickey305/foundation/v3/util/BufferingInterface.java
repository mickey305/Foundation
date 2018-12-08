package com.mickey305.foundation.v3.util;

public interface BufferingInterface<R> {
  /**
   * 直前のコマンドを取り消す
   *
   * @return 処理結果
   */
  R undo();
  
  /**
   * 取り消した直後のコマンドを再実行する
   *
   * @return 処理結果
   */
  R redo();
}
