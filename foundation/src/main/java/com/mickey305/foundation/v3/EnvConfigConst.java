package com.mickey305.foundation.v3;

public final class EnvConfigConst {
  /*
   * Build type mask
   */
  private static final boolean IS_PRODUCTION_MODE = false;
  private static final boolean IS_DEVELOP_MODE    = true;
  private static final boolean IS_TEST_MODE       = false;
  
  /**
   * デバッグモード判別フラグ
   */
  public static final boolean IS_DEBUG_MODE = (!IS_PRODUCTION_MODE) && (IS_DEVELOP_MODE || IS_TEST_MODE);
}
