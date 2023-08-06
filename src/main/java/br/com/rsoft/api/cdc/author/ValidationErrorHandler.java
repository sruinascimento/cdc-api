package br.com.rsoft.api.cdc.author;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationErrorHandler {
    public static Map<String, List<String>> getErrorMessages(BindingResult bindingResult) {
        Map<String, List<String>> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));
        return errors;
    }
}
