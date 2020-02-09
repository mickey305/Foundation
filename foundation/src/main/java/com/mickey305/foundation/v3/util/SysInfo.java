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

import java.util.Enumeration;
import java.util.Properties;

public class SysInfo {
  
  private SysInfo() {
  }
  
  public static void printEnv() {
    Properties properties = System.getProperties();
    Enumeration<Object> enumeration = properties.keys();
    for (int i = 0; i < properties.size(); i++) {
      Object obj = enumeration.nextElement();
      System.out.println(obj + "=[" + System.getProperty(obj.toString()) + "]");
    }
  }
}
