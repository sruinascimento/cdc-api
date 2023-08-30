package br.com.rsoft.api.cdc.state;

import br.com.rsoft.api.cdc.country.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "states")
public class State {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Deprecated
    public State() {
    }

    public State(@NotBlank String name, @NotNull @Min(1) Country country) {
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }
}
