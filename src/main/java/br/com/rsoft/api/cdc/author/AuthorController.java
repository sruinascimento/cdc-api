package br.com.rsoft.api.cdc.author;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
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
    ResponseEntity addAuthor(@RequestBody @Valid NewAuthorRequest newAuthorRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorHandler.getErrorMessages(bindingResult));
        }

        Author author = newAuthorRequest.toModel();
        authorRepository.save(author);
        return ResponseEntity.ok(new NewAuthorResponse(author));
    }
}
