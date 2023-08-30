package br.com.rsoft.api.cdc.state;

import br.com.rsoft.api.cdc.country.Country;
import br.com.rsoft.api.cdc.validator.UniqueValue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewStateRequest(
        @NotBlank
        @UniqueValue(domainClass = State.class, fieldName = "name")
        String name,
        @NotNull
        @Min(1)
        Long countryId) {

        public State toModel(Country country) {
            return new State(name, country);
        }
}
