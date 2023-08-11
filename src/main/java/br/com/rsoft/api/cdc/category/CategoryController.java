package br.com.rsoft.api.cdc.category;

import br.com.rsoft.api.cdc.author.ValidationErrorHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/category")
    ResponseEntity<?> addCategory(@RequestBody @Valid NewCategoryRequest newCategoryRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorHandler.getErrorMessages(bindingResult));
        }

        Category category = newCategoryRequest.toModel();
        categoryRepository.save(category);

        return ResponseEntity.ok(new NewCategoryResponse(category));

    }
}
