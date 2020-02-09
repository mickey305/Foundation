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

package com.mickey305.foundation.v3.compat.util;

import com.mickey305.foundation.v3.util.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DayOfWeekTest {
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    DayOfWeek dayOfWeek = DayOfWeek.Monday;
    Log.i(dayOfWeek.getJpn());
    Log.i(dayOfWeek.plus(1).getJpn());
    Log.i(dayOfWeek.plus(2).getJpn());
    Log.i(dayOfWeek.plus(3).getJpn());
    Log.i(dayOfWeek.plus(4).getJpn());
    Log.i(dayOfWeek.plus(5).getJpn());
    Log.i(dayOfWeek.plus(6).getJpn());
    Log.i(dayOfWeek.plus(7).getJpn());
  }
  
  @Test
  public void testCase_02_01() throws Exception {
    DayOfWeek dayOfWeek = DayOfWeek.Monday;
    Log.i(dayOfWeek.getJpn());
    Log.i(dayOfWeek.minus(1).getJpn());
    Log.i(dayOfWeek.minus(2).getJpn());
    Log.i(dayOfWeek.minus(3).getJpn());
    Log.i(dayOfWeek.minus(4).getJpn());
    Log.i(dayOfWeek.minus(5).getJpn());
    Log.i(dayOfWeek.minus(6).getJpn());
    Log.i(dayOfWeek.minus(7).getJpn());
  }
}