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

import javax.annotation.Nonnull;
import java.io.File;
import java.net.URL;
import java.util.Objects;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public class ResFile {
  
  private ResFile() {
  }
  
  /**
   * @param path
   * @return
   */
  public static File get(@Nonnull String path) {
    Assert.requireNonNull(path);
    final URL url = ResFile.class.getClassLoader().getResource(path);
    
    Assert.requireNonNull(url);
    
    // URLからファイルオブジェクトを生成し、ファイルかどうかを確認後、読み取り専用にする
    final File resFile = new File(url.getPath());
    boolean isFile = resFile.isFile();
    boolean readOnly = resFile.setReadOnly();
    
    if (!isFile) {
      throw new RuntimeException("this path is not a file.");
    }
    
    if (!readOnly) {
      throw new RuntimeException("read only flag setting error occurred.");
    }
    
    if (IS_DEBUG_MODE) Log.d("data:" + resFile.toString());
    
    return resFile;
  }
}
