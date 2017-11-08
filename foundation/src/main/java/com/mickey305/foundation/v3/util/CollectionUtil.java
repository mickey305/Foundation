package com.mickey305.foundation.v3.util;

import java.lang.reflect.Array;
import java.util.Collection;

public class CollectionUtil {

    private CollectionUtil() {}

    /**
     * 要素をダウンキャストする
     * @param subClass キャスト先のクラス
     * @param castTarget キャスト対象のコレクション
     * @param output キャスト後のコレクション
     * @param <SUPER> キャスト対象の総称型
     * @param <SUB> キャスト先の総称型
     * @return キャスト後のコレクション
     */
    public static <SUPER extends DownCastable, SUB extends SUPER>
    Collection<SUB> downCastElementTo(Class<SUB> subClass, Collection<SUPER> castTarget, Collection<SUB> output) {
        for (SUPER parent : castTarget) {
            if (!parent.getClass().isAssignableFrom(subClass) || parent.getClass().equals(subClass))
                throw new ClassCastException("castTarget#" + parent.toString() + " parameter rejected");
            output.add(parent.downcastTo(subClass));
        }
        return output;
    }

    /**
     * コレクション型から配列に変換する
     * @param elements 変換対象のコレクション
     * @param dummy 要素の型パラメータ（未入力可）
     * @param <E> 要素の総称型
     * @return 変換後の配列
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] toArray(Collection<E> elements, E... dummy) {
        Class<E> type = (Class<E>) dummy.getClass().getComponentType();
        return toArray(elements, type);
    }

    /**
     * コレクション型から配列に変換する
     * <p>要素クラスがnullの場合、{@link IllegalArgumentException}が発生する</p>
     * @param elements 変換対象のコレクション
     * @param elementType 要素のクラス
     * @param <E> 要素の総称型
     * @return 変換後の配列
     * @throws IllegalArgumentException 引数例外
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] toArray(Collection<E> elements, Class<E> elementType) {
        if (elementType == null)
            throw new IllegalArgumentException("elementType is rejected: reason NULL-VALUE");
        E[] result = (E[]) Array.newInstance(elementType, elements.size());
        return elements.toArray(result);
    }
}
