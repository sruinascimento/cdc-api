package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.error.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookInfo>> showBooks() {
        List<BookInfo> books = bookRepository.findAll().stream()
                .map(BookInfo::new)
                .toList();

        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(books);
    }

    @GetMapping("/book/{id}")
    ResponseEntity<BookDetail> getBookDetail(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book", id));
        return ResponseEntity.ok(new BookDetail(book));
    }
}
