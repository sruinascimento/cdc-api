package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.author.Author;
import br.com.rsoft.api.cdc.author.AuthorRepository;
import br.com.rsoft.api.cdc.author.ValidationErrorHandler;
import br.com.rsoft.api.cdc.category.Category;
import br.com.rsoft.api.cdc.category.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

        Category category = categoryRepository.findByName(newBookRequest.category()).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, format("Category with name %s not found",
                        newBookRequest.category())));

        Author author = authorRepository.findByName(newBookRequest.author()).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, format("Author with name %s not found",
                        newBookRequest.author())));

        Book book = newBookRequest.toModel();
        book.setCategory(category);
        book.setAuthor(author);

        bookRepository.save(book);

        return ResponseEntity.ok(new NewBookResponse(book));
    }
}
