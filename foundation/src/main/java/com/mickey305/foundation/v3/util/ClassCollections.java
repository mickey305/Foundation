package com.mickey305.foundation.v3.util;

import java.util.ArrayList;
import java.util.Collection;

public class ClassCollections {
  
  private ClassCollections() {
  }
  
  /**
   * コレクションを生成する
   * <p>引数で与えられたクラスとそのクラスが継承するすべてのクラスのコレクションを生成する</p>
   *
   * @param targetClass クラス
   * @return クラスコレクション
   */
  public static Collection<Class<?>> untilAdam(Class<?> targetClass) {
    Collection<Class<?>> list = new ArrayList<>();
    Class<?> workClass = targetClass;
    while (workClass != null) {
      list.add(workClass);
      workClass = workClass.getSuperclass();
    }
    return list;
  }
}
