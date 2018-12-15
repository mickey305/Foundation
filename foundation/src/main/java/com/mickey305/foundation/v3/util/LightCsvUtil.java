package com.mickey305.foundation.v3.util;

import com.mickey305.foundation.v3.util.bean.LightCsvDto;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
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
   *
   * @param csv
   */
  public static void split(@Nonnull LightCsvDto csv) {
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
      col = ESCAPED_ELEMENT_PATTERN.matcher(col).replaceAll("");
      col = INLINE_ESCAPED_ELEMENT_PATTERN.matcher(col).replaceAll(LightCsvDto.ESCAPE_CHAR.toString());
    
      elements.add(col);
    }
    csv.setElements(elements);
  }
  
  /**
   *
   * @param csv
   */
  public static void join(@Nonnull LightCsvDto csv) {
    // empty logic
    if (csv.getElements() == null || csv.getElements().isEmpty()) {
      csv.setLine("");
      return;
    }
    // main logic
    final List<String> elements = csv.getElements();
    final List<String> tmpElements = new ArrayList<>(elements.size());
    for (String element : elements) {
      if (StringUtils.isEmpty(element)) {
        tmpElements.add("");
        continue;
      }
      element = element.replace("" + LightCsvDto.ESCAPE_CHAR,
          "" + LightCsvDto.ESCAPE_CHAR + LightCsvDto.ESCAPE_CHAR);
      final char[] chars = element.toCharArray();
      if (element.contains(LightCsvDto.DLM.toString())) {
        final char wrapChar = LightCsvDto.ESCAPE_CHAR;
        tmpElements.add(wrapChar + element + wrapChar);
      } else if (element.contains(LightCsvDto.ESCAPE_CHAR.toString())) {
        final char wrapChar = LightCsvDto.ESCAPE_CHAR;
        tmpElements.add(wrapChar + element + wrapChar);
      } else if (Character.isWhitespace(chars[0]) || Character.isWhitespace(chars[chars.length - 1])) {
        final char wrapChar = LightCsvDto.ESCAPE_CHAR;
        tmpElements.add(wrapChar + element + wrapChar);
      } else {
        tmpElements.add(element);
      }
    }
    csv.setLine(StringUtils.join(tmpElements, LightCsvDto.DLM));
  }
}
