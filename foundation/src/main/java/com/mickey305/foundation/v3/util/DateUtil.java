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

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

/**
 * {@link java.util.Date} convert utility class.
 * return object for all-method is new-instance.
 */
public class DateUtil {
  /**
   * default year constant
   */
  private static final int BASE_YEAR = 1970;
  
  /**
   * cast nano-sec to milli-sec digit constant
   */
  private static final int NANO_TO_MILLI_OFFSET_DIGIT = 1_000_000;
  
  /**
   * date type check const
   */
  private static final boolean DEFAULT_LENIENT = false;
  
  /**
   * timezone setting override constant
   */
  private static final TimeZone DEFAULT_TIMEZONE;
  
  static {
    final File propertyFile = ResFile.get("common.properties");
    final Properties properties = new Properties();
    try (InputStream is = new FileInputStream(propertyFile)) {
      properties.load(is);
    } catch (Exception e) {
      if (IS_DEBUG_MODE) {
        Log.d(e.getMessage());
      }
    }
    final String zoneId = (properties.isEmpty())
        ? null
        : properties.getProperty("defaultTimeZone");
    DEFAULT_TIMEZONE = (zoneId == null)
        ? TimeZone.getDefault()
        : TimeZone.getTimeZone(zoneId);
  }
  
  private DateUtil() {
    // nop
  }
  
  /**
   * 日時情報を{@link java.util.Date}から{@link java.sql.Date}へ変換する。
   * 変換する際に、時・分・秒・ミリ秒の情報を初期化する。
   * @param date 変換対象の日時情報
   * @return 変換後の日時情報
   */
  @Nonnull
  public static java.sql.Date toSqlDate(@Nonnull java.util.Date date) {
    final Calendar cal = toCal(date);
    // time info initialization
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return new java.sql.Date(cal.getTimeInMillis());
  }
  
  /**
   * 日時情報を{@link java.util.Date}から{@link java.sql.Time}へ変換する。
   * 変換する際に、年・月・日の情報を初期化する。
   * @param date 変換対象の日時情報
   * @return 変換後の日時情報
   */
  @Nonnull
  public static java.sql.Time toSqlTime(@Nonnull java.util.Date date) {
    final Calendar cal = toCal(date);
    // date info initialization
    cal.set(Calendar.YEAR, BASE_YEAR);
    cal.set(Calendar.MONTH, Calendar.JANUARY);
    cal.set(Calendar.DATE, 1);
    return new java.sql.Time(cal.getTimeInMillis());
  }
  
  /**
   * 日時情報を{@link java.util.Date}から{@link java.sql.Timestamp}へ変換する。
   * @param date 変換対象の日時情報
   * @return 変換後の日時情報
   */
  @Nonnull
  public static java.sql.Timestamp toSqlTimestamp(@Nonnull java.util.Date date) {
    final Calendar cal = toCal(date);
    return new java.sql.Timestamp(cal.getTimeInMillis());
  }
  
  /**
   * 日時情報を{@link java.util.Date}から{@link java.util.Calendar}へ変換する。
   * 変換する際に、タイムゾーンの情報を更新する。
   * @param date 変換対象の日時情報
   * @return 変換後の日時情報
   */
  @Nonnull
  public static Calendar toCal(@Nonnull java.util.Date date) {
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    // set timezone
    cal.setTimeZone(DEFAULT_TIMEZONE);
    // set lenient
    cal.setLenient(DEFAULT_LENIENT);
    return cal;
  }
  
  /**
   * 日時情報を{@link java.util.Date}から{@link java.util.Calendar}へ変換する。
   * 変換する際に、タイムゾーンの情報を更新する。
   * @param date 変換対象の日時情報
   * @return 変換後の日時情報
   */
  @Nonnull
  public static Calendar toJpCal(@Nonnull java.util.Date date) {
    final Calendar cal = toCal(date);
    return CalendarUtil.toJpCal(cal);
  }
  
  /**
   * 日時情報を{@link java.sql.Date}から{@link java.util.Date}へ変換する。
   * @param sqlDate 変換対象の日時情報
   * @return 変換後の日時情報
   */
  @Nonnull
  public static java.util.Date fromSqlDate(@Nonnull java.sql.Date sqlDate) {
    final java.util.Date date = new java.util.Date();
    date.setTime(sqlDate.getTime());
    return date;
  }
  
  /**
   * 日時情報を{@link java.sql.Time}から{@link java.util.Date}へ変換する。
   * @param time 変換対象の日時情報
   * @return 変換後の日時情報
   */
  @Nonnull
  public static java.util.Date fromSqlTime(@Nonnull java.sql.Time time) {
    final java.util.Date date = new java.util.Date();
    date.setTime(time.getTime());
    return date;
  }
  
  /**
   * 日時情報を{@link java.sql.Timestamp}から{@link java.util.Date}へ変換する。
   * <p>
   * タイムスタンプ型から日付型への変換のため、マイクロ秒・ナノ秒（1.0[sec]*10^(-4) ~ 1.0[sec]*10^(-9)）
   * の範囲の情報が失われる。</p>
   * @param timestamp 変換対象の日時情報
   * @return 変換後の日時情報
   */
  @Nonnull
  public static java.util.Date fromSqlTimestamp(@Nonnull java.sql.Timestamp timestamp) {
    final java.util.Date date = new java.util.Date();
    // cast: nano-sec of timestamp -> milli-sec of date
    if (IS_DEBUG_MODE) {
      final int nanos = timestamp.getNanos();
      final int casted = (nanos / NANO_TO_MILLI_OFFSET_DIGIT) * NANO_TO_MILLI_OFFSET_DIGIT;
      final int lost = nanos - casted;
      Log.d("casted nanos data: " + casted);
      Log.d("lost nanos data: " + lost);
    }
    date.setTime(timestamp.getTime());
    return date;
  }
  
  /**
   * 日時情報を{@link java.util.Calendar}から{@link java.util.Date}へ変換する。
   * 変換する際に、タイムゾーンの情報を更新する。
   * @param cal 変換対象の日時情報
   * @return 変換後の日時情報
   */
  @Nonnull
  public static java.util.Date fromCal(@Nonnull Calendar cal) {
    // set timezone
    cal.setTimeZone(DEFAULT_TIMEZONE);
    // set lenient
    cal.setLenient(DEFAULT_LENIENT);
    // new Date object
    //   same code: return new java.util.Date(cal.getTimeInMillis());
    return cal.getTime();
  }
  
  /**
   * 入力された日時情報の曜日を取得する
   * @param date 変換対象の日時情報
   * @return 曜日
   */
  @Nonnull
  public static DayOfWeek getDayOfWeek(@Nonnull java.util.Date date) {
    final Calendar cal = toCal(date);
    return CalendarUtil.getDayOfWeek(cal);
  }
}
