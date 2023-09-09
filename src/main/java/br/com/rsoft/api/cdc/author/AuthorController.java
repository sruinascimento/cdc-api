package br.com.rsoft.api.cdc.author;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @PostMapping("/author")
    ResponseEntity<NewAuthorResponse> addAuthor(@RequestBody @Valid NewAuthorRequest newAuthorRequest) {
        Author author = newAuthorRequest.toModel();
        authorRepository.save(author);
        return ResponseEntity.ok(new NewAuthorResponse(author));
    }
}
