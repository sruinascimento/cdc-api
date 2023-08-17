package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.validator.UniqueValue;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewBookResponse(
        String title,
        String summary,
        String tableOfContent,
        BigDecimal price,
        Integer numberOfPages,
        String isbn,
        LocalDate publicationDate,
        String category,
        String author
) {
    public NewBookResponse(Book book) {
        this(book.getTitle(),
                book.getSummary(),
                book.getTableOfContent(),
                book.getPrice(),
                book.getNumberOfPages(),
                book.getIsbn(),
                book.getPublicationDate(),
                book.getCategory().getName(),
                book.getAuthor().getName());
    }

}
