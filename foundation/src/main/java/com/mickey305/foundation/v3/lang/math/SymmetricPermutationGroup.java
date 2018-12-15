package com.mickey305.foundation.v3.lang.math;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Deprecated
public class SymmetricPermutationGroup extends AbstractNumberTable {
  public static final int SYMMETRIC_GROUP_ID = 0;
  private static final long serialVersionUID = -6564909210449073110L;
  private final Set<Number> dataSet;
  
  public enum Type {
    Even,
    Odd
  }
  
  protected SymmetricPermutationGroup(Number[][] table) {
    super(table);
    this.dataSet = new HashSet<>();
    // permutation data-structure check
    final Set<Number> dataSet = new HashSet<>();
    if (!this.checkPermutation())
      throw new IllegalArgumentException();
    for (Number data : table[0]) {
      final boolean status = dataSet.add(data);
      if (!status)
        throw new IllegalArgumentException();
    }
    this.dataSet.addAll(dataSet);
    dataSet.clear();
    for (Number data : table[1]) {
      final boolean status1 = this.dataSet.contains(data);
      final boolean status2 = dataSet.add(data);
      if (!(status1 && status2))
        throw new IllegalArgumentException("status1=" + status1 + ", status2=" + status2);
    }
  }
  
  protected SymmetricPermutationGroup(List<Pair<Number, Number>> list) {
    this(SymmetricPermutationGroup.covertTable(list));
  }
  
