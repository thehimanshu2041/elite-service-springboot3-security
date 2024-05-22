package com.elite.core.bean.validation.impl;

import com.elite.core.bean.validation.UsernameValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UsernameValidatorImpl implements ConstraintValidator<UsernameValidator, String> {

    private final String USERNAME_REGEX = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";

    @Override
    public void initialize(UsernameValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(USERNAME_REGEX, username);
    }
}
