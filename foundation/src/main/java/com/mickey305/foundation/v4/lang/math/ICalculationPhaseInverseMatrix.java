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

package com.mickey305.foundation.v4.lang.math;

public interface ICalculationPhaseInverseMatrix<E extends Number> {
  /**
   * matrix(row=i,column=i) <==> NE ZERO transformation
   * 掃き出し法の第１段階実装メソッドです。同じ行/列番号上の要素がゼロ以外になるように行基本変形を行います。
   * @param horizontalExtensionMatrix 拡大係数行列
   */
  void calcInverseMatrixPhase1(Matrix<E> horizontalExtensionMatrix);
  
  /**
   * matrix(row=i,column=j), i != j <==> EQ ZERO transformation
   * 掃き出し法の第２段階実装メソッドです。異なる行/列番号上の要素がゼロになるように行基本変形を行います。
   * @param horizontalExtensionMatrix 拡大係数行列
   */
  void calcInverseMatrixPhase2(Matrix<E> horizontalExtensionMatrix);
  
  /**
   * matrix(row=i,column=i) <==> EQ ONE transformation
   * 掃き出し法の第３段階実装メソッドです。同じ行/列番号上の要素が１になるように行基本変形を行います。
   * このメソッドの実装が完了した段階で、horizontalExtensionMatrixの左部分が単位行列になります。
   * @param horizontalExtensionMatrix 拡大係数行列
   */
  void calcInverseMatrixPhase3(Matrix<E> horizontalExtensionMatrix);
}
