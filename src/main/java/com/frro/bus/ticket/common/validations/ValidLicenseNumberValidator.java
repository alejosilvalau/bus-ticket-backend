package com.frro.bus.ticket.common.validations;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidLicenseNumberValidator implements ConstraintValidator<ValidLicenseNumber, String> {

    private static final Pattern LICENSE_PATTERN = Pattern.compile("^\\d{7,8}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return LICENSE_PATTERN.matcher(value).matches();
    }
}
