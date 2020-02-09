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

import com.mickey305.foundation.v3.compat.util.Option;

import javax.annotation.Nullable;

import static com.mickey305.foundation.v3.lang.FoundationThrowingMessageConst.HeaderString;
import static com.mickey305.foundation.v3.lang.FoundationThrowingMessageConst.LocalizedHeaderString;

public final class FoundationRuntimeExceptionProxy extends RuntimeException implements IThrowableProxyObject<RuntimeException> {
  private final static RuntimeException Dummy = new RuntimeException();
  private final Option<RuntimeException> exOrgOpt;
  private IThrowableMessageOverWriter overWriter = null;
  
  public FoundationRuntimeExceptionProxy(@Nullable RuntimeException ex) {
    this(ex, HeaderString);
  }
  
  public FoundationRuntimeExceptionProxy(@Nullable RuntimeException ex, IThrowableMessageOverWriter overWriter) {
    this(ex, (overWriter == null)
        ? HeaderString
        : overWriter.getHeaderOfMessage());
    this.overWriter = overWriter;
  }
  
  private FoundationRuntimeExceptionProxy(@Nullable RuntimeException ex, String header) {
    super(header + Option.<RuntimeException>of(ex).orElse(Dummy).getMessage(),
        Option.<RuntimeException>of(ex).orElse(Dummy).getCause());
    this.exOrgOpt = Option.<RuntimeException>of(ex);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String getLocalizedMessage() {
    final String header = (this.overWriter == null)
        ? LocalizedHeaderString
        : this.overWriter.getHeaderOfLocalizedMessage();
    return header + exOrgOpt.orElse(Dummy).getLocalizedMessage();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean typeOfOriginalObjectEquals(Class<? extends RuntimeException> throwableClass) {
    return exOrgOpt.orElse(Dummy).getClass().equals(throwableClass);
  }
}
