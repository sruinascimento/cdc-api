package br.com.rsoft.api.cdc.category;

public record NewCategoryResponse(String name) {
    public NewCategoryResponse(Category category) {
        this(category.getName());
    }

}
