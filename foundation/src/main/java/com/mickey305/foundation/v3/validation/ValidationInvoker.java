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

import com.mickey305.foundation.v3.compat.exception.wrap.BeanValidationException;
import org.apache.commons.lang3.tuple.Pair;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

public class ValidationInvoker {
  
  private ValidationInvoker() {
  }
  
  public static <T>
  void validateConstructor(T object) {
    Validator validator = ValidatorProvider.get();
    Set<ConstraintViolation<T>> validatorSet = validator.validate(object);
    ValidationInvoker.check(validatorSet);
  }
  
  public static <T, C extends Constructor<T>>
  void validateConstructor(C bean, Object[] parameter) {
    ExecutableValidator validator = ValidatorProvider.get().forExecutables();
    Set<ConstraintViolation<T>> validatorSet = validator.validateConstructorParameters(bean, parameter);
    ValidationInvoker.check(validatorSet);
  }
  
  @SafeVarargs
  public static <T>
  void validateMethod(
      T object, String methodName, Pair<Class<?>, Object>... arguments) throws NoSuchMethodException {
    ExecutableValidator validator = ValidatorProvider.get().forExecutables();
    Class<?>[] types = new Class<?>[arguments.length];
    Object[] parameter = new Object[arguments.length];
    for (int i = 0; i < arguments.length; i++) {
      types[i] = arguments[i].getLeft();
      parameter[i] = arguments[i].getRight();
    }
    Method method = object.getClass().getMethod(methodName, types);
    Set<ConstraintViolation<T>> validatorSet = validator.validateParameters(object, method, parameter);
    ValidationInvoker.check(validatorSet);
  }
  
  public static <T>
  void validateMethod(T object, Method method, Object[] parameter) {
    ExecutableValidator validator = ValidatorProvider.get().forExecutables();
    Set<ConstraintViolation<T>> validatorSet = validator.validateParameters(object, method, parameter);
    ValidationInvoker.check(validatorSet);
  }
  
  private static <T, R extends Set<ConstraintViolation<T>>>
  void check(R result) {
    if (result.size() == 0)
      return;
    
    StringBuilder info = new StringBuilder();
    for (ConstraintViolation<T> violation : result) {
      info.append(System.lineSeparator());
      info.append("[Path]    > ").append(violation.getPropertyPath());
      info.append(System.lineSeparator());
      info.append("[Value]   > ").append(violation.getInvalidValue());
      info.append(System.lineSeparator());
      info.append("[Message] > ").append(violation.getMessage());
      info.append(System.lineSeparator());
    }
    throw new BeanValidationException(info.toString());
  }
}
