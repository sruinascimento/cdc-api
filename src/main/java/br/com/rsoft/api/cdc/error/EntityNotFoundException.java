package br.com.rsoft.api.cdc.error;

import static java.lang.String.format;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Long id) {
        super(format("%s with id %s not found", entityName, id));
    }
}
