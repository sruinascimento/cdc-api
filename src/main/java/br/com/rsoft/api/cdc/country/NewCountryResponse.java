package br.com.rsoft.api.cdc.country;

public record NewCountryResponse(String name) {
    public NewCountryResponse(Country country) {
        this(country.getName());
    }
}
