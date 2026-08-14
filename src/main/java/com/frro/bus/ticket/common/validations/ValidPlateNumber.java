package com.frro.bus.ticket.common.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidPlateNumberValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPlateNumber {
    String message() default "Plate number must be a valid Argentine plate (e.g. ab123cd or abc123)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
