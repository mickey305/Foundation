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

package com.mickey305.foundation.v3.util.constant;

public class EmptyArrayConst {
  public static final Object[] Obj = new Object[0];
  public static final String[] Str = new String[0];
  
  public static final Integer[] Int = new Integer[0];
  public static final Long[] Long = new Long[0];
  public static final Short[] Short = new Short[0];
  public static final Byte[] Byte = new Byte[0];
  public static final Character[] Char = new Character[0];
  public static final Float[] Float = new Float[0];
  public static final Double[] Double = new Double[0];
  public static final Boolean[] Bool = new Boolean[0];
  
  private EmptyArrayConst() {
    // nop
  }
  
  public static class Primitive {
    public static final int[] Int = new int[0];
    public static final long[] Long = new long[0];
    public static final short[] Short = new short[0];
    public static final byte[] Byte = new byte[0];
    public static final char[] Char = new char[0];
    public static final float[] Float = new float[0];
    public static final double[] Double = new double[0];
    public static final boolean[] Bool = new boolean[0];
    
    private Primitive() {
      // nop
    }
  }
}
