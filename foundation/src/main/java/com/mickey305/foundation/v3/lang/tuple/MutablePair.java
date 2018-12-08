package com.mickey305.foundation.v3.lang.tuple;

public class MutablePair<L, R> extends Pair<L, R> {
  /**
   * use serialVersionUID for interoperability
   */
  private static final long serialVersionUID = 3488116292685483870L;

  private org.apache.commons.lang3.tuple.ImmutablePair<L, R> pair;

  private MutablePair(final L left, final R right) {
    this.setPair(org.apache.commons.lang3.tuple.ImmutablePair.of(left, right));
  }

  public static <L, R> MutablePair<L, R> of(final L left, final R right) {
    return new MutablePair<>(left, right);
  }

  @Override
  public L getLeft() {
    return this.getPair().getLeft();
  }

  @Override
  public R getRight() {
    return this.getPair().getRight();
  }

  @Override
  public R setValue(R value) {
    return this.getPair().setValue(value);
  }

  /**
   * 要素入れ替え処理
   * <p>要素を入れ替えたイミュータブルなペアオブジェクトのインスタンスを生成し、返却する</p>
   *
   * @return 入れ替え後のペアオブジェクト
   */
  @SuppressWarnings("unchecked")
  @Override
  public MutablePair<R, L> swap() {
    return new MutablePair<>(this.getRight(), this.getLeft());
  }

  private org.apache.commons.lang3.tuple.ImmutablePair<L, R> getPair() {
    return pair;
  }

  private void setPair(org.apache.commons.lang3.tuple.ImmutablePair<L, R> pair) {
    this.pair = pair;
  }
}
