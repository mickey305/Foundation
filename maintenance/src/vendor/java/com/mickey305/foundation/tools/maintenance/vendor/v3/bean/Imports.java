package com.mickey305.foundation.tools.maintenance.vendor.v3.bean;

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
public class Imports {
  
  /** 全importの配列 */
  public final Import[] imports;
  
  /** import無し */
  public Imports() {
    imports = new Import[0];
  }
  
  /** {@link Import}配列を与える */
  public Imports(Import[]imports) {
    this.imports = imports;
  }
}

