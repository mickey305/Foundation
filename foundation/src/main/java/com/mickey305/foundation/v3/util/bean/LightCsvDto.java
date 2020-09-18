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

package com.mickey305.foundation.v3.util.bean;

import java.util.List;

/**
 * CSV変換前後の情報を格納するDTO
 */
public class LightCsvDto {
  public static final Character DLM = ',';
  public static final Character ESCAPE_CHAR = '"';
  
  private List<String> elements;
  private String line;
  
  public List<String> getElements() {
    return elements;
  }
  
  public void setElements(List<String> elements) {
    this.elements = elements;
  }
  
  public String getLine() {
    return line;
  }
  
  public void setLine(String line) {
    this.line = line;
  }
}
