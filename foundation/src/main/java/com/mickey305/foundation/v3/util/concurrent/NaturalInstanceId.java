package com.mickey305.foundation.v3.util.concurrent;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class NaturalInstanceId {
  private static final Map<Class<?>, ICounter> registry;
  private static final StringBuilder builder;
  
  static {
    registry = new HashMap<>();
    builder = new StringBuilder();
  }
  
  /**
   * インスタンス固有のIDを生成する
   * <p>ID生成対象のコンストラクタ内でメソッドを1回実行し、ID番号を生成する
   * </p>
   * <p>ID体系：~[full class name for obj]~[this method invoked sequence]
   * </p>
   * <pre>{@code
   * public class SampleClass {
   *   private static final id;
   *   public SampleClass() {
   *     id = NaturalInstanceId.gen(this);
   *   }
   * }}</pre>
   *
   * @param obj 生成対象のインスタンス
   * @param <T> 生成対象の型
   * @return ID番号
   */
  // todo: IDの体系を外部から差し換えられるようにする
  @Nonnull
  public static <T> String gen(@Nonnull T obj) {
    final Class<?> target = obj.getClass();
    ICounter cachedCounter;
    synchronized (registry) {
      cachedCounter = registry.get(target);
      if (cachedCounter == null) {
        cachedCounter = new LongCounter();
        registry.put(target, cachedCounter);
      }
    }
    final long newId = cachedCounter.count();
    String result;
    synchronized (builder) {
      builder.setLength(0);
      result = builder.append("~").append(target.getName())
          .append("~").append(newId)
          .toString();
    }
    return result;
  }
}
