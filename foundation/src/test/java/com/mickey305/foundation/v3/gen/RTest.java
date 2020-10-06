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

package com.mickey305.foundation.v3.gen;

import com.mickey305.foundation.v3.util.Log;
import org.apache.commons.math3.fraction.BigFraction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void knownImmutableClasses() throws Exception {
    Log.i("-- test start");
    Log.i("status:" + R.knownImmutableClasses().contains(String.class));
    Log.i("status:" + R.knownImmutableClasses().contains(Double.class));
    Log.i("status:" + R.knownImmutableClasses().contains(Integer.class));
    Log.i("status:" + R.knownImmutableClasses().contains(String.class));
    Log.i("status:" + R.knownImmutableClasses().contains(String.class));
    Log.i("status:" + R.knownImmutableClasses().contains(BigFraction.class));
    Log.i("status:" + R.knownImmutableClasses().contains(Log.class));
    Log.i("-- test finish");
  }
}