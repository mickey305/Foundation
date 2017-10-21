package com.mickey305.foundation.v3.validation;

import com.mickey305.foundation.v3.validation.annotation.EscapeReject;
import com.mickey305.foundation.v3.ansi.code.Escape;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EscapeRejectValidator implements ConstraintValidator<EscapeReject, Object> {
    @Override
    public void initialize(EscapeReject constraintAnnotation) {}

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return !containsEscapeCode(value);
    }

    private static boolean containsEscapeCode(Object object) {
        final Escape[] escapeLst = Escape.values();
        for (Escape escape: escapeLst) {
            if (String.valueOf(object).contains(escape.code()))
                return true;
        }
        return false;
    }

    private static boolean isEscapeCode(Object object) {
        final Escape[] escapeLst = Escape.values();
        for (Escape escape: escapeLst) {
            if (String.valueOf(object).equals(escape.code()))
                return true;
        }
        return false;
    }
}
