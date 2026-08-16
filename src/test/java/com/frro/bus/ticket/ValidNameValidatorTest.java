package com.frro.bus.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.frro.bus.ticket.common.validations.ValidName;
import com.frro.bus.ticket.common.validations.ValidNameValidator;

class ValidNameValidatorTest {

    private final ValidNameValidator validator = new ValidNameValidator();

    @Test
    void acceptsSimpleAndMultiWordNames() {
        assertThat(validator.isValid("Juan", null)).isTrue();
        assertThat(validator.isValid("Maria Jose", null)).isTrue();
        assertThat(validator.isValid("Coche Cama", null)).isTrue();
    }

    @Test
    void acceptsAccentedAndUnicodeLetters() {
        assertThat(validator.isValid("María", null)).isTrue();
        assertThat(validator.isValid("Ñancufil", null)).isTrue();
        assertThat(validator.isValid("García-López", null)).isTrue();
        assertThat(validator.isValid("O'Brien", null)).isTrue();
        assertThat(validator.isValid("D’Angelo", null)).isTrue();
    }

    @Test
    void rejectsDigitsAndSymbols() {
        assertThat(validator.isValid("Juan1", null)).isFalse();
        assertThat(validator.isValid("12345", null)).isFalse();
        assertThat(validator.isValid("Pedro@", null)).isFalse();
        assertThat(validator.isValid("Ana.", null)).isFalse();
        assertThat(validator.isValid("Ro$io", null)).isFalse();
    }

    @Test
    void rejectsMalformedSeparators() {
        assertThat(validator.isValid("Ana  Maria", null)).isFalse();
        assertThat(validator.isValid("-Juan", null)).isFalse();
        assertThat(validator.isValid("Juan-", null)).isFalse();
        assertThat(validator.isValid("Ana '", null)).isFalse();
    }

    @Test
    void rejectsEmptyString() {
        assertThat(validator.isValid("", null)).isFalse();
    }

    @Test
    void acceptsNull() {
        assertThat(validator.isValid(null, null)).isTrue();
    }

    @Test
    void annotationIsValidatedByValidator() {
        assertThat(ValidName.class.isAnnotationPresent(jakarta.validation.Constraint.class)).isTrue();
    }
}
