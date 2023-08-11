package br.com.rsoft.api.cdc.category;

import br.com.rsoft.api.cdc.validator.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record NewCategoryRequest(
        @NotBlank
        @UniqueValue(fieldName = "name", domainClass = Category.class)
        String name) {
    public Category toModel() {
        return new Category(name());
    }
}
