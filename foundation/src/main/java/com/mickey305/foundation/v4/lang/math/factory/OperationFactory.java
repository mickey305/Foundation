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

package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v3.util.Assert;
import com.mickey305.foundation.v4.lang.math.operator.AbstractOperationFactory;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.BigInteger;

public class OperationFactory {
  private OperationFactory() {}
  
  /**
   * オペレーションファクトリーオブジェクトを生成して返却します
   * @param dummyElementType 未入力可。型を解決するために内部で使用する可変長引数です。
   * @param <E> {@link java.lang.Number}を継承しているクラスの総称型です
   * @return ファクトリーオブジェクト
   */
  @SuppressWarnings("unchecked")
  @Nonnull
  public static <E extends Number> AbstractOperationFactory<E> getFactory(E... dummyElementType) {
    final Class<E> type = (Class<E>) dummyElementType.getClass().getComponentType();
  
    Assert.requireNonNull(type);
    
    if (type.equals(BigDecimal.class))  return (AbstractOperationFactory<E>) new OperationBigDecimalFactory();
    if (type.equals(BigFraction.class)) return (AbstractOperationFactory<E>) new OperationBigFractionFactory();
    if (type.equals(BigInteger.class))  return (AbstractOperationFactory<E>) new OperationBigIntFactory();
    if (type.equals(Byte.class))        return (AbstractOperationFactory<E>) new OperationByteFactory();
    if (type.equals(Double.class))      return (AbstractOperationFactory<E>) new OperationDoubleFactory();
    if (type.equals(Float.class))       return (AbstractOperationFactory<E>) new OperationFloatFactory();
    if (type.equals(Fraction.class))    return (AbstractOperationFactory<E>) new OperationFractionFactory();
    if (type.equals(Integer.class))     return (AbstractOperationFactory<E>) new OperationIntFactory();
    if (type.equals(Long.class))        return (AbstractOperationFactory<E>) new OperationLongFactory();
    if (type.equals(Short.class))       return (AbstractOperationFactory<E>) new OperationShortFactory();
 
    // exception: element-type analyze unreached
    throw new UnsupportedOperationException();
  }
  
  /**
   * オペレーションファクトリーオブジェクトのシングルトンを返却します
   * @param dummyElementType 未入力可。型を解決するために内部で使用する可変長引数です。
   * @param <E> {@link java.lang.Number}を継承しているクラスの総称型です
   * @return ファクトリーオブジェクト
   */
  @SuppressWarnings("unchecked")
  @Nonnull
  public static <E extends Number> AbstractOperationFactory<E> getSharedFactory(E... dummyElementType) {
    final Class<E> type = (Class<E>) dummyElementType.getClass().getComponentType();
  
    Assert.requireNonNull(type);
    
    if (type.equals(BigDecimal.class))  return (AbstractOperationFactory<E>) OperationBigDecimalFactory.getInstance();
    if (type.equals(BigFraction.class)) return (AbstractOperationFactory<E>) OperationBigFractionFactory.getInstance();
    if (type.equals(BigInteger.class))  return (AbstractOperationFactory<E>) OperationBigIntFactory.getInstance();
    if (type.equals(Byte.class))        return (AbstractOperationFactory<E>) OperationByteFactory.getInstance();
    if (type.equals(Double.class))      return (AbstractOperationFactory<E>) OperationDoubleFactory.getInstance();
    if (type.equals(Float.class))       return (AbstractOperationFactory<E>) OperationFloatFactory.getInstance();
    if (type.equals(Fraction.class))    return (AbstractOperationFactory<E>) OperationFractionFactory.getInstance();
    if (type.equals(Integer.class))     return (AbstractOperationFactory<E>) OperationIntFactory.getInstance();
    if (type.equals(Long.class))        return (AbstractOperationFactory<E>) OperationLongFactory.getInstance();
    if (type.equals(Short.class))       return (AbstractOperationFactory<E>) OperationShortFactory.getInstance();
  
    // exception: element-type analyze unreached
    throw new UnsupportedOperationException();
  }
}
