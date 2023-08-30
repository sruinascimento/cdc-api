package br.com.rsoft.api.cdc.country;

import br.com.rsoft.api.cdc.author.ValidationErrorHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {
    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostMapping("/country")
    ResponseEntity<?> addCountry(@RequestBody @Valid NewCountryRequest newCountryRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorHandler.getErrorMessages(bindingResult));
        }

        Country country = newCountryRequest.toModel();
        countryRepository.save(country);

        return ResponseEntity.ok(new NewCountryResponse(country));
    }
}
