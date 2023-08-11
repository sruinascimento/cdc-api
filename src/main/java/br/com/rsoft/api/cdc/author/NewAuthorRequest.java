package br.com.rsoft.api.cdc.author;

import br.com.rsoft.api.cdc.validator.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewAuthorRequest(
        @NotBlank
        @Size(min = 3)
        String name,
        @NotBlank
        @Email
        @UniqueValue(fieldName = "email", domainClass = Author.class)
        String email,
        @NotBlank
        @Size(max = 400)
        String description){
    public Author toModel() {
       return new Author(name(), email(), description());
    }
}
