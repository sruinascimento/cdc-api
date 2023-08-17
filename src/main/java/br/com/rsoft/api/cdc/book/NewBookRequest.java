package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.validator.UniqueValue;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewBookRequest(
        @NotBlank
        @UniqueValue(domainClass = Book.class, fieldName = "title")
        String title,
        @NotBlank
        @Max(500)
        String summary,
        String tableOfContent,
        @DecimalMin("20.00")
        BigDecimal price,
        @NotBlank
        @Min(100)
        Integer numberOfPages,
        @NotBlank
        String isbn,
        @Future
        LocalDate publicationDate,
        @NotNull
        String category,
        @NotNull
        String author) {
    public Book toModel() {
        return new Book(title, summary, tableOfContent, price, numberOfPages, isbn, publicationDate);
    }
}
