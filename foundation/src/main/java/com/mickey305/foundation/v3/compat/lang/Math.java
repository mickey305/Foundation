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

package com.mickey305.foundation.v3.compat.lang;

// JDK1.8 以降は標準API(java.lang.Math)を参照
/**
 * compatible util class
 * original util class link; {@link java.lang.Math}
 */
public final class Math {
  private Math() {
    // nop
  }
  
  public static int addExact(int x, int y) {
    int r = x + y;
    // HD 2-12 Overflow iff both arguments have the opposite sign of the result
    if (((x ^ r) & (y ^ r)) < 0) {
      throw new ArithmeticException("integer overflow. lhs[" + x + "], rhs[" + y + "], operator[+], result[" + r + "]");
    }
    return r;
  }
  
  public static long addExact(long x, long y) {
    long r = x + y;
    // HD 2-12 Overflow iff both arguments have the opposite sign of the result
    if (((x ^ r) & (y ^ r)) < 0) {
      throw new ArithmeticException("long overflow. lhs[" + x + "], rhs[" + y + "], operator[+], result[" + r + "]");
    }
    return r;
  }
  
  public static int subtractExact(int x, int y) {
    int r = x - y;
    // HD 2-12 Overflow iff the arguments have different signs and
    // the sign of the result is different from the sign of x
    if (((x ^ y) & (x ^ r)) < 0) {
      throw new ArithmeticException("integer overflow. lhs[" + x + "], rhs[" + y + "], operator[-], result[" + r + "]");
    }
    return r;
  }
  
  public static long subtractExact(long x, long y) {
    long r = x - y;
    // HD 2-12 Overflow iff the arguments have different signs and
    // the sign of the result is different from the sign of x
    if (((x ^ y) & (x ^ r)) < 0) {
      throw new ArithmeticException("long overflow. lhs[" + x + "], rhs[" + y + "], operator[-], result[" + r + "]");
    }
    return r;
  }
  
  public static int multiplyExact(int x, int y) {
    long r = (long)x * (long)y;
    if ((int)r != r) {
      throw new ArithmeticException("integer overflow. lhs[" + x + "], rhs[" + y + "], operator[*], result[" + r + "]");
    }
    return (int)r;
  }
  
  public static long multiplyExact(long x, int y) {
    return multiplyExact(x, (long)y);
  }
  
  public static long multiplyExact(long x, long y) {
    long r = x * y;
    long ax = java.lang.Math.abs(x);
    long ay = java.lang.Math.abs(y);
    if (((ax | ay) >>> 31 != 0)) {
      // Some bits greater than 2^31 that might cause overflow
      // Check the result using the divide operator
      // and check for the special case of Long.MIN_VALUE * -1
      if (((y != 0) && (r / y != x)) ||
          (x == Long.MIN_VALUE && y == -1)) {
        throw new ArithmeticException("long overflow. lhs[" + x + "], rhs[" + y + "], operator[*], result[" + r + "]");
      }
    }
    return r;
  }
  
  public static int incrementExact(int a) {
    if (a == Integer.MAX_VALUE) {
      throw new ArithmeticException("integer overflow. input[" + a + "], operator[++], result[" + (a + 1) + "]");
    }
    
    return a + 1;
  }
  
  public static long incrementExact(long a) {
    if (a == Long.MAX_VALUE) {
      throw new ArithmeticException("long overflow. input[" + a + "], operator[++], result[" + (a + 1L) + "]");
    }
    
    return a + 1L;
  }
  
  public static int decrementExact(int a) {
    if (a == Integer.MIN_VALUE) {
      throw new ArithmeticException("integer overflow. input[" + a + "], operator[--], result[" + (a - 1) + "]");
    }
    
    return a - 1;
  }
  
  public static long decrementExact(long a) {
    if (a == Long.MIN_VALUE) {
      throw new ArithmeticException("long overflow. input[" + a + "], operator[--], result[" + (a - 1L) + "]");
    }
    
    return a - 1L;
  }
  
  public static int negateExact(int a) {
    if (a == Integer.MIN_VALUE) {
      throw new ArithmeticException("integer overflow. input[" + a + "], lhs-operator[-], result[" + -a + "]");
    }
    
    return -a;
  }
  
  public static long negateExact(long a) {
    if (a == Long.MIN_VALUE) {
      throw new ArithmeticException("long overflow. input[" + a + "], lhs-operator[-], result[" + -a + "]");
    }
    
    return -a;
  }
  
  public static int toIntExact(long value) {
    if ((int)value != value) {
      throw new ArithmeticException("integer overflow. input[" + value + "], lhs-operator[(int)], result[" + (int)value + "]");
    }
    return (int)value;
  }
}
