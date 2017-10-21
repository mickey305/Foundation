package com.mickey305.foundation.v3.validation.annotation;

import com.mickey305.foundation.v3.validation.EscapeRejectValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {EscapeRejectValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface EscapeReject {
    String message() default "validation.EscapeReject.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
