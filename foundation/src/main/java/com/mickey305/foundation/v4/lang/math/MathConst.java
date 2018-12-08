package com.mickey305.foundation.v4.lang.math;

import static com.mickey305.foundation.v3.EnvConfigConst.IS_DEBUG_MODE;

final class MathConst {
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
}
