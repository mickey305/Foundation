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

public final class FoundationThrowableProxy extends Throwable implements IThrowableProxyObject<Throwable> {
  private static final Throwable Dummy = new Throwable();
  private static final long serialVersionUID = -8830644095906702201L;
  private final Option<Throwable> thOrgOpt;
  private IThrowableMessageOverWriter overWriter = null;
  
  public FoundationThrowableProxy(@Nullable Throwable th) {
    this(th, HeaderString);
  }
  
  public FoundationThrowableProxy(@Nullable Throwable th, IThrowableMessageOverWriter overWriter) {
    this(th, (overWriter == null)
        ? HeaderString
        : overWriter.getHeaderOfMessage());
    this.overWriter = overWriter;
  }
  
  private FoundationThrowableProxy(@Nullable Throwable th, String header) {
    super(header + Option.<Throwable>of(th).orElse(Dummy).getMessage(),
        Option.<Throwable>of(th).orElse(Dummy).getCause());
    this.thOrgOpt = Option.<Throwable>of(th);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String getLocalizedMessage() {
    final String header = (this.overWriter == null)
        ? LocalizedHeaderString
        : this.overWriter.getHeaderOfLocalizedMessage();
    return header + thOrgOpt.orElse(Dummy).getLocalizedMessage();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean typeOfOriginalObjectEquals(Class<? extends Throwable> throwableClass) {
    return thOrgOpt.orElse(Dummy).getClass().equals(throwableClass);
  }
}
