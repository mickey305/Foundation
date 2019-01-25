/*
 * Copyright (c) 2019. K.Misaki
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

package com.mickey305.foundation.v3.lang.tuple;

import java.io.Serializable;

/**
 * <p>A pair consisting of two elements.</p>
 *
 * <p>This class is an abstract implementation defining the extension API
 * for {@link org.apache.commons.lang3.tuple.Pair}.</p>
 *
 * <p>Subclass implementations may be mutable or immutable.
 * However, there is no restriction on the type of the stored objects that may be stored.
 * If mutable objects are stored in the pair, then the pair itself effectively becomes mutable.</p>
 *
 * @param <L> the left element type
 * @param <R> the right element type
 */
public abstract class Pair<L, R> extends org.apache.commons.lang3.tuple.Pair<L, R>
    implements Swappable<L, R>, Serializable {
  /**
   * use serialVersionUID for interoperability
   */
  private static final long serialVersionUID = 2633736899135037202L;

  //===----------------------------------------------------------------------------------------------------------===//
  // Methods                                                                                                        //
  //===----------------------------------------------------------------------------------------------------------===//
  public static <L, R> Pair<L, R> of(final L left, final R right) {
    return MutablePair.of(left, right);
  }

  public L left() {
    return this.getLeft();
  }

  public R right() {
    return this.getRight();
  }

  public L key() {
    return this.getKey();
  }

  public R value() {
    return this.getValue();
  }
}
