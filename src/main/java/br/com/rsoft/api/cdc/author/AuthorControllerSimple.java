package br.com.rsoft.api.cdc.author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorControllerSimple {
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/authorv2")
    @Transactional
    public ResponseEntity addAuthor(@RequestBody @Valid NewAuthorRequest newAuthorRequest) {
        if (isEmailAlreadyUsed(newAuthorRequest.email())) {
            return ResponseEntity.badRequest().body("Email já existente");
        }

        Author author = newAuthorRequest.toModel();
        entityManager.persist(author);

        return ResponseEntity.ok().body(new NewAuthorResponse(author));
    }

    private boolean isEmailAlreadyUsed(String email) {
        try {
            entityManager.createQuery("SELECT a FROM Author a WHERE a.email = :email", Author.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
