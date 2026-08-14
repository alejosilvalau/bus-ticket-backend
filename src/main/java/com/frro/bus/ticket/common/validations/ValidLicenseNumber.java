package com.frro.bus.ticket.common.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidLicenseNumberValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLicenseNumber {
    String message() default "License number must be a valid Argentine DNI (7 or 8 digits)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
