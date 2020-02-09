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

package com.mickey305.foundation.v3.lang;

import com.mickey305.foundation.v3.util.pattern.LineChainedNode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public final class StackFinder {
  
  private StackFinder() {
    // nop
  }
  
  @Nullable
  public static StackTraceElement tryGet(@Nonnull Position position) {
    Objects.requireNonNull(position);
    // stacktrace load point
    // The position definition sets the relative index from this code point.
    StackTraceElement[] elements = Thread.currentThread().getStackTrace();
    return (position.index >= 0 && position.index < elements.length)
        ? elements[position.index]
        : null;
  }
  
  public static final class Position implements LineChainedNode<Position> {
    private int index;
    
    public static Position thisCodeBlock() {
      // index 1 -> this StackFinder#tryGet block stacktrace
      // index 2 -> this StackFinder#tryGet called block stacktrace
      return new Position(2);
    }
  
    public static Position thisMethodParent() {
      return Position.thisCodeBlock().parent();
    }
    
    public static Position thisMethodCaller() {
      return Position.thisCodeBlock().parent();
    }
    
    private Position(int index) {
      this.index = index;
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public Position parent() {
      this.index++;
      return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position child() {
      this.index--;
      return this;
    }
    
    public Position caller() {
      return this.parent();
    }
    
    public Position callee() {
      return this.child();
    }
  }
}