  protected SymmetricPermutationGroup(SymmetricPermutationGroup table) {
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
  private static Number[][] covertTable(List<Pair<Number, Number>> columnList) {
    final Number[][] table = new Number[2][columnList.size()];
    int i = 0;
    for (Pair<Number, Number> pair : columnList) {
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
  public static Pair<SymmetricPermutationGroup, SymmetricPermutationGroup> extension(SymmetricPermutationGroup l, SymmetricPermutationGroup r) {
    if (l.getColumnSize() == r.getColumnSize())
      return Pair.of(l, r);
    
    l = new SymmetricPermutationGroup(l);
    r = new SymmetricPermutationGroup(r);
    
    SymmetricPermutationGroup small = r;
    SymmetricPermutationGroup big = l;
    boolean isLeftSmall = false;
    if (l.getColumnSize() < r.getColumnSize()) {
      small = l;
      big = r;
      isLeftSmall = true;
    }
    
    final Number[] bigTopAry = big.getRow(0);
    final Number[] smallTopAry = small.getRow(0);
    final List<Pair<Number, Number>> result = new ArrayList<>();
    
    for (int i = 0; i < big.getColumnSize(); i++) {
      if (small.dataSet.contains(bigTopAry[i])) {
        final int index = small.getColumnIndexOf(bigTopAry[i]);
        result.add(Pair.of(smallTopAry[index], small.getPairOf(smallTopAry[index])));
      } else {
        result.add(Pair.of(bigTopAry[i], bigTopAry[i]));
      }
    }
    final SymmetricPermutationGroup newPermutation = new SymmetricPermutationGroup(result);
    
    return (isLeftSmall)
        ? Pair.of(newPermutation, big)
        : Pair.of(big, newPermutation);
  }
  
  /**
   * 乗算処理
   *
   * @param l 左置換データ
   * @param r 右置換データ
   * @return 演算結果置換データ
   */
  public static SymmetricPermutationGroup multi(SymmetricPermutationGroup l, SymmetricPermutationGroup r) {
    if (!(r.dataSet.containsAll(l.dataSet) || l.dataSet.containsAll(r.dataSet)))
      throw new UnsupportedOperationException();
    
    // extension logic impl
    Pair<SymmetricPermutationGroup, SymmetricPermutationGroup> pair = SymmetricPermutationGroup.extension(l, r);
    l = pair.getLeft();
    r = pair.getRight();
    
    final Number[] leftAry = l.getRow(0);
    final List<Pair<Number, Number>> result = new ArrayList<>();
    for (int i = 0; i < l.getColumnSize(); i++) {
      final Number rightBottom = r.getPairOf(leftAry[i]);
      final Number leftBottom = l.getPairOf(rightBottom);
      result.add(Pair.of(leftAry[i], leftBottom));
    }
    return new SymmetricPermutationGroup(result);
  }
  
  /**
   * 単位置換取得メソッド
   *
   * @param permutation 置換
   * @return 単位置換
   */
  public static SymmetricPermutationGroup createIdentityPermutation(SymmetricPermutationGroup permutation) {
    final Number[] ary = permutation.getRow(0);
    final List<Pair<Number, Number>> result = new ArrayList<>();
    for (int i = 0; i < permutation.getColumnSize(); i++) {
      result.add(Pair.of(ary[i], ary[i]));
    }
    return new SymmetricPermutationGroup(result);
  }
  
  /**
   * 逆置換取得メソッド
   *
   * @param permutation 置換
   * @return 逆置換
   */
  public static SymmetricPermutationGroup createInversePermutation(SymmetricPermutationGroup permutation) {
    final SymmetricPermutationGroup result = new SymmetricPermutationGroup(permutation);
    result.swapRow(0, 1);
    return result;
  }
  
  /**
   * 符号取得メソッド
   *
   * @param permutation 置換
   * @return 符号
   */
  public static int sgn(SymmetricPermutationGroup permutation) {
    return (permutation.is(Type.Even)) ? 1 : -1;
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
  public void putRowForcibly(int row, Number[] data) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void putColumnForcibly(int column, Number[] data) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void putCellForcibly(int row, int column, Number data) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean putCell(int row, int column, Number data) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * 置換⇒巡回置換変換メソッド
   *
   * @return 巡回置換リスト
   */
  public List<? extends SymmetricCycleGroup> convertCycle() {
    List<SymmetricCycleGroup> resultCycle = new ArrayList<>();
    Set<Number> checkedElms = new HashSet<>();
    
    for (Number tpData : this.getRow(0)) {
      if (checkedElms.contains(tpData))
        continue;
      
      // search
      List<Pair<Number, Number>> list = new ArrayList<>();
      Number tmpData = tpData;
      do {
        checkedElms.add(tmpData);
        list.add(Pair.of(tmpData, this.getPairOf(tmpData)));
        tmpData = this.getPairOf(tmpData);
      } while (!tmpData.equals(tpData));
      
      resultCycle.add(new SymmetricCycleGroup(list));
    }
    
    return resultCycle;
  }
  
  /**
   * 置換⇒互換変換メソッド
   *
   * @return 互換リスト
   */
  public List<? extends SymmetricTransPositionGroup> convertTransPosition() {
    List<SymmetricTransPositionGroup> resultTransPosition = new ArrayList<>();
    final List<? extends SymmetricCycleGroup> cycles = this.convertCycle();
    for (SymmetricCycleGroup cycle : cycles) {
      if (cycle.getColumnSize() == 2) {
        resultTransPosition.add(new SymmetricTransPositionGroup(cycle.getTable()));
      } else {
        Number suffix = null;
        final Deque<Number> numbers = new ArrayDeque<>(cycle.getColumnSize() - 1);
        for (Number data : cycle.getRow(0)) {
          if (suffix == null)
            suffix = data;
          else
            numbers.offerFirst(data);
        }
        while (!numbers.isEmpty()) {
          resultTransPosition.add(
              new SymmetricTransPositionGroup(Pair.of(suffix, numbers.pollFirst())));
        }
      }
    }
    return resultTransPosition;
  }
  
  /**
   * 置換省略形データ生成メソッド
   *
   * @return 省略型置換データ
   */
  public SymmetricPermutationGroup compact() {
    final List<Integer> samePairRowIndexes = getColumnIndexOfSamePair();
    if (samePairRowIndexes.size() == this.getColumnSize())
      return new SymmetricPermutationGroup(this);
    
    final Number[][] table = this.getTable();
    final List<Pair<Number, Number>> result = new ArrayList<>();
    for (int i = 0; i < this.getColumnSize(); i++) {
      if (!samePairRowIndexes.contains(i)) {
        result.add(Pair.of(table[0][i], table[1][i]));
      }
    }
    return new SymmetricPermutationGroup(result);
  }
  
  /**
   * 未遷移要素番号取得メソッド
   *
   * @return 未遷移要素番号リスト
   */
  public List<Integer> getColumnIndexOfSamePair() {
    final Number[][] table = this.getTable();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < this.getColumnSize(); i++) {
      if (RelationalOperator.EQ.f.apply(table[0][i], table[1][i])) {
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
  public Integer getColumnIndexOf(Number num) {
    final Number[][] table = this.getTable();
    for (int i = 0; i < this.getColumnSize(); i++) {
      if (RelationalOperator.EQ.f.apply(table[0][i], num)) {
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
  public Number getPairOf(Number num) {
    final Number[][] table = this.getTable();
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
  
  protected Set<Number> getDataSet() {
    return dataSet;
  }
}
