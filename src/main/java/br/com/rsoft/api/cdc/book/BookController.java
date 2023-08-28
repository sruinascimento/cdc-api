package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.author.Author;
import br.com.rsoft.api.cdc.author.AuthorRepository;
import br.com.rsoft.api.cdc.author.ValidationErrorHandler;
import br.com.rsoft.api.cdc.category.Category;
import br.com.rsoft.api.cdc.category.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class BookController {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/book")
    public ResponseEntity<?> addBook(@RequestBody @Valid NewBookRequest newBookRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorHandler.getErrorMessages(bindingResult));
        }

        Category category = categoryRepository.findById(newBookRequest.categoryId()).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, format("Category with id %s not found",
                        newBookRequest.categoryId())));

        Author author = authorRepository.findById(newBookRequest.authorId()).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, format("Author with id %s not found",
                        newBookRequest.authorId())));

        Book book = newBookRequest.toModel();
        book.setCategory(category);
        book.setAuthor(author);

        bookRepository.save(book);

        return ResponseEntity.ok(new NewBookResponse(book));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookInfo>> listBooks() {
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
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, format("Book not found with id %d", id)));
        return ResponseEntity.ok(new BookDetail(book));
    }
}
