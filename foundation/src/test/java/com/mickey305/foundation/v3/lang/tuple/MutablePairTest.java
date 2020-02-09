/*
 * Copyright (c) 2017 - 2020 K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mickey305.foundation.v3.lang.tuple;

import com.mickey305.foundation.v3.util.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MutablePairTest {
  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testCase_01_01() throws Exception {
    final Swappable<Integer, String> swappable = Pair.of(1, "test");

    final Pair<String, Integer> pair = swappable.swap(); // test target logic
    Log.i("left: " + pair.getLeft());
    Log.i("right: " + pair.getRight());
    Assert.assertEquals("test", pair.getLeft());
    Assert.assertEquals(Integer.valueOf(1), pair.getRight());
    Assert.assertEquals("test", pair.getKey());
    Assert.assertEquals(Integer.valueOf(1), pair.getValue());

    final MutablePair<String, Integer> pair2 = swappable.swap().swap().swap().swap().swap();
    Log.i("left: " + pair2.getLeft());
    Log.i("right: " + pair2.getRight()); // test target logic
    Assert.assertEquals("test", pair2.getLeft());
    Assert.assertEquals(Integer.valueOf(1), pair2.getRight());
    Assert.assertEquals("test", pair2.getKey());
    Assert.assertEquals(Integer.valueOf(1), pair2.getValue());
    
    final Swappable<Integer, String> swappable2 = MutablePairEx1.of(1, "test");
  
    final Pair<Integer, String> nonErrPair = swappable2.swap().swap(); // test target logic
    try {
      final MutablePair<Integer, String> errPair = swappable2.swap().swap(); // test target logic
      Assert.fail();
    } catch (ClassCastException e) {
      Log.d(e);
    }
  }
  
  private static class MutablePairEx1<L, R> extends Pair<L, R> {
    private static final long serialVersionUID = 8775417255962555640L;
  
    private org.apache.commons.lang3.tuple.MutablePair<L, R> pair;
  
    private MutablePairEx1(final L left, final R right) {
      this.setPair(org.apache.commons.lang3.tuple.MutablePair.of(left, right));
    }
  
    public static <L, R> MutablePairEx1<L, R> of(final L left, final R right) {
      return new MutablePairEx1<>(left, right);
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public L getLeft() {
      return this.getPair().getLeft();
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public R getRight() {
      return this.getPair().getRight();
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public R setValue(R value) {
      return this.getPair().setValue(value);
    }
  
    /**
     * 要素入れ替え処理
     * <p>要素を入れ替えたミュータブルなペアオブジェクトのインスタンスを生成し、返却する</p>
     *
     * @return 入れ替え後のペアオブジェクト
     * <p>
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends org.apache.commons.lang3.tuple.Pair<R, L> & Swappable<R, L>> T swap() {
      return (T) new MutablePairEx1<>(this.getRight(), this.getLeft());
    }
  
    private org.apache.commons.lang3.tuple.MutablePair<L, R> getPair() {
      return pair;
    }
  
    private void setPair(org.apache.commons.lang3.tuple.MutablePair<L, R> pair) {
      this.pair = pair;
    }
  }
}