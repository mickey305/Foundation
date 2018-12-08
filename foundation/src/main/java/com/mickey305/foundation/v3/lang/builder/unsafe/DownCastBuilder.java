package com.mickey305.foundation.v3.lang.builder.unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;

public class DownCastBuilder {

  /**
   * インスタンスの生成を試行する
   *
   * @param targetClass 生成するインスタンスのクラス
   * @param <T>         生成インスタンスの総称型
   * @return 生成されたインスタンス。失敗した場合は、nullを返却する
   */
  @SuppressWarnings("unchecked")
  static <T> T createInstanceChallenge(Class<T> targetClass) {
    T targetInstance = null;
    Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
    Arrays.sort(constructors, new Comparator<Constructor<?>>() {
      @Override
      public int compare(Constructor<?> o1, Constructor<?> o2) {
        return o1.getParameterTypes().length - o2.getParameterTypes().length;
      }
    });
    // 引数の少ないコンストラクタから順番にインスタンス生成が可能かを試行する
    for (Constructor<?> constructor : constructors) {
      constructor.setAccessible(true);
      try {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        if (parameterTypes.length == 0) {
          targetInstance = (T) constructor.newInstance();
        } else {
          Object[] parameters = new Object[parameterTypes.length];
          for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> type = parameterTypes[i];
            /**
             * 初期値を設定する
             * @see <a href="https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html">Default Values(by Oracle)</a>
             */
            if (type.equals(byte.class)) {
              parameters[i] = (byte) 0;
            } else if (type.equals(short.class)) {
              parameters[i] = (short) 0;
            } else if (type.equals(int.class)) {
              parameters[i] = 0;
            } else if (type.equals(long.class)) {
              parameters[i] = 0L;
            } else if (type.equals(float.class)) {
              parameters[i] = 0.0f;
            } else if (type.equals(double.class)) {
              parameters[i] = 0.0d;
            } else if (type.equals(char.class)) {
              parameters[i] = '\u0000';
            } else if (type.equals(boolean.class)) {
              parameters[i] = false;
            } else {
              parameters[i] = null;
            }
          }
          targetInstance = (T) constructor.newInstance(parameters);
        }
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
      }
      if (targetInstance != null) break;
    }
    return targetInstance;
  }

  /**
   * ダウンキャスト実装用のリフレクションメソッド
   *
   * @param destClass   キャスト先のクラス
   * @param srcInstance キャスト元（キャスト対象）のインスタンス
   * @param <S>         移動元の総称型
   * @param <D>         移動先の総称型
   * @return キャスト先（ダウンキャスト後）のインスタンス
   */
  public static <S, D> D reflectionDownCast(Class<D> destClass, S srcInstance) {
    // ---> Input data check
    if (!srcInstance.getClass().isAssignableFrom(destClass) || srcInstance.getClass().equals(destClass))
      return null;

    // ---> Dest-Instance creation
    D subInstance = createInstanceChallenge(destClass);
    // ---> Dest-Instance injection of Src fields
    if (subInstance != null) {
      subInstance = reflectionDownCast(subInstance, srcInstance);
    }
    return subInstance;
  }

  /**
   * ダウンキャスト実装用のリフレクションメソッド
   *
   * @param destInstance キャスト先のインスタンス
   * @param srcInstance  キャスト元（キャスト対象）のインスタンス
   * @param <S>          移動元の総称型
   * @param <D>          移動先の総称型
   * @return キャスト先（ダウンキャスト後）のインスタンス
   */
  public static <S, D> D reflectionDownCast(D destInstance, S srcInstance) {
    return ObservableDownCastBuilder.reflectionDownCast(destInstance, srcInstance, null);
  }
}
