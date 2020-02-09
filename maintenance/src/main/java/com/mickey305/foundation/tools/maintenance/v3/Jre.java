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

package com.mickey305.foundation.tools.maintenance.v3;

public enum Jre {
  /**
   * Java SE7
   */
  SE7(1.7),
  
  /**
   * Java SE8
   */
  SE8(1.8),
  
  /**
   * Java SE9
   */
  SE9(9.0),
  
  /**
   * Java SE10
   */
  SE10(10.0),
  
  /**
   * Java SE11
   */
  SE11(11.0);
  
  Jre(double version) {
    this.version = version;
  }
  
  public double getVersion() {
    return version;
  }
  
  private final double version;
}
