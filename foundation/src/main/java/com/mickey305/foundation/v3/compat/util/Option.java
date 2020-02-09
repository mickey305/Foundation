/*
 * Copyright (c) 2017 - 2020 K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mickey305.foundation.v3.compat.util;

import com.mickey305.foundation.v3.compat.stream.Consumer;
import com.mickey305.foundation.v3.compat.stream.Supplier;
import com.mickey305.foundation.v3.util.Assert;

import java.util.NoSuchElementException;
import java.util.Objects;

// JDK1.8 以降は標準APIを参照 base: java.util.Optional
/**
 * null排除ラッパークラス
 * @param <T> 内部保持オブジェクトの総称型
 */
public final class Option<T> {
  private static final Option<?> EMPTY = new Option<>();
  
  private final T value;
  
  private Option() {
    this.value = null;
  }
  
  @SuppressWarnings("unchecked")
  public static <T> Option<T> empty() {
    return (Option<T>) EMPTY;
  }
  
  private Option(T value) {
    this.value = Assert.requireNonNull(value);
  }
  
  private static <T> Option<T> ofDefault(T value) {
    return new Option<>(value);
  }
  
  @SuppressWarnings("unchecked")
  public static <T> Option<T> of(T value) {
    return value == null ? (Option<T>) empty() : ofDefault(value);
  }
  
  public T get() {
    if (value == null) {
      throw new NoSuchElementException("No value present");
    }
    return value;
  }
  
  public boolean isPresent() {
    return value != null;
  }
  
  public void ifPresent(Consumer<? super T> action) {
    if (value != null) {
      action.accept(value);
    }
  }
  
  public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
    if (value != null) {
      action.accept(value);
    } else {
      emptyAction.run();
    }
  }
  
  public <U> Option<U> map(Function<? super T, ? extends U> mapper) {
    Assert.requireNonNull(mapper);
    if (!isPresent()) {
      return empty();
    } else {
      return Option.<U>of(mapper.apply(value));
    }
  }
  
  public <U> Option<U> flatMap(Function<? super T, ? extends Option<? extends U>> mapper) {
    Assert.requireNonNull(mapper);
    if (!isPresent()) {
      return empty();
    } else {
      @SuppressWarnings("unchecked")
      Option<U> r = (Option<U>) mapper.apply(value);
      return Assert.requireNonNull(r);
    }
  }
  
  public Option<T> or(Supplier<? extends Option<? extends T>> supplier) {
    Assert.requireNonNull(supplier);
    if (isPresent()) {
      return this;
    } else {
      @SuppressWarnings("unchecked")
      Option<T> r = (Option<T>) supplier.get();
      return Assert.requireNonNull(r);
    }
  }
  
  public T orElse(T other) {
    return value != null ? value : other;
  }
  
  public T orElseGet(Supplier<? extends T> supplier) {
    return value != null ? value : supplier.get();
  }
  
  public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
    if (value != null) {
      return value;
    } else {
      throw exceptionSupplier.get();
    }
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Option)) {
      return false;
    }
    
    Option<?> other = (Option<?>) obj;
    return Objects.equals(value, other.value);
  }
  
  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
  
  @Override
  public String toString() {
    return value != null
        ? String.format("Optional[%s]", value)
        : "Optional.empty";
  }
}
