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

package com.mickey305.foundation.v3.lang.math.operator;

import com.mickey305.foundation.v3.compat.exception.wrap.UnsupportedOperationException;
import com.mickey305.foundation.v3.compat.util.BinaryFunction;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class AbstractNumberOperation<R> implements
    BinaryFunction<Number, Number, R>,
    OperationExtensions<Number, Number, R> {
  private BinaryFunction<Number, Number, R> extension;
  
  @Deprecated
  public AbstractNumberOperation() {
    this(null);
  }
  
  @Deprecated
  public AbstractNumberOperation(BinaryFunction<Number, Number, R> extension) {
    this.setExtension(extension);
  }
  
  public BinaryFunction<Number, Number, R> getExtension() {
    return extension;
  }
  
  /**
   * {@inheritDoc}
   */
  @Deprecated
  @Override
  public void setExtension(BinaryFunction<Number, Number, R> extension) {
    this.extension = extension;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public R apply(Number l, Number r) {
    R result = null;
    
    // extension operation invoke
    if (this.getExtension() != null)
      result = this.getExtension().apply(l, r);
    
    if (result != null)
      return result;
    
    // default operation invoke
    result = this.operationDefault(l, r);
    
    if (result != null)
      return result;
    
    // throwing exception
    throw new UnsupportedOperationException("unSupported binary function.");
  }
  
  /**
   * 数値変換メソッド
   * <p>
   * 数値クラス{@link Number}を任意精度整数クラス{@link BigInteger}に変換する。
   * </p>
   *
   * @param data 変換前数値データ
   * @return 変換後数値データ
   */
  protected BigInteger convertToBigInteger(Number data) {
    if (data.getClass().equals(Integer.class))
      return new BigInteger(String.valueOf(data.intValue()));
    
    if (data.getClass().equals(Long.class))
      return new BigInteger(String.valueOf(data.longValue()));
    
    if (data.getClass().equals(Short.class))
      return new BigInteger(String.valueOf(data.shortValue()));
    
    if (data.getClass().equals(Byte.class))
      return new BigInteger(String.valueOf(data.byteValue()));
    
    if (data.getClass().equals(BigInteger.class))
      return (BigInteger) data;
    
    // throwing exception
    throw new UnsupportedOperationException("elementData-convert failed.");
  }
  
  /**
   * 数値変換メソッド
   * <p>
   * 数値クラス{@link Number}を分数クラス{@link Fraction}に変換する。
   * </p>
   *
   * @param data 変換前数値データ
   * @return 変換後数値データ
   */
  protected Fraction convertToFraction(Number data) {
    if (data.getClass().equals(Integer.class))
      return new Fraction(data.intValue());
    
    if (data.getClass().equals(Long.class))
      return new Fraction(data.longValue());
    
    if (data.getClass().equals(Float.class))
      return new Fraction(data.floatValue());
    
    if (data.getClass().equals(Double.class))
      return new Fraction(data.doubleValue());
    
    if (data.getClass().equals(Short.class))
      return new Fraction(data.shortValue());
    
    if (data.getClass().equals(Byte.class))
      return new Fraction(data.byteValue());
    
    if (data.getClass().equals(Fraction.class))
      return (Fraction) data;
    
    // throwing exception
    throw new UnsupportedOperationException("elementData-convert failed.");
  }
  
  /**
   * 数値変換メソッド
   * <p>
   * 数値クラス{@link Number}を任意精度分数クラス{@link BigFraction}に変換する。
   * </p>
   *
   * @param data 変換前数値データ
   * @return 変換後数値データ
   */
  protected BigFraction convertToBigFraction(Number data) {
    if (data.getClass().equals(Integer.class))
      return new BigFraction(data.intValue());
    
    if (data.getClass().equals(Long.class))
      return new BigFraction(data.longValue());
    
    if (data.getClass().equals(Float.class))
      return new BigFraction(data.floatValue());
    
    if (data.getClass().equals(Double.class))
      return new BigFraction(data.doubleValue());
    
    if (data.getClass().equals(Short.class))
      return new BigFraction(data.shortValue());
    
    if (data.getClass().equals(Byte.class))
      return new BigFraction(data.byteValue());
    
    if (data.getClass().equals(BigInteger.class))
      return new BigFraction((BigInteger) data);
    
    if (data.getClass().equals(BigDecimal.class)) {
      final BigDecimal decimal = (BigDecimal) data;
      final BigInteger ten = BigInteger.TEN;
      if (decimal.scale() >= 0) {
        return new BigFraction(decimal.unscaledValue(), ten.pow(decimal.scale()));
      } else {
        return new BigFraction(decimal.unscaledValue().multiply(ten.pow(-1 * decimal.scale())));
      }
    }
    
    if (data.getClass().equals(Fraction.class))
      return new BigFraction(
          ((Fraction) data).getNumerator(), ((Fraction) data).getDenominator());
    
    if (data.getClass().equals(BigFraction.class))
      return (BigFraction) data;
    
    // throwing exception
    throw new UnsupportedOperationException("elementData-convert failed.");
  }
  
  /**
   * デフォルト演算・実装メソッド
   *
   * @param l 演算項１
   * @param r 演算項２
   * @return 演算結果
   */
  protected abstract R operationDefault(Number l, Number r);
}
