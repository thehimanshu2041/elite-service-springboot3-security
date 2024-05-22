package com.elite.core.bean.validation;

import com.elite.core.bean.validation.impl.UsernameValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = {UsernameValidatorImpl.class})
public @interface UsernameValidator {

    String message() default "Username should be minimum 8 characters and maximum 20 characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
