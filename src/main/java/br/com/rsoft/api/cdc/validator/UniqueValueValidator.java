package br.com.rsoft.api.cdc.validator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private Class<?> entity;
    private String field;
    @PersistenceContext
    private final EntityManager entityManager;
    UniqueValueValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void initialize(UniqueValue values) {
        entity = values.domainClass();
        field = values.fieldName();
    }

    @Override
    public boolean isValid(Object valueToBeValidated, ConstraintValidatorContext constraintValidatorContext) {
        String jpql = String.format("SELECT COUNT(1) > 0 FROM %s WHERE %s = :value", entity.getSimpleName(), field);
        boolean existsInDB = entityManager
                .createQuery(jpql, Boolean.class)
                .setParameter("value", valueToBeValidated)
                .getSingleResult();
        return !existsInDB;
    }
}
