package br.com.rsoft.api.cdc.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {IdentificationNumberValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface IdentificationNumber {
    String message() default "Identification Number invalid, must be 11 or 14 digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}