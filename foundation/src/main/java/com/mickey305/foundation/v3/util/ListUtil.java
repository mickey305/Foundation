package com.mickey305.foundation.v3.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    private ListUtil() {}

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
}
