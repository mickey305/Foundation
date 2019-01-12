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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FileUtil {
  
  /**
   * @param absolutePath
   * @return
   */
  public static List<File> findAllFile(@Nonnull String absolutePath) {
    Assert.requireNonNull(absolutePath);
    
    final List<File> files = new ArrayList<>();
    final Deque<File> pool = new ArrayDeque<>();
    
    pool.offerFirst(new File(absolutePath));
    
    // 検索キューにデータが存在する間、処理を繰り返す
    while (!pool.isEmpty()) {
      File item = pool.pollFirst();
      Assert.requireNonNull(item);
      
      // ファイルの場合、返却キューに追加
      if (item.isFile()) {
        files.add(item);
      }
      
      // ディレクトリの場合、ファイル一覧を検索キューに追加
      if (item.isDirectory()) {
        final File[] items = item.listFiles();
        Assert.requireNonNull(items);
        
        for (File child : items) {
          pool.offerFirst(child);
        }
      }
    }
    
    return files;
  }
  
  /**
   * @param file
   * @return
   */
  public static String readSmallFileData(@Nonnull File file) {
    Assert.requireNonNull(file);
    
    StringBuilder sb = new StringBuilder();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String string = reader.readLine();
      while (string != null) {
        sb.append(string).append(System.lineSeparator());
        string = reader.readLine();
      }
    } catch (IOException e) {
      Log.e(e.getMessage());
      throw new RuntimeException(e);
    }
    
    return sb.toString();
  }
  
  /**
   * @param file
   * @param lines
   */
  public static void writeSmallFileData(@Nonnull File file, @Nonnull String[] lines) {
    Assert.requireNonNull(file);
    Assert.requireNonNull(lines);
    
    try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
      for (String line: lines) {
        writer.println(line);
      }
    } catch (IOException e) {
      Log.e(e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
