package com.frro.bus.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.frro.bus.ticket.common.validations.ValidLicenseNumber;
import com.frro.bus.ticket.common.validations.ValidLicenseNumberValidator;

class ValidLicenseNumberValidatorTest {

    private final ValidLicenseNumberValidator validator = new ValidLicenseNumberValidator();

    @Test
    void acceptsSevenAndEightDigitDni() {
        assertThat(validator.isValid("1234567", null)).isTrue();
        assertThat(validator.isValid("30123456", null)).isTrue();
    }

    @Test
    void rejectsNonDigitInput() {
        assertThat(validator.isValid("3012345A", null)).isFalse();
        assertThat(validator.isValid("30.123.456", null)).isFalse();
        assertThat(validator.isValid("30 123456", null)).isFalse();
        assertThat(validator.isValid("30123456-9", null)).isFalse();
    }

    @Test
    void rejectsWrongLength() {
        assertThat(validator.isValid("123456", null)).isFalse();
        assertThat(validator.isValid("123456789", null)).isFalse();
    }

    @Test
    void acceptsNull() {
        assertThat(validator.isValid(null, null)).isTrue();
    }

    @Test
    void annotationIsValidatedByValidator() {
        assertThat(ValidLicenseNumber.class.isAnnotationPresent(jakarta.validation.Constraint.class)).isTrue();
    }
}
