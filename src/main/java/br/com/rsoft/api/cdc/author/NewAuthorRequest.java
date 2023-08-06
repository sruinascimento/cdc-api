package br.com.rsoft.api.cdc.author;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record NewAuthorRequest(
        @NotBlank
        @Size(min = 3)
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(max = 400)
        String description){
    public Author toEntity() {
       return new Author(name(), email(), description());
    }
}
