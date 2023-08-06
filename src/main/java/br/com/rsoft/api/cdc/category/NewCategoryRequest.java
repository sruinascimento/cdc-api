package br.com.rsoft.api.cdc.category;

import jakarta.validation.constraints.NotBlank;

public record NewCategoryRequest(@NotBlank String name) {
    public Category toModel() {
        return new Category(name());
    }
}
