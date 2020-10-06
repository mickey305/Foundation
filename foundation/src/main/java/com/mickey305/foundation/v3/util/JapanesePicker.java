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

import java.util.regex.Matcher;

import static com.mickey305.foundation.v3.util.Regexp.JP_PURE_SEED_PATTERN;

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
   *
   * @param stmt 日本語抽出対象の文字列
   * @return 日本語抽出マッチャ
   */
  public Matcher build(String stmt) {
    Matcher matcher;
    if (extensionRhsPattern == null || extensionLhsPattern == null || extensionInlinePattern == null) {
      // 拡張正規表現が設定されていない場合
      matcher = Regexp.pattern(
          JP_PURE_SEED_PATTERN,
          Regexp.CompileType.Plain).matcher(stmt);
      
    } else {
      // 拡張正規表現が設定されている場合
      matcher = Regexp.pattern(
          extensionLhsPattern
              + "(" + JP_PURE_SEED_PATTERN + "(" + extensionInlinePattern + JP_PURE_SEED_PATTERN + ")*)+"
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
