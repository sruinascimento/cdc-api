package br.com.rsoft.api.cdc.country;

import br.com.rsoft.api.cdc.validator.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record NewCountryRequest(
        @NotBlank
        @UniqueValue(domainClass = Country.class, fieldName = "name")
        String name) {

    public Country toModel() {
        return new Country(name);
    }
}
