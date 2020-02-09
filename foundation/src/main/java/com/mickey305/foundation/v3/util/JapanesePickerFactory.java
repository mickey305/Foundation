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

public class JapanesePickerFactory {
  private static final String EXT_DEFAULT_LHS_PATTERN = "[\"\\.\\?\\$\\+\\\\ </>!&,a-zA-Z0-9℀-⅏⅐-\u218B←-⇿∀-⋿①-⓿〇〆ａ-ｚＡ-Ｚ０-９　〒＄￥％〝【︗『《≪〈〔［（「｛＜]*";
  private static final String EXT_DEFAULT_RHS_PATTERN = "([\"\\.\\?\\$\\\\</>!a-zA-Z0-9℀-⅏⅐-\u218B←-⇿∀-⋿①-⓿〇〆ａ-ｚＡ-Ｚ０-９＄￥％〟〞】︘』》≫〉〕］）」｝＞…︙]*[。︒！？]?)";
  private static final String EXT_DEFAULT_INLINE_PATTERN = "[\"\\-\\[\\{\\(\\]\\}\\)\\.\\?\\$\\+\t\\\\ </>!&;:,a-zA-Z0-9℀-⅏⅐-\u218B←-⇿∀-⋿①-⓿〇〆ａ-ｚＡ-Ｚ０-９　〝〟〞〱〲〳〴〵々〒＠＃＄％＾＆＊【︗】︘『』《》≪≫〈〉〔〕［］（）－＿＋＝〜～‘「」￥｛｝｜；’：”、︑・＜＞…︙]*";
  
  private static final String EXT_XML_LHS_PATTERN = "[\\.\\?\\$\\+\\\\ !&,a-zA-Z0-9℀-⅏⅐-\u218B←-⇿∀-⋿①-⓿〇〆ａ-ｚＡ-Ｚ０-９　〒＄￥％〝【︗『《≪〈〔［（「｛＜]*";
  private static final String EXT_XML_RHS_PATTERN = "([\\.\\?\\$\\\\/!a-zA-Z0-9℀-⅏⅐-\u218B←-⇿∀-⋿①-⓿〇〆ａ-ｚＡ-Ｚ０-９＄￥％〟〞】︘』》≫〉〕］）」｝＞…︙]*[。︒！？]?)";
  private static final String EXT_XML_INLINE_PATTERN = "[\"\\-\\[\\{\\(\\]\\}\\)\\.\\?\\$\\+\t\\\\ /!&;:,a-zA-Z0-9℀-⅏⅐-\u218B←-⇿∀-⋿①-⓿〇〆ａ-ｚＡ-Ｚ０-９　〝〟〞〱〲〳〴〵々〒＠＃＄％＾＆＊【︗】︘『』《》≪≫〈〉〔〕［］（）－＿＋＝〜～‘「」￥｛｝｜；’：”、︑・＜＞…︙]*";
  
  private static final String EXT_JSON_LHS_PATTERN = "[\\.\\?\\$\\+\\\\ </>!&a-zA-Z0-9℀-⅏⅐-\u218B←-⇿∀-⋿①-⓿〇〆ａ-ｚＡ-Ｚ０-９　〒＄￥％〝【︗『《≪〈〔［（「｛＜]*";
  private static final String EXT_JSON_RHS_PATTERN = "([\\.\\?\\$\\\\</>!a-zA-Z0-9℀-⅏⅐-\u218B←-⇿∀-⋿①-⓿〇〆ａ-ｚＡ-Ｚ０-９＄￥％〟〞】︘』》≫〉〕］）」｝＞…︙]*[。︒！？]?)";
  private static final String EXT_JSON_INLINE_PATTERN = "[\\-\\(\\)\\.\\?\\$\\+\t\\\\ </>!&;a-zA-Z0-9℀-⅏⅐-\u218B←-⇿∀-⋿①-⓿〇〆ａ-ｚＡ-Ｚ０-９　〝〟〞〱〲〳〴〵々〒＠＃＄％＾＆＊【︗】︘『』《》≪≫〈〉〔〕［］（）－＿＋＝〜～‘「」￥｛｝｜；’：”、︑・＜＞…︙]*";
  
  /**
   * デフォルトの日本語抽出器を生成する
   *
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
   *
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
   * JSON用の日本語抽出器を生成する
   *
   * @return 日本語抽出器
   */
  public static JapanesePicker createDefaultJsonStyle() {
    final JapanesePicker picker = new JapanesePicker();
    picker.setExtensionLhsPattern(EXT_JSON_LHS_PATTERN);
    picker.setExtensionRhsPattern(EXT_JSON_RHS_PATTERN);
    picker.setExtensionInlinePattern(EXT_JSON_INLINE_PATTERN);
    
    return picker;
  }
  
  /**
   * オリジナルの日本語抽出器を生成する
   *
   * @return 日本語抽出器
   */
  public static JapanesePicker createPlain() {
    return new JapanesePicker();
  }
}
