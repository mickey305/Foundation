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

package com.mickey305.foundation.v4.lang.math.factory;

import com.mickey305.foundation.v3.util.Assert;
import com.mickey305.foundation.v4.lang.math.operator.AbstractElementInitializer;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ElementInitializerFactory {
  private ElementInitializerFactory() {}
  
  /**
   * 要素初期化ファクトリーオブジェクトを生成して返却します
   * @param dummyElementType 未入力可。型を解決するために内部で使用する可変長引数です。
   * @param <E> {@link java.lang.Number}を継承しているクラスの総称型です
   * @return ファクトリーオブジェクト
   */
  @SuppressWarnings("unchecked")
  @Nonnull
  public static <E extends Number> AbstractElementInitializer<E> getFactory(E... dummyElementType) {
    final Class<E> type = (Class<E>) dummyElementType.getClass().getComponentType();
  
    Assert.requireNonNull(type);
    
    if (type.equals(BigDecimal.class))  return (AbstractElementInitializer<E>) new ElementInitializerBigDecimalFactory();
    if (type.equals(BigFraction.class)) return (AbstractElementInitializer<E>) new ElementInitializerBigFractionFactory();
    if (type.equals(BigInteger.class))  return (AbstractElementInitializer<E>) new ElementInitializerBigIntFactory();
    if (type.equals(Byte.class))        return (AbstractElementInitializer<E>) new ElementInitializerByteFactory();
    if (type.equals(Double.class))      return (AbstractElementInitializer<E>) new ElementInitializerDoubleFactory();
    if (type.equals(Float.class))       return (AbstractElementInitializer<E>) new ElementInitializerFloatFactory();
    if (type.equals(Fraction.class))    return (AbstractElementInitializer<E>) new ElementInitializerFractionFactory();
    if (type.equals(Integer.class))     return (AbstractElementInitializer<E>) new ElementInitializerIntFactory();
    if (type.equals(Long.class))        return (AbstractElementInitializer<E>) new ElementInitializerLongFactory();
    if (type.equals(Short.class))       return (AbstractElementInitializer<E>) new ElementInitializerShortFactory();
  
    // exception: element-type analyze unreached
    throw new UnsupportedOperationException("element-type analyze unreached. type=" + type);
  }
  
  /**
   * 要素初期化ファクトリーオブジェクトのシングルトンを返却します
   * @param dummyElementType 未入力可。型を解決するために内部で使用する可変長引数です。
   * @param <E> {@link java.lang.Number}を継承しているクラスの総称型です
   * @return ファクトリーオブジェクト
   */
  @SuppressWarnings("unchecked")
  @Nonnull
  public static <E extends Number> AbstractElementInitializer<E> getSharedFactory(E... dummyElementType) {
    final Class<E> type = (Class<E>) dummyElementType.getClass().getComponentType();
  
    Assert.requireNonNull(type);
    
    if (type.equals(BigDecimal.class))  return (AbstractElementInitializer<E>) ElementInitializerBigDecimalFactory.getInstance();
    if (type.equals(BigFraction.class)) return (AbstractElementInitializer<E>) ElementInitializerBigFractionFactory.getInstance();
    if (type.equals(BigInteger.class))  return (AbstractElementInitializer<E>) ElementInitializerBigIntFactory.getInstance();
    if (type.equals(Byte.class))        return (AbstractElementInitializer<E>) ElementInitializerByteFactory.getInstance();
    if (type.equals(Double.class))      return (AbstractElementInitializer<E>) ElementInitializerDoubleFactory.getInstance();
    if (type.equals(Float.class))       return (AbstractElementInitializer<E>) ElementInitializerFloatFactory.getInstance();
    if (type.equals(Fraction.class))    return (AbstractElementInitializer<E>) ElementInitializerFractionFactory.getInstance();
    if (type.equals(Integer.class))     return (AbstractElementInitializer<E>) ElementInitializerIntFactory.getInstance();
    if (type.equals(Long.class))        return (AbstractElementInitializer<E>) ElementInitializerLongFactory.getInstance();
    if (type.equals(Short.class))       return (AbstractElementInitializer<E>) ElementInitializerShortFactory.getInstance();
  
    // exception: element-type analyze unreached
    throw new UnsupportedOperationException("element-type analyze unreached. type=" + type);
  }
}
