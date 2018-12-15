package com.mickey305.foundation.v3.util;

import javax.annotation.Nonnull;
import java.util.regex.Pattern;

public class Regexp {
  
  /*
   * standard regex solution
   *
   *
   */
  
  /**
   * 半角数字
   */
  public static final String HALF_NUMBER = "[0-9]+";
  
  /**
   * 半角数字（符号、小数）
   */
  public static final String HALF_REAL = "[-]?[0-9]+(\\.[0-9]+)?";
  
  /**
   * 全角数字
   */
  public static final String FULL_NUMBER = "[０-９]+";
  
  /**
   * 全角数字（符号、小数）
   */
  public static final String FULL_REAL = "[ー]?[０-９]+(\\．[０-９]+)?";
  
  /**
   * 数字
   */
  public static final String NUMBER = "[0-9０-９]+";
  
  /**
   * 半角英字（小文字）
   */
  public static final String HALF_ALPHABET_LOWERCASE = "[a-z]+";
  
  /**
   * 半角英字（大文字）
   */
  public static final String HALF_ALPHABET_UPPERCASE = "[A-Z]+";
  
  /**
   * 半角英字
   */
  public static final String HALF_ALPHABET = "[a-zA-Z]+";
  
  /**
   * 半角英数字（小文字）
   */
  public static final String HALF_ALPHABET_LOWERCASE_NUMBER = "[a-z0-9]+";
  
  /**
   * 半角英数字（大文字）
   */
  public static final String HALF_ALPHABET_UPPERCASE_NUMBER = "[A-Z0-9]+";
  
  /**
   * 半角英数字
   */
  public static final String HALF_ALPHABET_NUMBER = "[a-zA-Z0-9]+";
  
  /**
   * 全角平仮名
   * unicode: U+3041 - U+3093, U+30FC
   */
  public static final String FULL_HIRA_KANA = "[ぁ-んー]+";
  
  /**
   * 全角片仮名
   * unicode: U+30A1 - U+30F6, U+30FC
   */
  public static final String FULL_KATA_KANA = "[ァ-ヶー]+";
  
  /**
   * 全角仮名
   */
  public static final String FULL_KANA = "[ぁ-んァ-ヶー]+";
  
  /**
   * 半角仮名
   * unicode: U+FF66 - U+FF9F
   */
  public static final String HALF_KANA = "[ｦ-ﾟ]+";
  
  /**
   * 漢字
   * unicode: U+4E00 - U+9FA0
   */
  public static final String KANJI = "[一-龠]+";
  
  /**
   * 漢数字
   */
  public static final String KANJI_NUBER = "[一二三四五六七八九十壱弐参拾百千万萬億兆〇]+";
  
  /**
   * 全角平仮名・漢字
   */
  public static final String FULL_HIRA_KANA_KANJI = "[ぁ-ん一-龠]+";
  
  /**
   * 全角仮名・漢字
   */
  public static final String FULL_KANA_KANJI = "[ぁ-んァ-ヶー一-龠]+";
  
  /**
   * 半角スペース
   */
  public static final String HALF_SPACE = " +";
  
  /**
   * 全角スペース
   */
  public static final String FULL_SPACE = "　+";
  
  /**
   * スペース
   */
  public static final String SPACE = "( |　)+";
  
  /**
   * 空白文字
   * （半角スペース、タブ文字、改行、改ページ）
   */
  public static final String WHITESPACE = "\\s+";
  
  /**
   * 空白文字・全角スペース
   */
  public static final String ALL_WHITESPACE = "(\\s|　)+";
  
  /**
   * ピリオド文字
   */
  public static final String PERIOD = "\\.+";
  
  
  /*
   * extension regex solution
   *
   *
   */
  
  /**
   * メースアドレス
   */
  public static final String EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
  
  /**
   * ドメイン名
   */
  public static final String DOMAIN = "[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}";
  
  /**
   * URL
   */
  public static final String URL = "(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";
  
  /**
   * 電話暗号（固定回線）
   */
  public static final String PHONE = "0\\d-\\d{4}-\\d{4}";
  
  /**
   * 携帯電話番号
   */
  // todo: 060-xxxx-xxxx coming soon
  public static final String CELLPHONE = "(070|080|090)-\\d{4}-\\d{4}";
  
  /**
   * IP電話番号
   */
  public static final String IP_PHONE = "050-\\d{4}-\\d{4}";
  
  /**
   * 電話番号（フリーダイヤル）
   */
  public static final String FREE_DIAL = "0120-\\d{3}-\\d{3}";
  
  /**
   * 郵便番号
   */
  public static final String ZIPCODE = "\\d{3}-\\d{4}";
  
  /**
   * パスワード推奨文字列
   */
  public static final String DEFAULT_PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}";
  
  /**
   * 空白行
   */
  public static final String BLANK_LINE = "\\n\\s*\\r";
  
  /**
   * 日本語抽出のバイナリ正規表現パターン
   */
  public static final String JP_SEED_BINARY_PATTERN = "[^\\x01-\\x7E]+";
  
  private Regexp() {
  }
  
  /**
   * @param regexp
   * @param compileType
   * @return
   */
  public static Pattern pattern(@Nonnull String regexp, @Nonnull CompileType compileType) {
    regexp = wrap(regexp, compileType);
    return Pattern.compile(regexp);
  }
  
  /**
   * @param regexp
   * @param compileType
   * @return
   */
  public static String wrap(@Nonnull String regexp, @Nonnull CompileType compileType) {
    if (compileType == CompileType.Exact) regexp = "^" + regexp + "$";
    if (compileType == CompileType.Partial) regexp = "^.*" + regexp + ".*$";
    if (compileType == CompileType.Plain) regexp = "" + regexp + "";
    return regexp;
  }
  
  /**
   * コンパイル対象の正規表現のパターン
   */
  public enum CompileType {
    /**
     * 完全一致
     */
    Exact,
    
    /**
     * 部分一致
     */
    Partial,
    
    /**
     * オリジナル
     */
    Plain
  }
}
