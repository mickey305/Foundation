package com.mickey305.foundation.v3.util;

import javax.annotation.Nonnull;
import java.util.regex.Pattern;

import static com.mickey305.foundation.EnvConfigConst.IS_DEBUG_MODE;

public class Regexp {
  
  /*
   *-------------------------------------------------------------------------------------------------------------
   * standard regex solution definition area
   *
   *
   *-------------------------------------------------------------------------------------------------------------
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
  public static final String KANJI_NUMBER = "[一二三四五六七八九十壱弐参拾百千万萬億兆〇]+";
  
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
   *-------------------------------------------------------------------------------------------------------------
   * extension regex solution definition area
   *
   *
   *-------------------------------------------------------------------------------------------------------------
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
   * 日本語抽出の正規表現パターン
   */
  public static final String JP_PURE_SEED_PATTERN = "[ｦ-ﾟぁ-んァ-ヶー一-龠]+";
  public static final String JP_BINARY_SEED_PATTERN = "[^\\x01-\\x7E]+";
  
  public static final class JavaGrammar {
    
    /**
     * package文パターン
     */
    public static final String PACKAGE = "package\\s+([^;]+);";
  
    /**
     * import文パターン
     */
    public static final String IMPORT = "import\\s+([^;]+);";
  
    /**
     * import static文パターン
     */
    public static final String IMPORT_STATIC = "import\\s+static\\s+([^;]+);";
    
  }
  
  private Regexp() {
  }
  
  /**
   * 入力された正規表現の情報をもとに、コンパイルタスクを実行する
   *
   * @param regexp      正規表現
   * @param compileType 正規表現のパターン
   * @return パターンオブジェクト
   */
  public static Pattern pattern(@Nonnull String regexp, @Nonnull CompileType compileType) {
    // 初期値のマスク情報:0で正規表現をビルドする
    /**
     * mask info
     * {@link Pattern#CASE_INSENSITIVE}, {@link Pattern#MULTILINE}, {@link Pattern#DOTALL},
     * {@link Pattern#UNICODE_CASE}, {@link Pattern#CANON_EQ}, {@link Pattern#UNIX_LINES},
     * {@link Pattern#LITERAL}, {@link Pattern#UNICODE_CHARACTER_CLASS}
     * and {@link Pattern#COMMENTS}
     */
    final int flags = 0;
    
    // edit regexp
    regexp = wrap(regexp, compileType);
    
    if (IS_DEBUG_MODE) Log.d("compile target data pattern ‴" + regexp + "‴");
    
    // compile
    return Pattern.compile(regexp, flags);
  }
  
  /**
   * 入力された正規表現の情報をもとに、正規表現パターンを適用した正規表現文字列を返却する
   *
   * @param regexp      正規表現
   * @param compileType 正規表現のパターン
   * @return 正規表現（パターン適用済）
   */
  public static String wrap(@Nonnull String regexp, @Nonnull CompileType compileType) {
    if (compileType == CompileType.Exact) regexp = "^" + regexp + "$";
    if (compileType == CompileType.Partial) regexp = "^.*" + regexp + ".*$";
    if (compileType == CompileType.Plain) regexp = "" + regexp + "";
    return regexp;
  }
  
  /**
   * 入力された正規表現の情報をもとに、コンパイルタスクを実行する
   * <p>
   * このメソッドで適用される正規表現パターンは、{@link CompileType#Exact}です</p>
   *
   * @param regexp 正規表現
   * @return パターンオブジェクト
   */
  public static Pattern patternDefault(@Nonnull String regexp) {
    return pattern(regexp, CompileType.Exact);
  }
  
  /**
   * 入力された正規表現の情報をもとに、正規表現パターンを適用した正規表現文字列を返却する
   * <p>
   * このメソッドで適用される正規表現パターンは、{@link CompileType#Exact}です</p>
   *
   * @param regexp 正規表現
   * @return 正規表現（パターン適用済）
   */
  public static String wrapDefault(@Nonnull String regexp) {
    return wrap(regexp, CompileType.Exact);
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
