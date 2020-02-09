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

package com.mickey305.foundation.v3.util.concurrent;

import com.mickey305.foundation.v3.util.Assert;
import com.mickey305.foundation.v3.util.Log;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public class NaturalInstanceId implements Serializable {
  private static final Map<Class<?>, ICounter> registry;
  private static final StringBuilder builder;
  private static final String empty = "";
  private static final long serialVersionUID = 2430359117017953063L;
  
  static {
    registry = new HashMap<>();
    builder = new StringBuilder();
  }
  
  private NaturalInstanceId() {
    // nop
  }
  
  /**
   * インスタンス固有のIDを生成する
   * <p>ID生成対象のコンストラクタ内でメソッドを1回実行し、ID番号を生成する
   * </p>
   * <p>ID体系：~[provider constant information]~[full class name for obj]~[hashcode constant empty]~[this method invoked sequence]
   * </p>
   * <pre>{@code
   * public class SampleClass {
   *   private final String id;
   *   public SampleClass() {
   *     id = NaturalInstanceId.gen(SampleClass.class);
   *   }
   * }}</pre>
   *
   * @param target 生成対象のクラス
   * @param <T> 生成対象の型
   * @return ID番号
   */
  // todo: IDの体系を外部から差し換えられるようにする
  @Nonnull
  public static <T> String gen(@Nonnull Class<T> target) {
    Assert.requireNonNull(target);
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
          .append("~").append(empty)
          .append("~").append(newId)
          .toString();
    }
    Assert.requireNonNull(result);
    if (IS_DEBUG_MODE) {
      Log.d("id[" + result + "]");
    }
    return result;
  }
}
