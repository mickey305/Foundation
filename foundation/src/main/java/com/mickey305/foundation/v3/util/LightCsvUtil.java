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

import com.mickey305.foundation.v3.util.bean.LightCsvDto;
import org.apache.commons.lang3.StringUtils;

import com.mickey305.foundation.v3.compat.exception.wrap.IllegalArgumentException;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LightCsvUtil {
  private static final Pattern ELEMENT_PATTERN;
  private static final Pattern ESCAPED_ELEMENT_PATTERN;
  private static final Pattern INLINE_ESCAPED_ELEMENT_PATTERN;
  
  static {
    ELEMENT_PATTERN = Pattern.compile(LightCsvDto.DLM
        + "(?=(([^"
        + LightCsvDto.ESCAPE_CHAR + "]*"
        + LightCsvDto.ESCAPE_CHAR + "){2})*[^"
        + LightCsvDto.ESCAPE_CHAR + "]*$)");
    ESCAPED_ELEMENT_PATTERN = Pattern.compile("^" + LightCsvDto.ESCAPE_CHAR + "|" + LightCsvDto.ESCAPE_CHAR + "$");
    INLINE_ESCAPED_ELEMENT_PATTERN = Pattern.compile("" + LightCsvDto.ESCAPE_CHAR + LightCsvDto.ESCAPE_CHAR);
  }
  
  private LightCsvUtil() {
  }
  
  /**
   * CSVのDTO格納情報をもとに、パース処理を実行する
   *
   * @param csv CSVのDTO
   */
  public static void split(@Nonnull LightCsvDto csv) {
    Assert.requireNonNull(csv);
    // empty logic
    if (StringUtils.isEmpty(csv.getLine())) {
      csv.setElements(new ArrayList<String>());
      return;
    }
    // main logic
    final String line = csv.getLine();
    final List<String> elements = (csv.getElements() == null) ? new ArrayList<String>() : csv.getElements();
    elements.clear();
    int count = 0;
    for (char c : line.toCharArray()) {
      if (c == LightCsvDto.ESCAPE_CHAR) {
        count++;
      }
    }
    if (count % 2 != 0) {
      throw new IllegalArgumentException("syntax error: data[" + line + "]");
    }
    final String[] tmpElements = ELEMENT_PATTERN.split(line, -1);
    for (String tmpElement : tmpElements) {
      String col = StringUtil.trim(tmpElement);
      Matcher sideEscaped = ESCAPED_ELEMENT_PATTERN.matcher(col);
      int sideEscapedCounter = 0;
      while (sideEscaped.find()) {
        sideEscapedCounter++;
      }
      // element data check
      if (sideEscapedCounter == 1) {
        throw new IllegalArgumentException("syntax error: column[" + col + "]");
      }
      if (sideEscapedCounter == 0
          && (col.contains("\r\n") || col.contains("\r") || col.contains("\n")
          || col.contains(LightCsvDto.ESCAPE_CHAR.toString()))) {
        throw new IllegalArgumentException("column data error: column[" + col + "]");
      }
      // edit task
      col = sideEscaped.replaceAll("");
      col = INLINE_ESCAPED_ELEMENT_PATTERN.matcher(col).replaceAll(LightCsvDto.ESCAPE_CHAR.toString());
      
      elements.add(col);
    }
    csv.setElements(elements);
  }
  
  /**
   * CSVのDTO格納情報をもとに、CSV作成処理を実行する
   *
   * @param csv CSVのDTO
   */
  public static void join(@Nonnull LightCsvDto csv) {
    Assert.requireNonNull(csv);
    // empty logic
    if (csv.getElements() == null || csv.getElements().isEmpty()) {
      csv.setLine("");
      return;
    }
    // main logic
    final List<String> elements = csv.getElements();
    final List<String> tmpElements = new ArrayList<>(elements.size());
    for (String element : elements) {
      // empty element task
      if (StringUtils.isEmpty(element)) {
        tmpElements.add("");
        continue;
      }
      // not empty element task
      element = element.replace("" + LightCsvDto.ESCAPE_CHAR,
          "" + LightCsvDto.ESCAPE_CHAR + LightCsvDto.ESCAPE_CHAR);
      final char[] chars = element.toCharArray();
      final char wrapChar = LightCsvDto.ESCAPE_CHAR;
      if (element.contains(LightCsvDto.DLM.toString())) {
        tmpElements.add(wrapChar + element + wrapChar);
      } else if (element.contains(LightCsvDto.ESCAPE_CHAR.toString())) {
        tmpElements.add(wrapChar + element + wrapChar);
      } else if (element.contains("\r\n") || element.contains("\n") || element.contains("\r")) {
        tmpElements.add(wrapChar + element + wrapChar);
      } else if (Character.isWhitespace(chars[0]) || Character.isWhitespace(chars[chars.length - 1])) {
        tmpElements.add(wrapChar + element + wrapChar);
      } else {
        tmpElements.add(element);
      }
    }
    csv.setLine(StringUtils.join(tmpElements, LightCsvDto.DLM));
  }
}
