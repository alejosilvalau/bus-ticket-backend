package com.frro.bus.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.frro.bus.ticket.common.validations.ValidEmailProvider;
import com.frro.bus.ticket.common.validations.ValidEmailProviderValidator;

class ValidEmailProviderValidatorTest {

    private final ValidEmailProviderValidator validator = new ValidEmailProviderValidator();

    @Test
    void acceptsKnownProviders() {
        assertThat(validator.isValid("juan@gmail.com", null)).isTrue();
        assertThat(validator.isValid("maria@outlook.com", null)).isTrue();
        assertThat(validator.isValid("carlos@hotmail.com", null)).isTrue();
        assertThat(validator.isValid("lucia@yahoo.com.ar", null)).isTrue();
        assertThat(validator.isValid("diego@uol.com.ar", null)).isTrue();
        assertThat(validator.isValid("sofia@icloud.com", null)).isTrue();
        assertThat(validator.isValid("pedro@fibertel.com.ar", null)).isTrue();
        assertThat(validator.isValid("ana@proton.me", null)).isTrue();
    }

    @Test
    void acceptsKnownProviderCaseInsensitive() {
        assertThat(validator.isValid("juan@Gmail.com", null)).isTrue();
        assertThat(validator.isValid("juan@OUTLOOK.COM", null)).isTrue();
    }

    @Test
    void rejectsUnknownProviders() {
        assertThat(validator.isValid("juan@mail.com", null)).isFalse();
        assertThat(validator.isValid("admin@bus.com", null)).isFalse();
        assertThat(validator.isValid("juan@desconocido.com", null)).isFalse();
        assertThat(validator.isValid("juan@hotmail.ar", null)).isFalse();
        assertThat(validator.isValid("juan@mipropiaempresa.com.ar", null)).isFalse();
    }

    @Test
    void rejectsMalformedEmail() {
        assertThat(validator.isValid("juan", null)).isFalse();
        assertThat(validator.isValid("juan@", null)).isFalse();
        assertThat(validator.isValid("juan@outlook", null)).isFalse();
    }

    @Test
    void acceptsNullAndBlank() {
        assertThat(validator.isValid(null, null)).isTrue();
        assertThat(validator.isValid("", null)).isTrue();
        assertThat(validator.isValid("   ", null)).isTrue();
    }

    @Test
    void annotationIsValidatedByValidator() {
        assertThat(ValidEmailProvider.class.isAnnotationPresent(jakarta.validation.Constraint.class)).isTrue();
    }
}
