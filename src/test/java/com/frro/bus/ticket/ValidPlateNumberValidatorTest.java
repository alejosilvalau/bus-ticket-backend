package com.frro.bus.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.frro.bus.ticket.common.validations.ValidPlateNumberValidator;

class ValidPlateNumberValidatorTest {

    private final ValidPlateNumberValidator validator = new ValidPlateNumberValidator();

    @Test
    void acceptsNewMercosurFormat() {
        assertThat(validator.isValid("ab123cd", null)).isTrue();
        assertThat(validator.isValid("AB123CD", null)).isTrue();
        assertThat(validator.isValid("Aa123Zz", null)).isTrue();
    }

    @Test
    void acceptsOldFormat() {
        assertThat(validator.isValid("abc123", null)).isTrue();
        assertThat(validator.isValid("ABC123", null)).isTrue();
    }

    @Test
    void rejectsSeparators() {
        assertThat(validator.isValid("ab-123-cd", null)).isFalse();
        assertThat(validator.isValid("ab 123 cd", null)).isFalse();
        assertThat(validator.isValid("ab.123.cd", null)).isFalse();
        assertThat(validator.isValid("abc-123", null)).isFalse();
    }

    @Test
    void rejectsInvalidInput() {
        assertThat(validator.isValid("ab12cd", null)).isFalse();
        assertThat(validator.isValid("ab123cde", null)).isFalse();
        assertThat(validator.isValid("1234567", null)).isFalse();
        assertThat(validator.isValid("", null)).isFalse();
    }

    @Test
    void acceptsNull() {
        assertThat(validator.isValid(null, null)).isTrue();
    }
}
