package br.com.rsoft.api.cdc.validator;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class CustomErrorHandler {
    private final MessageSource messageSource;

    public CustomErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrors handleValidationError(MethodArgumentNotValidException exception) {
        return buildValidationErrors(exception.getBindingResult().getFieldErrors());
    }

    private ValidationErrors buildValidationErrors(List<FieldError> fields) {
        return new ValidationErrors(
                fields.stream()
                        .map(field -> new FieldErrorValidation(field.getField(), getErrorMessage(field)))
                        .toList()
        );
    }

    private String getErrorMessage(ObjectError message) {
        return messageSource.getMessage(message, LocaleContextHolder.getLocale());
    }
}
