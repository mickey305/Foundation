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
   * @param stmt
   * @return
   */
  public Matcher build(String stmt) {
    Matcher matcher;
    if (extensionRhsPattern == null || extensionLhsPattern == null || extensionInlinePattern == null) {
      matcher = Regexp.pattern(
          JP_SEED_BINARY_PATTERN,
          Regexp.CompileType.Plain).matcher(stmt);
      
    } else {
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
