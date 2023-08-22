package br.com.rsoft.api.cdc.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ISBNPatternValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ISBN {
    String message() default "ISBN invalid, must be 10 or 13 digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
