package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.validator.ISBN;
import br.com.rsoft.api.cdc.validator.UniqueValue;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record NewBookRequest(
        @NotBlank
        @UniqueValue(domainClass = Book.class, fieldName = "title")
        String title,
        @NotBlank
        @Size(max = 500)
        String summary,
        String tableOfContent,
        @DecimalMin("20.00")
        BigDecimal price,
        @NotNull
        @Min(100)
        Integer numberOfPages,
        @NotBlank
        @UniqueValue(fieldName = "isbn", domainClass = Book.class)
        @ISBN
        String isbn,
        @Pattern(regexp = "\\d{2}([-/])\\d{2}([-/])\\d{4}", message = "must be in the format dd/MM/yyyy or dd-MM-yyyy")
        String publicationDate,
        @NotNull
        Long categoryId,
        @NotNull
        Long authorId) {
    public Book toModel() {
        return new Book(title,
                summary,
                tableOfContent,
                price,
                numberOfPages,
                isbn.replaceAll("[^\\dX]", ""),
                LocalDate.parse(publicationDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
