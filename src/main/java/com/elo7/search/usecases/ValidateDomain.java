package com.elo7.search.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidateDomain {

    private final Validator validator;

    @Autowired
    public ValidateDomain(final Validator validator) {
        this.validator = validator;
    }

    public void validate(final Object object) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new RuntimeException("Constraint violations...");
        }
    }

}
