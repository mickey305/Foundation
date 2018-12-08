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
