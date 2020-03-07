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

package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.compat.exception.wrap.IllegalStateException;
import com.mickey305.foundation.v3.compat.util.DayOfWeek;
import com.mickey305.foundation.v3.util.constant.LocaleConst;

import javax.annotation.Nonnull;
import java.util.Calendar;

/**
 * {@link java.util.Calendar} convert utility class.
 * return object for all-method is new-instance.
 */
public class CalendarUtil {
  /**
   * date type check const
   */
  private static final boolean DEFAULT_LENIENT = false;
  
  private CalendarUtil() {
    // nop
  }
  
  /**
   * 入力されたカレンダーをもとに和暦対応のカレンダーを取得する
   * @param cal カレンダー
   * @return 和暦対応カレンダー
   */
  @Nonnull
  public static Calendar toJpCal(@Nonnull Calendar cal) {
    Assert.requireNonNull(cal);
    final Calendar jpCal = Calendar.getInstance(LocaleConst.JP);
    // information setting
    jpCal.setTimeZone(cal.getTimeZone());
    // override lenient flag
    jpCal.setLenient(DEFAULT_LENIENT);
    // update time
    jpCal.setTime(cal.getTime());
    Assert.requireNonNull(jpCal);
    return jpCal;
  }
  
  /**
   * 入力されたカレンダーの曜日を取得する
   * @param cal カレンダー
   * @return 曜日
   */
  @Nonnull
  public static DayOfWeek getDayOfWeek(@Nonnull Calendar cal) {
    Assert.requireNonNull(cal);
    switch (cal.get(Calendar.DAY_OF_WEEK)) {
      case Calendar.SUNDAY: return DayOfWeek.Sunday;
      case Calendar.MONDAY: return DayOfWeek.Monday;
      case Calendar.TUESDAY: return DayOfWeek.Tuesday;
      case Calendar.WEDNESDAY: return DayOfWeek.Wednesday;
      case Calendar.THURSDAY: return DayOfWeek.Thursday;
      case Calendar.FRIDAY: return DayOfWeek.Friday;
      case Calendar.SATURDAY: return DayOfWeek.Saturday;
    }
    throw new IllegalStateException("dayOfWeek-check failed.");
  }
}
