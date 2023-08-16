package br.com.rsoft.api.cdc.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/book")
    public void addBook() {

    }
}
