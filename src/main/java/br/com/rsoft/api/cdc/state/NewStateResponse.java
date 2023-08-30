package br.com.rsoft.api.cdc.state;

public record NewStateResponse(String name, String countryName) {

    public NewStateResponse(State state) {
        this(state.getName(), state.getCountry().getName());
    }
}
