package com.mickey305.foundation.v3.lang.tuple;

import org.apache.commons.lang3.tuple.Pair;

public interface Swappable<L, R> {
  /**
   * 要素入れ替え処理
   * <p>要素を入れ替えたオブジェクトを返却する</p>
   *
   * @return 入れ替え後のオブジェクト
   */
  <T extends Pair<R, L> & Swappable<R, L>> T swap();
}
