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

package com.mickey305.foundation.v4.lang.math.context;

import com.mickey305.foundation.v3.util.Assert;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.BigInteger;

public class MatrixContextFactory {
  private MatrixContextFactory() {}
  
  /**
   * 行列コンテキストファクトリーオブジェクトを生成して返却します
   * @param dummyElementType 未入力可。型を解決するために内部で使用する可変長引数です。
   * @param <E> {@link java.lang.Number}を継承しているクラスの総称型です
   * @return ファクトリーオブジェクト
   */
  @SuppressWarnings("unchecked")
  @Nonnull
  public static <E extends Number> IMatrixContext<E> getFactory(E... dummyElementType) {
    return getFactory(MatrixContextType.Default, dummyElementType);
  }
  
  /**
   * 行列コンテキストファクトリーオブジェクトのシングルトンを返却します
   * @param dummyElementType 未入力可。型を解決するために内部で使用する可変長引数です。
   * @param <E> {@link java.lang.Number}を継承しているクラスの総称型です
   * @return ファクトリーオブジェクト
   */
  @SuppressWarnings("unchecked")
  @Nonnull
  public static <E extends Number> IMatrixContext<E> getSharedFactory(E... dummyElementType) {
    return getFactory(MatrixContextType.Shared, dummyElementType);
  }
  
  /**
   * 引数で与えられた{@link MatrixContextType}の行列コンテキストファクトリーオブジェクトを返却します
   * @param contextType コンテキストの種類
   * @param dummyElementType 未入力可。型を解決するために内部で使用する可変長引数です。
   * @param <E> {@link java.lang.Number}を継承しているクラスの総称型です
   * @return ファクトリーオブジェクト
   */
  @SuppressWarnings("unchecked")
  @Nonnull
  public static <E extends Number> IMatrixContext<E> getFactory(
      @Nonnull MatrixContextType contextType, E... dummyElementType) {
    final Class<E> type = (Class<E>) dummyElementType.getClass().getComponentType();
    
    Assert.requireNonNull(contextType);
    Assert.requireNonNull(type);
  
    return tryGetFactory(contextType, type);
  }
  
  @SuppressWarnings("unchecked")
  private static <E extends Number> IMatrixContext<E> tryGetFactory(MatrixContextType contextType, Class<E> type) {
    if (type.equals(BigDecimal.class))  return (IMatrixContext<E>) new MatrixContextBigDecimal (contextType);
    if (type.equals(BigFraction.class)) return (IMatrixContext<E>) new MatrixContextBigFraction(contextType);
    if (type.equals(BigInteger.class))  return (IMatrixContext<E>) new MatrixContextBigInt     (contextType);
    if (type.equals(Byte.class))        return (IMatrixContext<E>) new MatrixContextByte       (contextType);
    if (type.equals(Double.class))      return (IMatrixContext<E>) new MatrixContextDouble     (contextType);
    if (type.equals(Float.class))       return (IMatrixContext<E>) new MatrixContextFloat      (contextType);
    if (type.equals(Fraction.class))    return (IMatrixContext<E>) new MatrixContextFraction   (contextType);
    if (type.equals(Integer.class))     return (IMatrixContext<E>) new MatrixContextInt        (contextType);
    if (type.equals(Long.class))        return (IMatrixContext<E>) new MatrixContextLong       (contextType);
    if (type.equals(Short.class))       return (IMatrixContext<E>) new MatrixContextShort      (contextType);
  
    // exception: element-type analyze unreached
    throw new UnsupportedOperationException("element-type analyze unreached.");
  }
}
