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

package com.mickey305.foundation.v3.compat.exception.wrap;

import com.mickey305.foundation.v3.compat.exception.wrap.manager.ThrowableDefaultMessage;

public class IllegalArgumentException extends java.lang.IllegalArgumentException {
  private static final long serialVersionUID = -2833104228989642303L;
  private final String msg;
  
  /**
   * {@inheritDoc}
   */
  public IllegalArgumentException() {
    super();
    this.msg = null;
  }
  
  /**
   * {@inheritDoc}
   */
  public IllegalArgumentException(String s) {
    super(ThrowableDefaultMessage.getInstance() + s);
    this.msg = s;
  }
  
  /**
   * {@inheritDoc}
   */
  public IllegalArgumentException(String message, Throwable cause) {
    super(ThrowableDefaultMessage.getInstance() + message, cause);
    this.msg = message;
  }
  
  /**
   * {@inheritDoc}
   */
  public IllegalArgumentException(Throwable cause) {
    super(cause);
    this.msg = null;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String getLocalizedMessage() {
    final String tmpLocalizedHeader = ThrowableDefaultMessage.getInstance().getHeaderOfLocalizedMessage();
    return tmpLocalizedHeader + this.createLocalizedMessage();
  }
  
  /**
   * ローカライズされたエラーメッセージの文字列を返却します。メッセージ情報を新たに実装する場合は、このメソッドをオーバーライドして
   * ローカライズ処理を記述する。
   * @return エラーメッセージの文字列
   */
  protected String createLocalizedMessage() {
    // デフォルトでは、エラーメッセージを仮設定する
    return this.msg;
  }
}