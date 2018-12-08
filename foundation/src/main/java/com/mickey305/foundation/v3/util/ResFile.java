package com.mickey305.foundation.v3.util;

import java.io.File;
import java.net.URL;
import java.util.Objects;

import static com.mickey305.foundation.v3.EnvConfigConst.IS_DEBUG_MODE;

public class ResFile {
  
  private ResFile() {
  }
  
  public static File get(String path) {
    final URL url = ResFile.class.getClassLoader().getResource(path);
    
    Objects.requireNonNull(url);
    
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
