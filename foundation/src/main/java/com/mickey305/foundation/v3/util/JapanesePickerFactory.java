package com.mickey305.foundation.v3.util;

public class JapanesePickerFactory {
  private static final String EXT_DEFAULT_LHS_PATTERN = "[\\?\\$\\+\\\\ 　</>!&,a-zA-Z0-9]*";
  private static final String EXT_DEFAULT_RHS_PATTERN = "[\\?\\$\\\\</>!a-zA-Z0-9]*";
  private static final String EXT_DEFAULT_INLINE_PATTERN = "[\\[\\{\\(\\]\\}\\)\\.\\?\\$\\+\t\\\\ 　</>!&,a-zA-Z0-9]*";
  
  private static final String EXT_XML_LHS_PATTERN = "[\\?\\$\\+\\\\ 　!&,a-zA-Z0-9]*";
  private static final String EXT_XML_RHS_PATTERN = "[\\?\\$\\\\!a-zA-Z0-9]*";
  private static final String EXT_XML_INLINE_PATTERN = "[\\[\\{\\(\\]\\}\\)\\.\\?\\$\\+\t\\\\ 　!&,a-zA-Z0-9]*";
  
  /**
   * デフォルトの日本語抽出器を生成する
   * @return 日本語抽出器
   */
  public static JapanesePicker createDefault() {
    final JapanesePicker picker = new JapanesePicker();
    picker.setExtensionLhsPattern(EXT_DEFAULT_LHS_PATTERN);
    picker.setExtensionRhsPattern(EXT_DEFAULT_RHS_PATTERN);
    picker.setExtensionInlinePattern(EXT_DEFAULT_INLINE_PATTERN);
    
    return picker;
  }
  
  /**
   * XML用の日本語抽出器を生成する
   * @return 日本語抽出器
   */
  public static JapanesePicker createDefaultXmlStyle() {
    final JapanesePicker picker = new JapanesePicker();
    picker.setExtensionLhsPattern(EXT_XML_LHS_PATTERN);
    picker.setExtensionRhsPattern(EXT_XML_RHS_PATTERN);
    picker.setExtensionInlinePattern(EXT_XML_INLINE_PATTERN);
    
    return picker;
  }
  
  /**
   * オリジナルの日本語抽出器を生成する
   * @return 日本語抽出器
   */
  public static JapanesePicker createPlain() {
    return new JapanesePicker();
  }
}
