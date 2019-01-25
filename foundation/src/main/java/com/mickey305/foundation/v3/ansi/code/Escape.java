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

package com.mickey305.foundation.v3.ansi.code;

public enum Escape {
  Reset("\u001b[0m"),
  // font style and attribute
  IntensityBold("\u001b[1m"),
  IntensityFaint("\u001b[2m"),
  Italic("\u001b[3m"),
  Underline("\u001b[4m"),
  BlinkSlow("\u001b[5m"),
  BlinkFast("\u001b[6m"),
  NegativeOn("\u001b[7m"),
  ConcealOn("\u001b[8m"),
  StrikeThroughOn("\u001b[9m"),
  UnderlineDouble("\u001b[21m"),
  IntensityBoldOff("\u001b[22m"),
  ItalicOff("\u001b[23m"),
  UnderlineOff("\u001b[24m"),
  BlinkOff("\u001b[25m"),
  NegativeOff("\u001b[27m"),
  ConcealOff("\u001b[28m"),
  StrikeThroughOff("\u001b[29m"),
  // font color
  Black("\u001b[30m"),
  Red("\u001b[31m"),
  Green("\u001b[32m"),
  Yellow("\u001b[33m"),
  Blue("\u001b[34m"),
  Magenta("\u001b[35m"),
  Cyan("\u001b[36m"),
  White("\u001b[37m"),
  Default("\u001b[39m"),
  // background color
  BkgBlack("\u001b[40m"),
  BkgRed("\u001b[41m"),
  BkgGreen("\u001b[42m"),
  BkgYellow("\u001b[43m"),
  BkgBlue("\u001b[44m"),
  BkgMagenta("\u001b[45m"),
  BkgCyan("\u001b[46m"),
  BkgWhite("\u001b[47m"),
  BkgDefault("\u001b[49m"),
  // bright font color
  BrightBlack("\u001b[90m"),
  BrightRed("\u001b[91m"),
  BrightGreen("\u001b[92m"),
  BrightYellow("\u001b[93m"),
  BrightBlue("\u001b[94m"),
  BrightMagenta("\u001b[95m"),
  BrightCyan("\u001b[96m"),
  BrightWhite("\u001b[97m"),
  BrightDefault("\u001b[99m"),
  // bright background color
  BrightBkgBlack("\u001b[100m"),
  BrightBkgRed("\u001b[101m"),
  BrightBkgGreen("\u001b[102m"),
  BrightBkgYellow("\u001b[103m"),
  BrightBkgBlue("\u001b[104m"),
  BrightBkgMagenta("\u001b[105m"),
  BrightBkgCyan("\u001b[106m"),
  BrightBkgWhite("\u001b[107m"),
  BrightBkgDefault("\u001b[109m");
  
  private String code;
  
  Escape(String code) {
    this.setCode(code);
  }
  
  String code() {
    return this.getCode();
  }
  
  private String getCode() {
    return code;
  }
  
  private void setCode(String code) {
    this.code = code;
  }
}
