package com.elite.core.bean.validation;

import com.elite.core.bean.validation.impl.MobileNumberValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = {MobileNumberValidatorImpl.class})
public @interface MobileNumberValidator {
    String message() default "Please enter valid mobile number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
