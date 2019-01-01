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

package com.mickey305.foundation;

import com.mickey305.foundation.tools.maintenance.v3.vendor.ImportExtractor;
import com.mickey305.foundation.tools.maintenance.v3.Workflow;
import com.mickey305.foundation.tools.maintenance.v3.vendor.bean.Imports;
import com.mickey305.foundation.v3.util.FileUtil;
import com.mickey305.foundation.v3.util.ListUtil;
import com.mickey305.foundation.v3.util.Log;
import com.mickey305.foundation.v4.lang.math.MathConst;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FoundationModuleTest {
  private static final SimpleDateFormat SDF_PATTERN1;
  
  static {
    SDF_PATTERN1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  }
  
  /**
   * 現在日時を取得する
   *
   * @return 現在時刻（フォーマット「yyyy-MM-dd HH:mm:ss.SSS」）
   */
  private String createDateTime() {
    Calendar cal = Calendar.getInstance();
    return SDF_PATTERN1.format(cal.getTime());
  }
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  /**
   * デバッグロジックが差し込まれているクラスを抽出し、一覧ファイルを出力する
   * @throws Exception 例外
   */
  @Test
  public void check_01_01() throws Exception {
    // search target package
    final Set<Class<?>> localClasses = Workflow.createClassesOf(
        "com.mickey305.foundation.v3.",
        "com.mickey305.foundation.v4.");
    final Collection<Class<?>> allClasses = Stream.of(localClasses)
        .flatMap(Collection::stream)
        .sorted(Comparator.comparing(Class::getName))
        .collect(Collectors.toUnmodifiableList());
  
    // build path info
    final String sp = File.separator;
    final String module = "foundation";
    final StringBuilder sb = new StringBuilder();
    final String root = System.getProperty("user.dir").contains(sp + "maintenance")
        ? System.getProperty("user.dir").replace(sp + "maintenance", "")
        : System.getProperty("user.dir");
  
    // create and initialize outfile
    File outFile = new File(root + sp + "include_debug_logic_classes.txt");
    Files.deleteIfExists(outFile.toPath());
    Files.createFile(outFile.toPath());
  
    // search target class
    List<Class<?>> debugCodeIncludeClasses = new ArrayList<>();
    allClasses.forEach(clazz -> {
      if (clazz.getName().contains("$")) {
        return;
      }
      final Path path = Paths.get(sb.append(root).append(sp)
          .append(module).append(sp)
          .append("src").append(sp)
          .append("main").append(sp)
          .append("java").append(sp)
          .append(clazz.getName().replace(".", sp)).append(".java").toString());
      sb.setLength(0);
      try {
        Imports imports = ImportExtractor.extract(path);
        final long findCount = Arrays.stream(imports.imports)
            .filter(imp -> Stream.of(
                EnvConfigConst.class.getName(),
                MathConst.class.getName()).anyMatch(imp.fullPath::startsWith))
            .count();
        if (findCount > 0) {
          debugCodeIncludeClasses.add(clazz);
        }
      } catch (IOException e) {
        Log.e(e);
        throw new RuntimeException(e);
      }
    });
    
    // write file
    List<String> resultList = new ArrayList<>();
    resultList.add("[HEADER MODULE:" + module + ", BUILD-DATETIME:" + this.createDateTime() + "]");
    resultList.addAll(debugCodeIncludeClasses.stream()
        .map(Class::getName)
        .collect(Collectors.toList()));
    FileUtil.writeSmallFileData(
        outFile,
        ListUtil.toArray(resultList));
  }
}
