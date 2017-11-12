package com.mickey305.foundation.v3.lang.builder.unsafe;

public interface InjectionEventListener {
    /**
     * ダウンキャスト実行前イベントメソッド
     * @param dest キャスト先コンテキスト情報
     * @param src キャスト元コンテキスト情報
     */
    void before(EasilyAccessibleContainer dest, EasilyContainer src);

    /**
     * ダウンキャスト実行後イベントメソッド
     * @param dest キャスト先（ダウンキャスト後）コンテキスト情報
     * @param src キャスト元コンテキスト情報
     */
    void after(EasilyAccessibleContainer dest, EasilyContainer src);
}
