package com.mickey305.foundation.v3.validation;

import org.apache.commons.lang3.tuple.Pair;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

public class ValidationInvoker {

    private ValidationInvoker() {}

    public static <T>
    void validateConstructor(T object) {
        Validator validator = ValidatorProvider.getInstance();
        Set<ConstraintViolation<T>> validatorSet = validator.validate(object);
        ValidationInvoker.check(validatorSet);
    }

    public static <T, C extends Constructor<T>>
    void validateConstructor(C bean, Object[] parameter) {
        ExecutableValidator validator = ValidatorProvider.getInstance().forExecutables();
        Set<ConstraintViolation<T>> validatorSet = validator.validateConstructorParameters(bean, parameter);
        ValidationInvoker.check(validatorSet);
    }

    @SafeVarargs
    public static <T>
    void validateMethod(
            T object, String methodName, Pair<Class<?>, Object>... arguments) throws NoSuchMethodException {
        ExecutableValidator validator = ValidatorProvider.getInstance().forExecutables();
        Class<?>[] types = new Class[arguments.length];
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
        ExecutableValidator validator = ValidatorProvider.getInstance().forExecutables();
        Set<ConstraintViolation<T>> validatorSet = validator.validateParameters(object, method, parameter);
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
