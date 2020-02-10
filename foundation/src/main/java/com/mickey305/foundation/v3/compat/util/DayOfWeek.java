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

import com.mickey305.foundation.v3.compat.exception.wrap.IllegalArgumentException;
import com.mickey305.foundation.v3.util.Log;

import java.util.Calendar;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

/**
 * enum day of the week
 * supported format: eng, jpn
 */
public enum DayOfWeek {
  Sunday(
      Calendar.SUNDAY,
      "日曜日",
      "Sunday"),
  Monday(
      Calendar.MONDAY,
      "月曜日",
      "Monday"),
  Tuesday(
      Calendar.TUESDAY,
      "火曜日",
      "Tuesday"),
  Wednesday(
      Calendar.WEDNESDAY,
      "水曜日",
      "Wednesday"),
  Thursday(
      Calendar.THURSDAY,
      "木曜日",
      "Thursday"),
  Friday(
      Calendar.FRIDAY,
      "金曜日",
      "Friday"),
  Saturday(
      Calendar.SATURDAY,
      "土曜日",
      "Saturday");
  
  DayOfWeek(int calCode, String jpn, String eng) {
    this.calCode = calCode;
    this.jpn = jpn;
    this.eng = eng;
  }
  
  private static final int WEEK_DAYS = 7;
  private static final DayOfWeek[] DAY_OF_WEEKS_ISO8601 = new DayOfWeek[] {
      Monday,
      Tuesday,
      Wednesday,
      Thursday,
      Friday,
      Saturday,
      Sunday};
  
  private final int calCode;
  private final String jpn;
  private final String eng;
  
  public int getCalCode() {
    return calCode;
  }
  
  public String getJpn() {
    return jpn;
  }
  
  public String getEng() {
    return eng;
  }
  
  public String getJpnShort() {
    // 先頭１文字を取得
    return this.getJpn().substring(0, 1);
  }
  
  public String getEngShort() {
    // 先頭３文字を取得
    return this.getEng().substring(0, 3);
  }
  
  /**
   * Gets the day-of-week {@code int} value.
   * <p>The values are numbered following the ISO-8601 standard, from 1 (Monday) to 7 (Sunday).</p>
   * @return the day-of-week, from 1 (Monday) to 7 (Sunday)
   */
  public int getValue() {
    // 0 (Sunday) -> edit target
    // 1 (Monday)
    // 2 (Tuesday)
    // 3 (Wednesday)
    // 4 (Thursday)
    // 5 (Friday)
    // 6 (Saturday)
    final int ordinal = ordinal();
    if (IS_DEBUG_MODE) {
      Log.d(this.name() + "[" + ordinal + "]");
    }
    // 7 (Sunday)
    // 1 (Monday)
    // 2 (Tuesday)
    // 3 (Wednesday)
    // 4 (Thursday)
    // 5 (Friday)
    // 6 (Saturday)
    return (ordinal < 1) ? ordinal + WEEK_DAYS : ordinal;
  }
  
  /**
   * Gets the {@link DayOfWeek} instance.
   * @param value day-of-week value.
   *              The values are numbered following the ISO-8601 standard, from 1 (Monday) to 7 (Sunday).
   * @return the {@link DayOfWeek} instance
   */
  public static DayOfWeek of(int value) {
    if (value < 1 || value > WEEK_DAYS) {
      throw new IllegalArgumentException("Invalid value for dayOfWeek: data=[" + value + "]");
    }
    return DAY_OF_WEEKS_ISO8601[value - 1];
  }
  
  /**
   * Gets the array of {@link DayOfWeek} for ISO-8601 standard.
   * @return array of {@link DayOfWeek}
   */
  public static DayOfWeek[] valuesOfISO8601() {
    return DAY_OF_WEEKS_ISO8601;
  }
  
  /**
   * Returns the day-of-week that is the specified number of days after this one.
   * <p>
   * The calculation rolls around the end of the week from Sunday to Monday.
   * The specified period may be negative.
   * <p>
   * This instance is immutable and unaffected by this method call.
   *
   * @param days the days to add, positive or negative
   * @return the resulting day-of-week, not null
   */
  public DayOfWeek plus(long days) {
    final int amount = (int) (days % WEEK_DAYS);
    final int valueISO8601 = this.getValue();
    return DAY_OF_WEEKS_ISO8601[(valueISO8601 - 1 + (amount + WEEK_DAYS)) % WEEK_DAYS];
  }
  
  /**
   * Returns the day-of-week that is the specified number of days before this one.
   * <p>
   * The calculation rolls around the start of the year from Monday to Sunday.
   * The specified period may be negative.
   * <p>
   * This instance is immutable and unaffected by this method call.
   *
   * @param days the days to subtract, positive or negative
   * @return the resulting day-of-week, not null
   */
  public DayOfWeek minus(long days) {
    return plus(-1 * (days % WEEK_DAYS));
  }
}
