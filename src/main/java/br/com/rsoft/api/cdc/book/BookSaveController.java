package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.author.Author;
import br.com.rsoft.api.cdc.author.AuthorRepository;
import br.com.rsoft.api.cdc.category.Category;
import br.com.rsoft.api.cdc.category.CategoryRepository;
import br.com.rsoft.api.cdc.error.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookSaveController {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    public BookSaveController(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/book")
    public ResponseEntity<NewBookResponse> addBook(@RequestBody @Valid NewBookRequest newBookRequest) {
        Category category = categoryRepository.findById(newBookRequest.categoryId()).orElseThrow(
                () -> new EntityNotFoundException("Category", newBookRequest.categoryId()));

        Author author = authorRepository.findById(newBookRequest.authorId()).orElseThrow(
                () -> new EntityNotFoundException("Author", newBookRequest.authorId()));

        Book book = newBookRequest.toModel();
        book.setCategory(category);
        book.setAuthor(author);

        bookRepository.save(book);

        return ResponseEntity.ok(new NewBookResponse(book));
    }
}
