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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {
  private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCase_01_01() throws Exception {
    {
      java.util.Date date = new Date();
      Log.i(SDF.format(date.getTime()));
    }
    {
      java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
      java.util.Date date = DateUtil.fromSqlDate(sqlDate);
      java.sql.Date sqlDate2 = DateUtil.toSqlDate(date);
      Log.i("SqlDate -> Date   : " + SDF.format(sqlDate.getTime()) + " / " + SDF.format(date.getTime()));
      Log.i("Date -> SqlDate   : same / " + SDF.format(sqlDate2.getTime()));
      Assert.assertEquals(SDF.format(sqlDate.getTime()), SDF.format(date.getTime()));
    }
    {
      java.sql.Time time = new java.sql.Time(System.currentTimeMillis());
      java.util.Date date = DateUtil.fromSqlTime(time);
      java.sql.Time time2 = DateUtil.toSqlTime(date);
      Log.i("Time -> Date      : " + SDF.format(time.getTime()) + " / " + SDF.format(date.getTime()));
      Log.i("Date -> Time      : same / " + SDF.format(time2.getTime()));
      Assert.assertEquals(SDF.format(time.getTime()), SDF.format(date.getTime()));
    }
    {
      java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
      timestamp.setNanos(timestamp.getNanos() + 100);
      java.util.Date date = DateUtil.fromSqlTimestamp(timestamp);
      java.sql.Timestamp timestamp2 = DateUtil.toSqlTimestamp(date);
      Log.i("Timestamp -> Date :" + SDF.format(timestamp.getTime()) + " / " + SDF.format(date.getTime()));
      Log.i("Timestamp nano-sec:" + timestamp.getNanos());
      Log.i("Date -> TimeStamp : same / " + SDF.format(timestamp2.getTime()));
      Log.i("Timestamp nano-sec:" + timestamp2.getNanos());
      Assert.assertEquals(SDF.format(timestamp.getTime()), SDF.format(date.getTime()));
    }
  }
}
