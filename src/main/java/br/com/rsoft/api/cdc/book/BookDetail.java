package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.author.AuthorDetail;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public record BookDetail(String title,
                         BigDecimal price,
                         String summary,
                         String tableOfContent,
                         String isbn,
                         Integer numberOfPages,
                         String publicationDate,
                         AuthorDetail author) {
    public BookDetail(Book book) {
        this(book.getTitle(),
                book.getPrice(),
                book.getSummary(),
                book.getTableOfContent(),
                book.getIsbn(),
                book.getNumberOfPages(),
                book.getPublicationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                new AuthorDetail(book.getAuthor().getName(), book.getAuthor().getDescription()));
    }
}
