package br.com.rsoft.api.cdc.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ISBNPatternValidator implements ConstraintValidator<ISBN, String> {

    @Override
    public boolean isValid(String valueToBeValidated, ConstraintValidatorContext constraintValidatorContext) {
        String cleanedISBN = valueToBeValidated.replaceAll("[^\\dX]", "");
        return cleanedISBN.length() == 10 || cleanedISBN.length() == 13;
    }
}
