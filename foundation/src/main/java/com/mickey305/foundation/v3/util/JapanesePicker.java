package com.mickey305.foundation.v3.util;

import java.util.regex.Matcher;

import static com.mickey305.foundation.v3.util.Regexp.JP_SEED_BINARY_PATTERN;

public class JapanesePicker {
  private String extensionLhsPattern;
  private String extensionRhsPattern;
  private String extensionInlinePattern;
  
  protected JapanesePicker() {
    extensionInlinePattern = null;
    extensionLhsPattern = null;
    extensionRhsPattern = null;
  }
  
  /**
   * 入力された文字列の日本語抽出マッチャを生成する
   * @param stmt 日本語抽出対象の文字列
   * @return 日本語抽出マッチャ
   */
  public Matcher build(String stmt) {
    Matcher matcher;
    if (extensionRhsPattern == null || extensionLhsPattern == null || extensionInlinePattern == null) {
      // 拡張正規表現が設定されていない場合
      matcher = Regexp.pattern(
          JP_SEED_BINARY_PATTERN,
          Regexp.CompileType.Plain).matcher(stmt);
      
    } else {
      // 拡張正規表現が設定されている場合
      matcher = Regexp.pattern(
          extensionLhsPattern
              + "(" + JP_SEED_BINARY_PATTERN + "(" + extensionInlinePattern + JP_SEED_BINARY_PATTERN + ")*)+"
              + extensionRhsPattern,
          Regexp.CompileType.Plain).matcher(stmt);
      
    }
    
    return matcher;
  }
  
  public String getExtensionLhsPattern() {
    return extensionLhsPattern;
  }
  
  public void setExtensionLhsPattern(String extensionLhsPattern) {
    this.extensionLhsPattern = extensionLhsPattern;
  }
  
  public String getExtensionRhsPattern() {
    return extensionRhsPattern;
  }
  
  public void setExtensionRhsPattern(String extensionRhsPattern) {
    this.extensionRhsPattern = extensionRhsPattern;
  }
  
  public String getExtensionInlinePattern() {
    return extensionInlinePattern;
  }
  
  public void setExtensionInlinePattern(String extensionInlinePattern) {
    this.extensionInlinePattern = extensionInlinePattern;
  }
}
