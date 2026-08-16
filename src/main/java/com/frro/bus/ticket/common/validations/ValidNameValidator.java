package com.frro.bus.ticket.common.validations;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidNameValidator implements ConstraintValidator<ValidName, String> {

    private static final Pattern NAME_PATTERN = Pattern.compile("^\\p{L}+(?:[\\s'\\u2019-]\\p{L}+)*$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return NAME_PATTERN.matcher(value).matches();
    }
}
