package com.mickey305.foundation.v3.util;

public interface DownCastable {
  /**
   * ダウンキャスト実装メソッド
   *
   * @param subClass キャスト先のクラス
   * @param <T>      キャスト先の総称型
   * @return キャスト先（ダウンキャスト後）のインスタンス
   */
  <T extends DownCastable> T downcastTo(Class<T> subClass);
}
