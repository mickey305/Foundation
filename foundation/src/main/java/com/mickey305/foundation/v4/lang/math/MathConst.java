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

package com.mickey305.foundation.v4.lang.math;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public final class MathConst {
  /*
   * AbstractNumberTable logging flag
   */
  private static final boolean ABS_NUM_TBL_CAPTURE = false;
  
  private static final boolean CAPTURE_INI = false;
  private static final boolean CAPTURE_TABLE_IO = true;
  
  /*
   * ログキャプチャ判定フラグ
   */
  static final boolean ABS_NUM_TBL_CAPTURE_INI = IS_DEBUG_MODE && ABS_NUM_TBL_CAPTURE && CAPTURE_INI;
  static final boolean ABS_NUM_TBL_CAPTURE_TABLE_IO = IS_DEBUG_MODE && ABS_NUM_TBL_CAPTURE && CAPTURE_TABLE_IO;
  
  private MathConst() {
    // nop
  }
}
