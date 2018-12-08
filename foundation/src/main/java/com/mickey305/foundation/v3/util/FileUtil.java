package com.mickey305.foundation.v3.util;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

public class FileUtil {
  
  public static List<File> findAllFile(String absolutePath) {
    final List<File> files = new ArrayList<>();
    final Deque<File> pool = new ArrayDeque<>();
    
    pool.offerFirst(new File(absolutePath));
    
    while (!pool.isEmpty()) {
      File item = pool.pollFirst();
      Objects.requireNonNull(item);
      
      if (item.isFile()) { files.add(item); }
      
      if (item.isDirectory()) {
        for (File child : Objects.requireNonNull(item.listFiles())) {
          pool.push(child);
        }
      }
    }
    
    return files;
  }
}
