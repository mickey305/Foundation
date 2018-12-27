/*
 * Copyright (c) 2018. K.Misaki
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

import java.util.Objects;

public class ClassPathUtil {
  /**
   *
   */
  public static final String PATH_SEPARATOR;
  
  /**
   *
   */
  public static final String[] PATHS;
  
  static {
    PATH_SEPARATOR = System.getProperty("path.separator");
    Objects.requireNonNull(PATH_SEPARATOR);
    PATHS = System.getProperty("java.class.path").split(PATH_SEPARATOR);
    Objects.requireNonNull(PATHS);
  }
}
