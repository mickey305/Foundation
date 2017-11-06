package com.mickey305.foundation.v3.util;

import java.lang.reflect.Array;
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
     * @param <T> キャスト対象の総称型
     * @param <S> キャスト先の総称型
     * @return キャスト後のリスト
     */
    public static <T extends DownCastable, S extends T>
    List<S> downCastElementTo(Class<S> subClass, List<T> castTarget) {
        List<S> castedLst =  new ArrayList<>(castTarget.size());
        for (T parent : castTarget) {
            if (!parent.getClass().isAssignableFrom(subClass) || parent.getClass().equals(subClass))
                throw new ClassCastException("castTarget#" + parent.toString() + " parameter rejected");
            castedLst.add(parent.downcast(subClass));
        }
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
     * @param <E> 要素の総称型
     * @return 変換後の配列
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] toArray(List<E> elements) {
        if (elements.isEmpty())
            return null;
        E element = elements.get(0);
        E[] result = (E[]) Array.newInstance(element.getClass(), elements.size());
        return elements.toArray(result);
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
