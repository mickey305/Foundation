package com.mickey305.foundation.v3.util;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

public class FileUtil {
  
  /**
   *
   * @param absolutePath
   * @return
   */
  public static List<File> findAllFile(@Nonnull String absolutePath) {
    final List<File> files = new ArrayList<>();
    final Deque<File> pool = new ArrayDeque<>();
    
    pool.offerFirst(new File(absolutePath));
    
    // 検索キューにデータが存在する間、処理を繰り返す
    while (!pool.isEmpty()) {
      File item = pool.pollFirst();
      Objects.requireNonNull(item);
      
      // ファイルの場合、返却キューに追加
      if (item.isFile()) {
        files.add(item);
      }
      
      // ディレクトリの場合、ファイル一覧を検索キューに追加
      if (item.isDirectory()) {
        final File[] items = item.listFiles();
        Objects.requireNonNull(items);
        
        for (File child : items) {
          pool.offerFirst(child);
        }
      }
    }
    
    return files;
  }
}
