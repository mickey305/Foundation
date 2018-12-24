package com.mickey305.foundation.v3.ansi.code;

import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v3.validation.annotation.EscapeReject;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AnsiStringBuilder extends AbstractAnsiStringBuilder
    implements AnsiAppendable, CharSequence, Serializable {
  /**
   * use serialVersionUID for interoperability
   */
  private static final long serialVersionUID = 5357077699693625877L;
  
  private StringBuilder stringBuilder;
  private int codeLength;
  private boolean reliableCodeLength;
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Constructor                                                                                                    //
  //===----------------------------------------------------------------------------------------------------------===//
  public AnsiStringBuilder() {
    this(new StringBuilder());
  }
  
  public AnsiStringBuilder(int capacity) {
    this(new StringBuilder(capacity));
  }
  
  public AnsiStringBuilder(@EscapeReject String str) {
    this(new StringBuilder(str));
  }
  
  public AnsiStringBuilder(@EscapeReject CharSequence seq) {
    this(new StringBuilder(seq));
  }
  
  public AnsiStringBuilder(@EscapeReject StringBuilder stringBuilder) {
    this.setStringBuilder(stringBuilder);
    this.setCodeLength(0);
    this.setReliableCodeLength(false);
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//

  /**
   * {@inheritDoc}
   */
  @Override
  public AnsiStringBuilder append(CharSequence csq) throws IOException {
    this.setReliableCodeLength(false);
    this.getStringBuilder().append(csq);
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AnsiStringBuilder append(CharSequence csq, int start, int end) throws IOException {
    this.setReliableCodeLength(false);
    this.getStringBuilder().append(csq, start, end);
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AnsiStringBuilder append(char c) throws IOException {
    this.setReliableCodeLength(false);
    this.getStringBuilder().append(c);
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public AnsiStringBuilder append(Escape escape) {
    this.setReliableCodeLength(false);
    this.getStringBuilder().append(escape.code());
    return this;
  }
  
  public AnsiStringBuilder append(@EscapeReject String target) {
    this.setReliableCodeLength(false);
    this.getStringBuilder().append(target);
    return this;
  }
  
  public AnsiStringBuilder append(@EscapeReject Object object) {
    this.setReliableCodeLength(false);
    this.getStringBuilder().append(object);
    return this;
  }
  
  public AnsiStringBuilder clear() {
    return this.delete();
  }
  
  public AnsiStringBuilder delete() {
    this.setCodeLength(0);
    this.setReliableCodeLength(true);
    return this.delete(0, this.getStringBuilder().length());
  }
  
  public AnsiStringBuilder delete(@EscapeReject String target) {
    int index = 0;
    do {
      index = this.deleteFirst(target, index);
    } while (index >= 0);
    return this;
  }
  
  public AnsiStringBuilder delete(Escape escape) {
    int index = 0;
    do {
      index = this.deleteFirst(escape.code(), index);
    } while (index >= 0);
    return this;
  }
  
  public AnsiStringBuilder deleteEscapeCode() {
    final Escape[] escapeLst = Escape.values();
    for (Escape escape : escapeLst) {
      this.delete(escape.code());
    }
    this.setCodeLength(0);
    this.setReliableCodeLength(true);
    return this;
  }
  
  public int deleteFirst(@EscapeReject String target) {
    return this.deleteFirst(target, 0);
  }
  
  public int deleteFirst(@EscapeReject String target, int fromIndex) {
    StringBuilder sb = this.getStringBuilder();
    final int index = sb.indexOf(target, fromIndex);
    if (index >= 0) {
      this.delete(index, index + target.length());
      return index;
    }
    return -1;
  }
  
  public AnsiStringBuilder insert(int offset, @EscapeReject String str) {
    this.setReliableCodeLength(false);
    this.getStringBuilder().insert(offset, str);
    return this;
  }
  
  public AnsiStringBuilder insert(int offset, Escape escape) {
    this.setReliableCodeLength(false);
    this.insert(offset, escape.code());
    return this;
  }
  
  public AnsiStringBuilder insert(int offset, @EscapeReject Object object) {
    this.setReliableCodeLength(false);
    this.getStringBuilder().insert(offset, object);
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int length() {
    this.updateCodeLength();
    int escapeLen = (this.getCodeLength() == 0)
        ? 0
        : Escape.Reset.code().length();
    return this.getStringBuilder().length() + escapeLen;
  }
  
  public int length(Without without) {
    this.updateCodeLength();
    if (without == Without.EscapeCode) {
      return this.getStringBuilder().length() - this.getCodeLength();
    }
    return -1;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public char charAt(int index) {
    return this.getStringBuilder().charAt(index);
  }
  
  public void setLength(int newLength) {
    this.getStringBuilder().setLength(newLength);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public CharSequence subSequence(int start, int end) {
    return this.getStringBuilder().subSequence(start, end);
  }
  
  public void trimToSize() {
    this.getStringBuilder().trimToSize();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    this.updateCodeLength();
    if (this.getCodeLength() != 0)
      this.append(Escape.Reset.code());
    StringBuilder sb = this.getStringBuilder();
    String target = sb.toString();
    if (this.getCodeLength() != 0) {
      int start = sb.lastIndexOf(Escape.Reset.code());
      int end = start + Escape.Reset.code().length();
      sb.delete(start, end);
    }
    return target;
//        String accent = (this.getCodeLength() == 0)
//                ? ""
//                : Escape.Reset.code();
//        return this.getStringBuilder().toString() + accent;

  }
  
  public String toString(Without without) {
    if (without == Without.EscapeCode) {
      String target = this.getStringBuilder().toString();
      target = target.replaceAll(ANSI_CODE_REGEX, "");
      return target;
    }
    return null;
  }
  
  private void addCodeLength(int offset) {
    this.setCodeLength(this.getCodeLength() + offset);
  }
  
  private String buildHexString(String data) {
    return this.buildHexString(data, 4, null, null, " ");
  }
  
  private String buildHexString(String data, int shift,
                                String startSpacer, String endSpacer, String spacer) {
    if (startSpacer == null) startSpacer = "";
    if (endSpacer == null) endSpacer = "";
    if (spacer == null) spacer = "";
    StringBuilder hexValue = new StringBuilder();
    String format = "%" + shift + "s";
    char[] dataAry = data.toCharArray();
    for (int i = 0; i < dataAry.length; i++) {
      char ch = dataAry[i];
      String hex = Integer.toHexString(ch);
      hexValue.append(String.format(format, hex));
      if (i != dataAry.length - 1)
        hexValue.append("#");
    }
    return startSpacer + hexValue.toString()
        .replace(" ", "0")
        .replace("#", spacer) + endSpacer;
  }
  
  private AnsiStringBuilder delete(int start, int end) {
    this.setReliableCodeLength(false);
    this.getStringBuilder().delete(start, end);
    return this;
  }
  
  private void dump(int expectedLength, int actualLength,
                    StringBuilder testData, Map<Integer, String> resultEscapeMap, Throwable th) {
    // waring performance!
    StringBuilder result = new StringBuilder();
    result.append(System.lineSeparator());
    result.append("//===--- DATA INFO ----------------------------------------------------------------------===//");
    result.append(System.lineSeparator());
    result.append("testDataLength             :").append(testData.length());
    result.append(System.lineSeparator());
    result.append("expectedEscapeDataLength   :").append(expectedLength);
    result.append(System.lineSeparator());
    result.append("actualEscapeDataLength     :").append(actualLength);
    result.append(System.lineSeparator());
    result.append("testDataHex                :").append(this.buildHexString(testData.toString()));
    result.append(System.lineSeparator());
    int number = 1;
    for (Map.Entry<Integer, String> entry : resultEscapeMap.entrySet()) {
      result.append(String.format("resultEscapeMapValueHex%03d ", number)).append(":");
      for (int i = 0; i < entry.getKey(); i++) {
        result.append("     ");
      }
      result.append(this.buildHexString(entry.getValue()));
      result.append(System.lineSeparator());
      number++;
    }
    result.append("//===--- CLASS INFO ---------------------------------------------------------------------===//");
    result.append(System.lineSeparator());
    result.append("testData                   :").append(ToStringBuilder.reflectionToString(testData));
    result.append(System.lineSeparator());
    result.append("resultEscapeMap            :").append(ToStringBuilder.reflectionToString(resultEscapeMap));
    result.append(System.lineSeparator());
    result.append("//===--- ESCAPE CODE INFO ---------------------------------------------------------------===//");
    result.append(System.lineSeparator());
    for (Escape escape : Escape.values()) {
      result.append(this.buildHexString(escape.code())).append(" // ").append(escape.name());
      result.append(System.lineSeparator());
    }
    Log.d(result.toString());
    Log.i(th.getMessage());
    for (StackTraceElement e : th.getStackTrace())
      Log.i(e.toString());
  }
  
  private void updateCodeLength() {
    if (!this.isReliableCodeLength()) {
      StringBuilder sb = this.getStringBuilder();
      Pattern p = Pattern.compile(ANSI_CODE_REGEX);
      Matcher m = p.matcher(sb);
      this.setCodeLength(0);
      while (m.find())
        this.addCodeLength(m.group().length());
      this.setReliableCodeLength(true);
    }
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Accessor                                                                                                       //
  //===----------------------------------------------------------------------------------------------------------===//
  private StringBuilder getStringBuilder() {
    return stringBuilder;
  }
  
  private void setStringBuilder(StringBuilder stringBuilder) {
    this.stringBuilder = stringBuilder;
  }
  
  private int getCodeLength() {
    return codeLength;
  }
  
  private void setCodeLength(int codeLength) {
    this.codeLength = codeLength;
  }
  
  private boolean isReliableCodeLength() {
    return reliableCodeLength;
  }
  
  private void setReliableCodeLength(boolean reliableCodeLength) {
    this.reliableCodeLength = reliableCodeLength;
  }
  
  //===----------------------------------------------------------------------------------------------------------===//
  // Innerclass                                                                                                     //
  //===----------------------------------------------------------------------------------------------------------===//
  public enum Without {
    EscapeCode
  }
}
