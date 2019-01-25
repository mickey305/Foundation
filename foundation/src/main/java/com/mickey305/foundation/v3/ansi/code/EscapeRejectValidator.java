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

package com.mickey305.foundation.v3.ansi.code;

import com.mickey305.foundation.v3.validation.annotation.EscapeReject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EscapeRejectValidator implements ConstraintValidator<EscapeReject, Object> {
  /**
   * {@inheritDoc}
   */
  @Override
  public void initialize(EscapeReject constraintAnnotation) {
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    return !containsEscapeCode(value);
  }
  
  private static boolean containsEscapeCode(Object object) {
    final Escape[] escapeLst = Escape.values();
    for (Escape escape : escapeLst) {
      if (String.valueOf(object).contains(escape.code()))
        return true;
    }
    return false;
  }
  
  private static boolean isEscapeCode(Object object) {
    final Escape[] escapeLst = Escape.values();
    for (Escape escape : escapeLst) {
      if (String.valueOf(object).equals(escape.code()))
        return true;
    }
    return false;
  }
}
