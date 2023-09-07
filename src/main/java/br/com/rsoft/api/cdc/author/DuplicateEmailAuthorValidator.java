package br.com.rsoft.api.cdc.author;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class DuplicateEmailAuthorValidator implements Validator {
    private final AuthorRepository authorRepository;

    public DuplicateEmailAuthorValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean supports(Class<?> targetClass) {
        return NewAuthorRequest.class.isAssignableFrom(targetClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NewAuthorRequest newAuthorRequest = (NewAuthorRequest) target;
        Optional<Author> possibleAuthor = authorRepository.findByEmail(newAuthorRequest.email());

        if (possibleAuthor.isPresent()) {
            errors.rejectValue("email", null, "Email already exists");
        }

    }

}
