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

import com.mickey305.foundation.v3.compat.util.FunctionalMap;
import com.mickey305.foundation.v3.compat.util.FunctionalMapAdapter;

import javax.annotation.Nonnull;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

/**
 * Math util function provided class
 * - thread unsafe
 */
public final class MathFun {
  private static final int INTERVAL_BUFFER_SIZE = 1_000;
  private static final int CACHE_SIZE = INTERVAL_BUFFER_SIZE * 2; // effect vale: 2
  private static final FunctionalMap<Long, BigInteger> FIB_CACHE
      = new FunctionalMapAdapter<>(new LinkedHashMap<Long, BigInteger>(CACHE_SIZE) {
    private static final long serialVersionUID = 1535240143834849493L;
    @Override
    protected boolean removeEldestEntry(Map.Entry<Long, BigInteger> eldest)  {
      return size() > CACHE_SIZE;
    }
  });
  private static final BigInteger ZERO = BigInteger.ZERO;
  private static final BigInteger ONE = BigInteger.ONE;
  
  private MathFun() {
    // nop
  }
  
  // history of lash access key-data
  private static final AtomicLong PREV_KEY = new AtomicLong(0);
  
  /**
   * フィボナッチ数計算処理
   * @param n 項数
   * @return 指定された項数（ｎ）番目のフィボナッチ数
   */
  public static BigInteger fib(final long n) {
    // create data (n: 0, 1)
    FIB_CACHE.put(0L, ZERO);
    FIB_CACHE.put(1L, ONE);
    // stack control
    //*/
    if (Math.abs(n - PREV_KEY.get()) > INTERVAL_BUFFER_SIZE) {
      final int heap = INTERVAL_BUFFER_SIZE;
      long index = heap;
      do {
        fib(index, FIB_CACHE);
        index += heap;
      } while (index < n);
    }
    PREV_KEY.set(n);
    //*/
    // calc logic
    final BigInteger value = fib(n, FIB_CACHE);
    if (IS_DEBUG_MODE) {
      //Log.d("KeySet" + Arrays.toString(FIB_CACHE.keySet().toArray()));
    }
    return value;
  }
  
  private static BigInteger fib(@Nonnull final Long n,
                                @Nonnull final FunctionalMap<Long, BigInteger> cache) {
    //*/ invoke logic
    if (cache.containsKey(n)) return cache.get(n);
    final BigInteger value = fib(n - 1, cache).add(fib(n - 2, cache));
    cache.put(n, value);
    return value;
    /*/
    return cache.computeIfAbsent(n, new Function<Long, BigInteger>() {
      @Override
      public BigInteger apply(Long key) {
        final BigInteger value = fib(key - 1, cache).add(fib(key - 2, cache));
        if (IS_DEBUG_MODE && key % 100_000 == 0) {
          //Log.i("element[" + i + "]"); // light log
          Log.d("element[" + key + "]=" + value);
        }
        return value;
      }
    });
    //
    // todo: Java8での実装(with FoundationLambda: SE8)
    // return cache.computeIfAbsent(n, key -> fib(key - 1, cache).add(fib(key - 2, cache)));
    //
    //*/
  }
  
  /**
   * Math util class of primitive functions
   */
  public static class Primitive {
    private static final Map<Integer, Long> _FIB_CACHE = new HashMap<>();
  
    private Primitive() {
      // nop
    }
    
    // history of lash access key-data
    private static final AtomicInteger _PREV_KEY = new AtomicInteger(0);
    
    /**
     * フィボナッチ数計算処理
     * @param n 項数
     * @return 指定された項数（ｎ）番目のフィボナッチ数
     */
    public static long fib(final int n) {
      // create data (n: 0, 1)
      _FIB_CACHE.put(0, 0L);
      _FIB_CACHE.put(1, 1L);
      // stack control
      // スタックオーバーフローが発生する前に、演算結果がオーバーフローするため、
      // 下記のチェックは不要。（※JVMの環境・設定に依存する）
      /*/
      if (Math.abs(n - _PREV_KEY.get()) > INTERVAL_BUFFER_SIZE) {
        final int heap = INTERVAL_BUFFER_SIZE;
        int index = heap;
        do {
          fib(index, _FIB_CACHE);
          index += heap;
        } while (index < n);
      }
      _PREV_KEY.set(n);
      //*/
      // calc logic
      final long value = fib(n, _FIB_CACHE);
      if (IS_DEBUG_MODE) {
        //Log.d("KeySet" + Arrays.toString(FIB_CACHE.keySet().toArray()));
      }
      return value;
    }
    
    private static long fib(int n, Map<Integer, Long> cache) {
      if (cache.containsKey(n)) return cache.get(n);
      // check overflow
      long value = com.mickey305.foundation.v3.compat.lang.Math.addExact(
          fib(n - 2, cache),
          fib(n - 1, cache));
      cache.put(n, value);
      return value;
    }
  }
}
