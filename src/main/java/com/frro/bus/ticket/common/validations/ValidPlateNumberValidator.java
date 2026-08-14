package com.frro.bus.ticket.common.validations;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPlateNumberValidator implements ConstraintValidator<ValidPlateNumber, String> {

    private static final Pattern PLATE_PATTERN = Pattern.compile(
            "^(?:[a-z]{2}[ -]?[0-9]{3}[ -]?[a-z]{2}|[a-z]{3}[ -]?[0-9]{3})$",
            Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return PLATE_PATTERN.matcher(value).matches();
    }
}
