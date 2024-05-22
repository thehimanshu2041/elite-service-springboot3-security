package com.elite.core.bean.validation.impl;

import com.elite.core.bean.validation.MobileNumberValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class MobileNumberValidatorImpl
        implements ConstraintValidator<MobileNumberValidator, String> {

    private final String MOBILE_NUMBER_REGEX = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";

    @Override
    public void initialize(MobileNumberValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(MOBILE_NUMBER_REGEX, mobile);
    }
}
