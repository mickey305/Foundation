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

package bean;

import com.mickey305.foundation.v3.validation.annotation.EscapeReject;

import javax.validation.constraints.NotNull;

public class EscapeRejectSample01 {
  @EscapeReject
  private String escapeRejectData;
  @NotNull
  private String notnullData;
  
  public EscapeRejectSample01() {
    // nop
  }
  
  public String getEscapeRejectData() {
    return escapeRejectData;
  }
  
  public void setEscapeRejectData(String escapeRejectData) {
    this.escapeRejectData = escapeRejectData;
  }
  
  public String getNotnullData() {
    return notnullData;
  }
  
  public void setNotnullData(String notnullData) {
    this.notnullData = notnullData;
  }
}
