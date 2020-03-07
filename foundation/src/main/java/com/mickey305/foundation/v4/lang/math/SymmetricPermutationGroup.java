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

package com.mickey305.foundation.v4.lang.math;

import com.mickey305.foundation.v3.compat.exception.wrap.UnsupportedOperationException;
import com.mickey305.foundation.v3.compat.exception.wrap.IllegalArgumentException;
import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IElementInitializer;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SymmetricPermutationGroup<E extends Number> extends AbstractNumberTable<E> {
  public static final int SYMMETRIC_GROUP_ID = 0;
  private static final long serialVersionUID = 381011639691015678L;
  private final Set<E> dataSet;
  
  public enum Type {
    Even, Odd
  }
  
  protected SymmetricPermutationGroup(E[][] table, IElementInitializer<E> initializer,
                                      Map<Operator, AbstractNumberOperation<E, E>> op,
                                      Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    super(table, initializer, op, rop);
    this.dataSet = new HashSet<>();
    // permutation data-structure check
    final Set<E> dataSet = new HashSet<>();
    if (!this.checkPermutation())
      throw new IllegalArgumentException("symmetricPermutationGroup-check failed.");
    for (E data : table[0]) {
      final boolean status = dataSet.add(data);
      if (!status)
        throw new IllegalArgumentException("symmetricPermutationGroup-check failed.");
    }
    this.dataSet.addAll(dataSet);
    dataSet.clear();
    for (E data : table[1]) {
      final boolean status1 = this.dataSet.contains(data);
      final boolean status2 = dataSet.add(data);
      if (!(status1 && status2))
        throw new IllegalArgumentException("status1=" + status1 + ", status2=" + status2);
    }
  }
  
  protected SymmetricPermutationGroup(List<Pair<E, E>> list, IElementInitializer<E> initializer,
                                      Map<Operator, AbstractNumberOperation<E, E>> op,
                                      Map<RelationalOperator, AbstractNumberOperation<E, Boolean>> rop) {
    this(covertTable(list, initializer), initializer, op, rop);
  }
  
  protected SymmetricPermutationGroup(SymmetricPermutationGroup<E> table) {
    super(table);
    this.dataSet = new HashSet<>();
    this.dataSet.retainAll(Collections.emptySet());
    this.dataSet.addAll(table.dataSet);
  }
  
  /**
   * ペアリスト⇒2次元配列変換メソッド
   *
   * @param columnList ペアリスト
   * @return 2次元配列
   */
  private static <E extends Number> E[][] covertTable(List<Pair<E, E>> columnList, IElementInitializer<E> initializer) {
    final E[][] table = initializer.table(2, columnList.size());
    int i = 0;
    for (Pair<E, E> pair : columnList) {
      table[0][i] = pair.getLeft();
      table[1][i] = pair.getRight();
      i++;
    }
    return table;
  }
  
  /**
   * 置換データ拡張メソッド
   * <p>
   * 入力された置換データのうち要素数の多い置換データの内容をもとに、要素数の少ない置換データの内容を拡張し、
   * 同一要素数の置換データを返却する。
   * </p>
   *
   * @param l 拡張対象置換データ１
   * @param r 拡張対象置換データ２
   * @return 拡張置換データセット
   */
  public static <E extends Number> Pair<SymmetricPermutationGroup<E>, SymmetricPermutationGroup<E>>
  extension(SymmetricPermutationGroup<E> l, SymmetricPermutationGroup<E> r) {
    if (l.getColumnSize() == r.getColumnSize())
      return Pair.<SymmetricPermutationGroup<E>, SymmetricPermutationGroup<E>>of(l, r);
    
    l = new SymmetricPermutationGroup<>(l);
    r = new SymmetricPermutationGroup<>(r);
    
    SymmetricPermutationGroup<E> small = r;
    SymmetricPermutationGroup<E> big = l;
    boolean isLeftSmall = false;
    if (l.getColumnSize() < r.getColumnSize()) {
      small = l;
      big = r;
      isLeftSmall = true;
    }
    
    final E[] bigTopAry = big.getRow(0);
    final E[] smallTopAry = small.getRow(0);
    final List<Pair<E, E>> result = new ArrayList<>();
    
    for (int i = 0; i < big.getColumnSize(); i++) {
      if (small.dataSet.contains(bigTopAry[i])) {
        final int index = small.getColumnIndexOf(bigTopAry[i]);
        result.add(Pair.<E, E>of(smallTopAry[index], small.getPairOf(smallTopAry[index])));
      } else {
        result.add(Pair.<E, E>of(bigTopAry[i], bigTopAry[i]));
      }
    }
    final SymmetricPermutationGroup<E> newPermutation = new SymmetricPermutationGroup<>(
        result,
        (isLeftSmall) ? l.getInitializer() : r.getInitializer(),
        (isLeftSmall) ? l.getOp() : r.getOp(),
        (isLeftSmall) ? l.getRop() : r.getRop());
    
    return (isLeftSmall)
        ? Pair.<SymmetricPermutationGroup<E>, SymmetricPermutationGroup<E>>of(newPermutation, big)
        : Pair.<SymmetricPermutationGroup<E>, SymmetricPermutationGroup<E>>of(big, newPermutation);
  }
  
  /**
   * 乗算処理
   *
   * @param r 右置換データ
   * @return 演算結果置換データ
   */
  public SymmetricPermutationGroup<E> multi(SymmetricPermutationGroup<E> r) {
    if (!(r.dataSet.containsAll(this.dataSet) || this.dataSet.containsAll(r.dataSet)))
      throw new UnsupportedOperationException("multi-target-data-check failed.");
    
    // extension logic impl
    Pair<SymmetricPermutationGroup<E>, SymmetricPermutationGroup<E>> pair = SymmetricPermutationGroup.extension(this, r);
    final SymmetricPermutationGroup<E> l = pair.getLeft();
    r = pair.getRight();
    
    final E[] leftAry = l.getRow(0);
    final List<Pair<E, E>> result = new ArrayList<>();
    for (int i = 0; i < l.getColumnSize(); i++) {
      final E rightBottom = r.getPairOf(leftAry[i]);
      final E leftBottom = l.getPairOf(rightBottom);
      result.add(Pair.<E, E>of(leftAry[i], leftBottom));
    }
    return new SymmetricPermutationGroup<>(result, this.getInitializer(), this.getOp(), this.getRop());
  }
  
  /**
   * 単位置換取得メソッド
   *
   * @return 単位置換
   */
  public SymmetricPermutationGroup<E> createIdentityPermutation() {
    final E[] ary = this.getRow(0);
    final List<Pair<E, E>> result = new ArrayList<>();
    for (int i = 0; i < this.getColumnSize(); i++) {
      result.add(Pair.<E, E>of(ary[i], ary[i]));
    }
    return new SymmetricPermutationGroup<>(result, this.getInitializer(), this.getOp(), this.getRop());
  }
  
  /**
   * 逆置換取得メソッド
   *
   * @return 逆置換
   */
  public SymmetricPermutationGroup<E> createInversePermutation() {
    final SymmetricPermutationGroup<E> result = new SymmetricPermutationGroup<>(this);
    result.swapRow(0, 1);
    return result;
  }
  
  /**
   * 符号取得メソッド
   *
   * @return 符号
   */
  public E sgn() {
    return (this.is(Type.Even))
        ? this.getInitializer().one()
        : this.getInitializer().minusOne();
  }
  
  /**
   * 置換データチェック
   *
   * @return 判定結果
   */
  private boolean checkPermutation() {
    return this.getRowSize() == 2 && this.getColumnSize() >= 1;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void putRow(int row, E[] data) {
    throw new UnsupportedOperationException("unSupported method.");
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void putColumn(int column, E[] data) {
    throw new UnsupportedOperationException("unSupported method.");
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void putCell(int row, int column, E data) {
    throw new UnsupportedOperationException("unSupported method.");
  }
  
  /**
   * 置換⇒巡回置換変換メソッド
   *
   * @return 巡回置換リスト
   */
  public List<? extends SymmetricCycleGroup<E>> convertCycle() {
    List<SymmetricCycleGroup<E>> R = new ArrayList<>();
    Set<E> checkedElms = new HashSet<>();
    
    for (E tpData : this.getRow(0)) {
      if (checkedElms.contains(tpData))
        continue;
      
      // search
      List<Pair<E, E>> list = new ArrayList<>();
      E tmpData = tpData;
      do {
        checkedElms.add(tmpData);
        list.add(Pair.<E, E>of(tmpData, this.getPairOf(tmpData)));
        tmpData = this.getPairOf(tmpData);
      } while (this.getRop(RelationalOperator.NE).apply(tmpData, tpData));
      
      R.add(new SymmetricCycleGroup<>(list, this.getInitializer(), this.getOp(), this.getRop()));
    }
    
    return R;
  }
  
  /**
   * 置換⇒互換変換メソッド
   *
   * @return 互換リスト
   */
  public List<? extends SymmetricTransPositionGroup<E>> convertTransPosition() {
    List<SymmetricTransPositionGroup<E>> R = new ArrayList<>();
    final List<? extends SymmetricCycleGroup<E>> cycles = this.convertCycle();
    for (SymmetricCycleGroup<E> cycle : cycles) {
      if (cycle.getColumnSize() == 2) {
        R.add(new SymmetricTransPositionGroup<>(
            cycle.getTable(), cycle.getInitializer(), cycle.getOp(), cycle.getRop()));
      } else {
        E suffix = null;
        final Deque<E> numbers = new ArrayDeque<>(cycle.getColumnSize() - 1);
        for (E data : cycle.getRow(0)) {
          if (suffix == null)
            suffix = data;
          else
            numbers.offerFirst(data);
        }
        while (!numbers.isEmpty()) {
          R.add(
              new SymmetricTransPositionGroup<>(
                  Pair.<E, E>of(suffix, numbers.pollFirst()), cycle.getInitializer(), cycle.getOp(), cycle.getRop())
          );
        }
      }
    }
    return R;
  }
  
  /**
   * 置換省略形データ生成メソッド
   *
   * @return 省略型置換データ
   */
  public SymmetricPermutationGroup<E> compact() {
    final List<Integer> samePairRowIndexes = getColumnIndexOfSamePair();
    if (samePairRowIndexes.size() == this.getColumnSize())
      return new SymmetricPermutationGroup<>(this);
    
    final E[][] table = this.getTable();
    final List<Pair<E, E>> result = new ArrayList<>();
    for (int i = 0; i < this.getColumnSize(); i++) {
      if (!samePairRowIndexes.contains(i)) {
        result.add(Pair.<E, E>of(table[0][i], table[1][i]));
      }
    }
    return new SymmetricPermutationGroup<>(result, this.getInitializer(), this.getOp(), this.getRop());
  }
  
  /**
   * 未遷移要素番号取得メソッド
   *
   * @return 未遷移要素番号リスト
   */
  public List<Integer> getColumnIndexOfSamePair() {
    final E[][] table = this.getTable();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < this.getColumnSize(); i++) {
      if (this.getRop(RelationalOperator.Eq).apply(table[0][i], table[1][i])) {
        result.add(i);
      }
    }
    return result;
  }
  
  /**
   * 対象要素番号取得メソッド
   *
   * @param num 対象要素
   * @return 対象要素番号
   */
  public Integer getColumnIndexOf(E num) {
    final E[][] table = this.getTable();
    for (int i = 0; i < this.getColumnSize(); i++) {
      if (this.getRop(RelationalOperator.Eq).apply(table[0][i], num)) {
        return i;
      }
    }
    return null;
  }
  
  /**
   * 遷移先要素取得メソッド
   *
   * @param num 遷移元要素
   * @return 遷移先要素
   */
  public E getPairOf(E num) {
    final E[][] table = this.getTable();
    return table[1][this.getColumnIndexOf(num)];
  }
  
  /**
   * 偶置換／奇置換判定メソッド
   *
   * @param type 置換タイプ{@link Type}
   * @return 判定結果
   */
  public boolean is(Type type) {
    return type == Type.Even && this.convertTransPosition().size() % 2 == 0
        || type == Type.Odd && this.convertTransPosition().size() % 2 != 0;
  }
  
  protected Set<E> getDataSet() {
    return dataSet;
  }
}
