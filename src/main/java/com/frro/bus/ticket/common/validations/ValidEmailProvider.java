package com.frro.bus.ticket.common.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidEmailProviderValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmailProvider {
    String message() default "Email must be from a known provider (e.g. gmail.com, outlook.com)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
