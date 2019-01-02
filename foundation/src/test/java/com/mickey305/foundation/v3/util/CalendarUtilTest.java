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

package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.compat.util.DayOfWeek;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarUtilTest {
  private static final Locale JP_LOCALE = new Locale("ja", "JP", "JP");
  private static final SimpleDateFormat JP_SDF = new SimpleDateFormat("GGGGy年M月d日 HH時mm分ss.SSS秒", JP_LOCALE);
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    {
      final Calendar cal = Calendar.getInstance();
      final DayOfWeek dow = CalendarUtil.getDayOfWeek(cal);
      Log.i(JP_SDF.format(cal.getTime()) +
          "[" + dow.getJpn() + "/" + dow.getJpnShort() + "/" + dow.getEng() + "/" + dow.getEngShort() + "]");
      Log.i(dow.getValue());
    }
    {
      final Calendar cal = Calendar.getInstance();
      final Calendar jpCal = CalendarUtil.toJpCal(cal);
      final DayOfWeek dow = CalendarUtil.getDayOfWeek(jpCal);
      Log.i(JP_SDF.format(jpCal.getTime()) +
          "[" + dow.getJpn() + "/" + dow.getJpnShort() + "/" + dow.getEng() + "/" + dow.getEngShort() + "]");
    }
  }
}