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

import com.mickey305.foundation.v3.util.Assert;
import com.mickey305.foundation.v4.lang.math.operator.AbstractNumberOperation;
import com.mickey305.foundation.v4.lang.math.operator.IOperationFactory;

import java.util.Map;

public class OperatorRegistryUtil {
  private OperatorRegistryUtil() {}
  
  /**
   *
   * @param op
   * @param rop
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerAll(
      Map<AbstractNumberTable.Operator,           AbstractNumberOperation<T, T>> op,
      Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> rop,
      IOperationFactory<T> factory) {
    // register std-operators
    registerAdd(op, factory);
    registerSub(op, factory);
    registerMulti(op, factory);
    registerDiv(op, factory);
    registerMax(op, factory);
    registerMin(op, factory);
    
    // register condition
    registerEq(rop, factory);
    registerNE(rop, factory);
    registerGT(rop, factory);
    registerGE(rop, factory);
    registerLT(rop, factory);
    registerLE(rop, factory);
    
    Assert.requireEquals(op.size(), AbstractNumberTable.Operator.values().length);
    Assert.requireEquals(rop.size(), AbstractNumberTable.RelationalOperator.values().length);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerAdd(
      Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> op, IOperationFactory<T> factory) {
    AbstractNumberOperation<T, T> res = op.put(AbstractNumberTable.Operator.Add, factory.add());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerSub(
      Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> op, IOperationFactory<T> factory) {
    AbstractNumberOperation<T, T> res = op.put(AbstractNumberTable.Operator.Sub, factory.sub());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerMulti(
      Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> op, IOperationFactory<T> factory) {
    AbstractNumberOperation<T, T> res = op.put(AbstractNumberTable.Operator.Multi, factory.multi());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerDiv(
      Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> op, IOperationFactory<T> factory) {
    AbstractNumberOperation<T, T> res = op.put(AbstractNumberTable.Operator.Div, factory.div());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerMax(
      Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> op, IOperationFactory<T> factory) {
    AbstractNumberOperation<T, T> res = op.put(AbstractNumberTable.Operator.Max, factory.max());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerMin(
      Map<AbstractNumberTable.Operator, AbstractNumberOperation<T, T>> op, IOperationFactory<T> factory) {
    AbstractNumberOperation<T, T> res = op.put(AbstractNumberTable.Operator.Min, factory.min());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerEq(
      Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> op,
      IOperationFactory<T> factory) {
    AbstractNumberOperation<T, Boolean> res = op.put(AbstractNumberTable.RelationalOperator.Eq, factory.eq());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerNE(
      Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> op,
      IOperationFactory<T> factory) {
    AbstractNumberOperation<T, Boolean> res = op.put(AbstractNumberTable.RelationalOperator.NE, factory.ne());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerGT(
      Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> op,
      IOperationFactory<T> factory) {
    AbstractNumberOperation<T, Boolean> res = op.put(AbstractNumberTable.RelationalOperator.GT, factory.gt());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerGE(
      Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> op,
      IOperationFactory<T> factory) {
    AbstractNumberOperation<T, Boolean> res = op.put(AbstractNumberTable.RelationalOperator.GE, factory.ge());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerLT(
      Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> op,
      IOperationFactory<T> factory) {
    AbstractNumberOperation<T, Boolean> res = op.put(AbstractNumberTable.RelationalOperator.LT, factory.lt());
    Assert.requireNull(res);
  }
  
  /**
   *
   * @param op
   * @param factory
   * @param <T>
   */
  public static <T extends Number> void registerLE(
      Map<AbstractNumberTable.RelationalOperator, AbstractNumberOperation<T, Boolean>> op,
      IOperationFactory<T> factory) {
    AbstractNumberOperation<T, Boolean> res = op.put(AbstractNumberTable.RelationalOperator.LE, factory.le());
    Assert.requireNull(res);
  }
}
