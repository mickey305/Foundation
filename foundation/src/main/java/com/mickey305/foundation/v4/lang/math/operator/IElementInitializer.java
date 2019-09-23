/*
 * Copyright (c) 2019. K.Misaki
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

package com.mickey305.foundation.v4.lang.math.operator;

/**
 * {@link Number}オブジェクト初期化用インターフェース
 * @param <E> 数値クラスの総称型です
 */
public interface IElementInitializer<E extends Number> {
  /**
   * 総称型{@link E}の初期値を生成します。
   * @return 初期値（0）
   */
  E zero();
  
  /**
   * 総称型{@link E}の初期値を生成します。
   * @return 初期値（1）
   */
  E one();
  
  /**
   * 総称型{@link E}の初期値を生成します。
   * @return 初期値（-1）
   */
  E minusOne();
  
  /**
   * 総称型{@link E}を要素とする配列を生成します。
   * @param size 配列サイズ
   * @return 新規配列
   */
  E[] array(int size);
  
  /**
   * 総称型{@link E}を要素とするテーブル配列を生成します。
   * @param r 配列サイズ（行サイズ）
   * @param c 配列サイズ（列サイズ）
   * @return 新規配列
   */
  E[][] table(int r, int c);
  
  /**
   * 変換元要素から総称型{@link E}の要素を生成します。生成ができない場合は、{@link RuntimeException}をスローします。
   * @param n 変換元要素
   * @return 変換後要素。総称型{@link E}の要素です。
   * @throws RuntimeException 例外
   */
  E convertFrom(Number n) throws RuntimeException;
}
