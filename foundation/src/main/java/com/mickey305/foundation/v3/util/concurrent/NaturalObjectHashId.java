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

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public class NaturalObjectHashId implements Serializable {
  private static final StringBuilder builder;
  private static final String empty = "";
  private static final long serialVersionUID = 6090276367741675419L;
  
  static {
    builder = new StringBuilder();
  }
  
  private NaturalObjectHashId() {
    // nop
  }
  
  /**
   * インスタンス固有のIDを生成する（同一クラスのインスタンスの内、ハッシュコードが同一の場合は、同じIDを返却する）
   * <p>ID生成対象のインスタンスメソッド内で実行し、ID番号を生成する
   * </p>
   * <p>ID体系：~[provider constant information]~[full class name for obj]~[this obj hashcode]~[sequence constant empty]
   * </p>
   * <pre>{@code
   * public class SampleClass {
   *   public String getId() {
   *     return NaturalObjectHashId.gen(this);
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
    Assert.requireNonNull(obj);
    final Class<?> target = obj.getClass();
    String result;
    synchronized (builder) {
      builder.setLength(0);
      result = builder
          .append("~").append(NaturalObjectHashId.class.getName())
          .append("~").append(target.getName())
          .append("~").append(obj.hashCode())
          .append("~").append(empty)
          .toString();
    }
    Assert.requireNonNull(result);
    if (IS_DEBUG_MODE) {
      Log.d("id[" + result + "]");
    }
    return result;
  }
}
