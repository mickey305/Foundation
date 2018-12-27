package com.mickey305.foundation.tools.maintenance.v3.vendor.bean;

// LICENSE HEADER
/*
 * Copyright (c) 2018 Cryptomedia Co.,Ltd.
 * Released under the MIT license
 * https://opensource.org/licenses/mit-license.php
 */

/**
 * @author kmisaki (update from 2018-12-27)
 * @see <a href="https://www.gwtcenter.com/">original web site</a>
 *      <a href="https://github.com/ysugimura/depDetect">github.com</a>
 */
public class Import  {
  /**
   * フルのimportパス
   * com.cm55.*, com.cm55.SampleClass.*等
   */
  public final String fullPath;
  
  /** static参照。import static文の場合 */
  public final boolean statical;
  
  /** import名称とstaticフラグを指定する */
  public Import(String fullPath, boolean statical) {
    this.fullPath = fullPath;
    this.statical = statical;
  }
}
