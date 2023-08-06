package br.com.rsoft.api.cdc.category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @Deprecated
    public Category() {
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

