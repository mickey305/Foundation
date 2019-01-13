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

package com.mickey305.foundation.v3.lang.math;

import com.mickey305.foundation.v3.compat.util.Function;
import com.mickey305.foundation.v3.compat.util.FunctionalMap;
import com.mickey305.foundation.v3.compat.util.FunctionalMapAdapter;
import com.mickey305.foundation.v3.util.Assert;

import javax.annotation.Nonnull;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

public class MathFun {
  private static final FunctionalMap<BigInteger, BigInteger> FIB_CACHE
      = new FunctionalMapAdapter<>(new ConcurrentHashMap<BigInteger, BigInteger>());
  private static final BigInteger ZERO = BigInteger.ZERO;
  private static final BigInteger ONE = BigInteger.ONE;
  private static final BigInteger TWO = BigInteger.valueOf(2L);
  
  private MathFun() {
    // nop
  }
  
  /**
   * フィボナッチ数計算処理
   * @param n 項数
   * @return 指定された項数（ｎ）番目のフィボナッチ数
   */
  public static BigInteger fib(final long n) {
    final BigInteger ini = BigInteger.valueOf(n);
    Assert.requireNonNull(FIB_CACHE);
    Assert.requireNonNull(ini);
    return fib(ini, FIB_CACHE);
  }
  
  private static BigInteger fib(@Nonnull final BigInteger n,
                                @Nonnull final FunctionalMap<BigInteger, BigInteger> cache) {
    return cache.computeIfAbsent(n, new Function<BigInteger, BigInteger>() {
      @Override
      public BigInteger apply(BigInteger i) {
        return (i.compareTo(TWO) < 0)
            ? /* i < 2 */ (i.compareTo(ONE) < 0)
            ? /* and i < 1 */ ZERO
            : /* and i >= 1 */ ONE
            : /* i >= 2 */ fib(i.subtract(ONE), cache).add(fib(i.subtract(TWO), cache));
      }
    });
  }
}
