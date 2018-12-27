package com.mickey305.foundation.tools.maintenance.v3.vendor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

// LICENSE HEADER
/*
 * Copyright (c) 2018 Cryptomedia Co.,Ltd.
 * Released under the MIT license
 * https://opensource.org/licenses/mit-license.php
 */

/**
 * Javaソースからコメントを削除した文字列を取得する。
 * @author kmisaki (update from 2018-12-27)
 * @see <a href="https://www.gwtcenter.com/">original web site</a>
 *      <a href="https://github.com/ysugimura/depDetect">github.com</a>
 */
public class CommentRemover {
  
  /** Javaソースのエンコーディング */
  public static final Charset CHARSET = StandardCharsets.UTF_8;
  
  /**
   * 指定Javaソース・ファイルを読み込み、コメントを除去した後のテキストを返す
   * ブロックコメントの場合、改行が含まれない場合は一つの半角スペースに、含まれる場合には一つの改行に置換する。
   * @param path 対象とするファイル
   * @return コメント除去した後のファイル文字列。改行コードは"\n"
   * @throws IOException
   */
  public static String remove(Path path) throws IOException {
    String text = Files.readAllLines(path, CHARSET).stream().collect(Collectors.joining("\n"));
    
    StringBuilder stripped = new StringBuilder();
    CharReader r = new CharReader(text);
    
    while (!r.endOfFile()) {
      char c = r.read();
      switch (c) {
        case '"': // 文字列定数開始
        case '\'': // 文字定数開始
          stripped.append(skipStringOrChar(c, r)); break;
        case '/': // コメント開始の可能性
          switch (r.read()) {
            case '*': // ブロックコメント開始
              stripped.append(skipBlockComment(r)); break;
            case '/': // 行コメント開始
              stripped.append(skipLineComment(r)); break;
            default: // コメントではない
              stripped.append(c); break;
          }
          break;
        default: // 注目しない文字
          stripped.append(c);
          continue;
      }
    }
    return stripped.toString();
  }
  
  /**
   * Javaのブロックコメントをスキップする。
   * コメント途中に改行の無い場合には空白一文字に置き換える。
   * 改行のある場合には改行一文字に置き換える。
   * @param r
   * @return
   */
  static String skipBlockComment(CharReader r) throws IOException {
    boolean hasNewLine = false;
    while (true) {
      char c = r.read();
      if (c == '*') {
        c = r.read();
        if (c == '/') break;
        r.unread();
        continue;
      }
      if (c == '\n') {
        hasNewLine = true;
        continue;
      }
    }
    return hasNewLine? "\n":" ";
  }
  
  /**
   * Javaのラインコメントをスキップする
   * @param r
   * @return
   */
  static String skipLineComment(CharReader r) throws IOException {
    while (!r.endOfFile()) {
      if (r.read() == '\n') return "\n";
    }
    return "";
  }
  
  /**
   * 文字列あるいは文字定数をスキップする
   *
   * @param start　開始文字、'"'あるいは'\''
   * @param r リーダ
   * @return 文字列あるいは文字定数
   * @throws IOException
   */
  static String skipStringOrChar(char start, CharReader r) throws IOException {
    StringBuilder output = new StringBuilder();
    output.append(start);
    while (true) {
      if (r.endOfFile()) break;
      char c = r.read();
      if (c == start) {
        output.append(c);
        break;
      }
      output.append(c);
      if (c == '\\') output.append(r.read());
    }
    return output.toString();
  }
  
  /**
   * 文字リーダ
   * {@link #endOfFile()}でファイルの終わりを検出する。
   * {@link #read()}で一文字を返すが、ファイル終端では{@link IOException}が発生する。
   */
  static class CharReader {
    BufferedReader r;
    CharReader(String text) {
      r = new BufferedReader(new StringReader(text));
    }
    boolean endOfFile() throws IOException {
      r.mark(1);
      int c = r.read();
      if (c < 0) return true;
      r.reset();
      return false;
    }
    char read() throws IOException {
      r.mark(1);
      int c = r.read();
      if (c < 0) throw new IOException("Truncated source file");
      return (char)c;
    }
    void unread() throws IOException {
      r.reset();
    }
    String getRest() throws IOException {
      StringBuilder s = new StringBuilder();
      while (!endOfFile()) s.append(read());
      return s.toString();
    }
  }
}
