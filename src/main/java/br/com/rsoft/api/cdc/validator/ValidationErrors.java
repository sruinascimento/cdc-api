package br.com.rsoft.api.cdc.validator;

import java.util.List;

public record ValidationErrors(List<FieldErrorValidation> fieldErrors) {
}
