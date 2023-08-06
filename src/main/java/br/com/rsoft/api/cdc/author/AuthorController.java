package br.com.rsoft.api.cdc.author;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping("/author")
    ResponseEntity<?> addAuthor(@RequestBody @Valid NewAuthorRequest newAuthorRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        if (authorRepository.existsByEmail(newAuthorRequest.email())) {
            return ResponseEntity.badRequest().body("Email must be unique");
        }

        Author author = newAuthorRequest.toEntity();
        authorRepository.save(author);
        return ResponseEntity.ok(new NewAuthorResponse(author));
    }
}
