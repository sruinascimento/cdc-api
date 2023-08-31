package br.com.rsoft.api.cdc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentificationNumberValidator implements ConstraintValidator<IdentificationNumber, String> {
    @Override
    public boolean isValid(String valueToBeValidated, ConstraintValidatorContext constraintValidatorContext) {
        String cleanedIdentificationNumber = valueToBeValidated.replaceAll("[^\\d]", "");
        return cleanedIdentificationNumber.length() == 11 || cleanedIdentificationNumber.length() == 14;
    }
}
