package br.com.rsoft.api.cdc.state;

import br.com.rsoft.api.cdc.author.Author;
import br.com.rsoft.api.cdc.author.ValidationErrorHandler;
import br.com.rsoft.api.cdc.country.Country;
import br.com.rsoft.api.cdc.country.CountryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class StateController {
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public StateController(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    @PostMapping("/state")
    public ResponseEntity<?> addState(@RequestBody @Valid NewStateRequest newStateRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorHandler.getErrorMessages(bindingResult));
        }

        Country country = countryRepository.findById(newStateRequest.countryId()).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, format("Country with id %s not found",
                        newStateRequest.countryId())));

        State state = newStateRequest.toModel(country);

        stateRepository.save(state);

        return ResponseEntity.ok(new NewStateResponse(state));
    }
}
