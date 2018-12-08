package com.mickey305.foundation.v3.util;

public interface Cancelable<R> {
  /**
   * 取り消し内容
   * <p>コマンドの実行内容{@link Executable<R>}に対する取り消し内容を実装する</p>
   *
   * @return 処理結果
   */
  R cancel();
}
