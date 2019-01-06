package com.mickey305.foundation.tools.maintenance.vendor.v3;

import com.mickey305.foundation.tools.maintenance.vendor.v3.bean.Import;
import com.mickey305.foundation.tools.maintenance.vendor.v3.bean.Imports;
import com.mickey305.foundation.v3.util.Regexp;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// LICENSE HEADER
/*
 * Copyright (c) 2018 Cryptomedia Co.,Ltd.
 * Released under the MIT license
 * https://opensource.org/licenses/mit-license.php
 */

/**
 * Javaソースからimport文情報を取得する
 * @author ysugimura
 * @author kmisaki (update from 2018-12-27)
 * @see <a href="https://www.gwtcenter.com/">original web site</a>
 *      <a href="https://github.com/ysugimura/depDetect">github.com</a>
 */
public class ImportExtractor {
  
  /** package文パターン */
  static final Pattern PACKAGE = Regexp.pattern(Regexp.JavaGrammar.PACKAGE, Regexp.CompileType.Exact);
  
  /** import文パターン */
  static final Pattern IMPORT = Regexp.pattern(Regexp.JavaGrammar.IMPORT, Regexp.CompileType.Exact);
  
  /** import static文パターン */
  static final Pattern IMPORT_STATIC = Regexp.pattern(Regexp.JavaGrammar.IMPORT_STATIC, Regexp.CompileType.Exact);
  
  /** 指定されたJavaソース・ファイルのimport文を全て取得し、{@link Imports}オブジェクトを返す */
  public static Imports extract(Path path) throws IOException {
    List<String> lines =
        Arrays.stream(CommentRemover.remove(path).split("\n"))
            .map(String::trim)
            .filter(line->line.length() > 0)
            .collect(Collectors.toList());
    return extract(lines);
  }
  
  /**
   * Javaソース・ファイルの行リストからimport文を全て取得し、{@link Imports}オブジェクトを返す。
   * ただし、linesは以下の条件を満たすこと。
   * <ul>
   * <li>コメントはすべて除去されている。
   * <li>空行はすべて除去されている
   * <li>行の前後の空白はすべて除去されている。
   * <ul>
   * <p>
   * したがって、一行名は必ずpackage文（オプション）となり、その次の行はimport文が連続することになる。
   * </p>
   * @param lines 入力Javaソース行リスト
   * @return {@link Imports}
   */
  static Imports extract(List<String>lines) {
    
    List<Import>importList = new ArrayList<>();
    
    // package文もしくは最初のimport文を取得
    if (lines.size() > 0) {
      String line = lines.remove(0);
      String pkgName = getPackage(line);
      if (pkgName == null) {
        Import imp = getImport(line);
        if (imp == null) return new Imports();
        importList.add(imp);
      }
    }
    
    // import文が無くなるまで繰り返す
    while (lines.size() > 0) {
      Import imp = getImport(lines.remove(0));
      if (imp == null) break;
      importList.add(imp);
    }
    
    return new Imports(importList.toArray(new Import[0]));
  }
  
  /** package文のパッケージ名称を取得する。package文でなければnullを返す */
  static String getPackage(String line) {
    Matcher m = PACKAGE.matcher(line);
    if (!m.matches()) return null;
    return m.group(1).replaceAll("\\s",  "");
  }
  
  /** import/import static文の依存パッケージを{@link Import}の形で返す */
  @Nullable
  static Import getImport(String line) {
    Matcher m;
    m = IMPORT_STATIC.matcher(line);
    if (m.matches()) return new Import(m.group(1).replaceAll("\\s",  ""), true);
    m = IMPORT.matcher(line);
    if (m.matches()) return new Import(m.group(1).replaceAll("\\s",  ""), false);
    return null;
  }
}

