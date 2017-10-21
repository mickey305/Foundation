package com.mickey305.foundation.v3.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.util.Set;

public class ValidationInvoker {

    private ValidationInvoker() {}

    public static <T>
    void validateConstructor(T object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> validatorSet = validator.validate(object);

        ValidationInvoker.check(validatorSet);
    }

    public static <T, C extends Constructor<T>>
    void validateConstructor(C bean, Object[] parameter) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        ExecutableValidator validator = factory.getValidator().forExecutables();
        Set<ConstraintViolation<T>> validatorSet = validator.validateConstructorParameters(bean, parameter);

        ValidationInvoker.check(validatorSet);
    }

    private static <T, R extends Set<ConstraintViolation<T>>>
    void check(R result) {
        if (result.size() == 0)
            return;

        StringBuilder info = new StringBuilder();
        for (ConstraintViolation<T> violation: result) {
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
