package com.elite.core.bean.validation;

import com.elite.core.bean.validation.impl.PasswordValidatorImpl;
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
@Constraint(validatedBy = {PasswordValidatorImpl.class})
public @interface PasswordValidator {
    String message() default
            "Password should be minimum 8 characters, maximum 20 characters, at least one uppercase letter, one lowercase letter, one number and one special character.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
