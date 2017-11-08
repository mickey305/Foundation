package com.mickey305.foundation.v3.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListUtil {

    private ListUtil() {}

    /**
     * 要素をダウンキャストする
     * @param subClass キャスト先のクラス
     * @param castTarget キャスト対象のリスト
     * @param <SUPER> キャスト対象の総称型
     * @param <SUB> キャスト先の総称型
     * @return キャスト後のリスト
     */
    public static <SUPER extends DownCastable, SUB extends SUPER>
    List<SUB> downCastElementTo(Class<SUB> subClass, List<SUPER> castTarget) {
        List<SUB> castedLst =  new ArrayList<>(castTarget.size());
        CollectionUtil.downCastElementTo(subClass, castTarget, castedLst);
        return castedLst;
    }

    /**
     * コレクション型からリスト型に変換する
     * @param elements 変換対象のコレクション
     * @param <E> 要素の総称型
     * @return 変換後のリスト
     */
    public static <E> List<E> downCastFrom(Collection<E> elements) {
        return new ArrayList<>(elements);
    }

    /**
     * リスト型から配列に変換する
     * @param elements 変換対象のリスト
     * @param dummy 要素の型パラメータ（未入力可）
     * @param <E> 要素の総称型
     * @return 変換後の配列
     */
    @SafeVarargs
    public static <E> E[] toArray(List<E> elements, E... dummy) {
        return CollectionUtil.toArray(elements, dummy);
    }

    /**
     * リスト型から配列に変換する
     * <p>要素クラスがnullの場合、{@link IllegalArgumentException}が発生する</p>
     * @param elements 変換対象のリスト
     * @param elementType 要素のクラス
     * @param <E> 要素の総称型
     * @return 変換後の配列
     * @throws IllegalArgumentException 引数例外
     */
    public static <E> E[] toArray(List<E> elements, Class<E> elementType) {
        return CollectionUtil.toArray(elements, elementType);
    }

    /**
     * 配列からリスト型に変換する
     * @param elements 変換対象の配列
     * @param <E> 要素の総称型
     * @return 変換後のリスト
     */
    public static <E> List<E> fromArray(E[] elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }
}
