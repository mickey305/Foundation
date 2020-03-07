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

package com.mickey305.foundation.v3.compat.exception.wrap.manager;

import com.mickey305.foundation.v3.lang.FoundationThrowingMessageConst;
import com.mickey305.foundation.v3.lang.IThrowableMessageOverWriter;

public class ThrowableDefaultMessage implements IThrowableMessageOverWriter {
  
  private static class ThrowableDefaultMessageHolder {
    private static final ThrowableDefaultMessage Instance = new ThrowableDefaultMessage();
  }
  
  public static ThrowableDefaultMessage getInstance() {
    return ThrowableDefaultMessageHolder.Instance;
  }
  
  private ThrowableDefaultMessage() {}
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String getHeaderOfMessage() {
    return FoundationThrowingMessageConst.HeaderString;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String getHeaderOfLocalizedMessage() {
    return FoundationThrowingMessageConst.LocalizedHeaderString;
  }
}
