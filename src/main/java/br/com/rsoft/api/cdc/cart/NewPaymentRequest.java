package br.com.rsoft.api.cdc.cart;

import jakarta.validation.constraints.*;

public record NewPaymentRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @NotBlank
        String identificationNumber,
        @NotBlank
        String address,
        @NotBlank
        String complement,
        @NotBlank
        String city,
        @Min(1)
        @NotNull
        Long countryId,
        Long stateId,
        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "^[0-9]{5}[0-9]{3}$", message = "CEP must be in the format XXXXXXXX without - or letters")
        String cep) {
}
