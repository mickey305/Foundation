package com.mickey305.foundation.v3.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SetUtil {

    private SetUtil() {}

    /**
     * 要素をダウンキャストする
     * @param subClass キャスト先のクラス
     * @param castTarget キャスト対象のセット
     * @param <SUPER> キャスト対象の総称型
     * @param <SUB> キャスト先の総称型
     * @return キャスト後のセット
     */
    public static <SUPER extends DownCastable, SUB extends SUPER>
    Set<SUB> downCastElementTo(Class<SUB> subClass, Set<SUPER> castTarget) {
        Set<SUB> castedSet =  new HashSet<>();
        CollectionUtil.downCastElementTo(subClass, castTarget, castedSet);
        return castedSet;
    }

    /**
     * コレクション型からセット型に変換する
     * @param elements 変換対象のコレクション
     * @param <E> 要素の総称型
     * @return 変換後のセット
     */
    public static <E> Set<E> downCastFrom(Collection<E> elements) {
        return new HashSet<>(elements);
    }

    /**
     * セット型から配列に変換する
     * @param elements 変換対象のセット
     * @param dummy 要素の型パラメータ（未入力可）
     * @param <E> 要素の総称型
     * @return 変換後の配列
     */
    @SafeVarargs
    public static <E> E[] toArray(Set<E> elements, E... dummy) {
        return CollectionUtil.toArray(elements, dummy);
    }

    /**
     * セット型から配列に変換する
     * <p>要素クラスがnullの場合、{@link IllegalArgumentException}が発生する</p>
     * @param elements 変換対象のセット
     * @param elementType 要素のクラス
     * @param <E> 要素の総称型
     * @return 変換後の配列
     * @throws IllegalArgumentException 引数例外
     */
    public static <E> E[] toArray(Set<E> elements, Class<E> elementType) {
        return CollectionUtil.toArray(elements, elementType);
    }

    /**
     * 配列からセット型に変換する
     * @param elements 変換対象の配列
     * @param <E> 要素の総称型
     * @return 変換後のセット
     */
    public static <E> Set<E> fromArray(E[] elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}
