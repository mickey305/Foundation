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

package com.mickey305.foundation.v3.validation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class ValidatorProvider {
  private Validator validator;
  
  private ValidatorProvider() {
    this.build();
  }
  
  public static Validator get() {
    return ValidatorHolder.INSTANCE.getValidator();
  }
  
  public static void rebuild() {
    ValidatorHolder.INSTANCE.build();
  }
  
  private static class ValidatorHolder {
    private static final ValidatorProvider INSTANCE = new ValidatorProvider();
  }
  
  private void build() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    this.setValidator(validator);
  }
  
  private Validator getValidator() {
    return validator;
  }
  
  private void setValidator(Validator validator) {
    this.validator = validator;
  }
}
