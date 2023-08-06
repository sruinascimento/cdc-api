package br.com.rsoft.api.cdc.author;

import java.time.LocalDate;

public record NewAuthorResponse(
        String name,
        String email,
        String description,
        LocalDate registredAt) {

    public NewAuthorResponse(Author author) {
        this(author.getName(), author.getEmail(), author.getDescription(), author.getRegistredAt());
    }
}
