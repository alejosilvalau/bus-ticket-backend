package com.frro.bus.ticket.common.validations;

import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidEmailProviderValidator implements ConstraintValidator<ValidEmailProvider, String> {

    private static final Set<String> KNOWN_PROVIDERS = Set.of(
            "gmail.com",
            "outlook.com",
            "hotmail.com",
            "yahoo.com",
            "yahoo.com.ar",
            "icloud.com",
            "live.com",
            "proton.me",
            "protonmail.com",
            "aol.com",
            "msn.com",
            "gmx.com",
            "zoho.com",
            "uol.com.ar",
            "fibertel.com.ar",
            "speedy.com.ar");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        int atIndex = value.lastIndexOf('@');
        if (atIndex < 0 || atIndex == value.length() - 1) {
            return false;
        }
        String domain = value.substring(atIndex + 1).toLowerCase();
        return KNOWN_PROVIDERS.contains(domain);
    }
}
