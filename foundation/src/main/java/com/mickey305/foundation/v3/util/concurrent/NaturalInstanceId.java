/*
 * Copyright (c) 2018. K.Misaki
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

package com.mickey305.foundation.v3.util.concurrent;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class NaturalInstanceId {
  private static final Map<Class<?>, ICounter> registry;
  private static final StringBuilder builder;
  
  static {
    registry = new HashMap<>();
    builder = new StringBuilder();
  }
  
  /**
   * インスタンス固有のIDを生成する
   * <p>ID生成対象のコンストラクタ内でメソッドを1回実行し、ID番号を生成する
   * </p>
   * <p>ID体系：~[provider constant information]~[full class name for obj]~[this method invoked sequence]
   * </p>
   * <pre>{@code
   * public class SampleClass {
   *   private final String id;
   *   public SampleClass() {
   *     id = NaturalInstanceId.gen(this);
   *   }
   * }}</pre>
   *
   * @param obj 生成対象のインスタンス
   * @param <T> 生成対象の型
   * @return ID番号
   */
  // todo: IDの体系を外部から差し換えられるようにする
  @Nonnull
  public static <T> String gen(@Nonnull T obj) {
    final Class<?> target = obj.getClass();
    ICounter cachedCounter;
    synchronized (registry) {
      cachedCounter = registry.get(target);
      if (cachedCounter == null) {
        cachedCounter = new LongCounter();
        registry.put(target, cachedCounter);
      }
    }
    final long newId = cachedCounter.count();
    String result;
    synchronized (builder) {
      builder.setLength(0);
      result = builder
          .append("~").append(NaturalInstanceId.class.getName())
          .append("~").append(target.getName())
          .append("~").append(newId)
          .toString();
    }
    return result;
  }
}
