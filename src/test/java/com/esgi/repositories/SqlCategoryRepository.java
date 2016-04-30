package com.esgi.repositories;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Sql(
    statements = {
        "INSERT INTO Category VALUES (1, 'Ophtalmologiste')",
        "INSERT INTO Category VALUES (2, 'Médecin généraliste')",
        "INSERT INTO Category VALUES (3, 'ORL')",
        "INSERT INTO Category VALUES (4, 'Kinésithérapeute')",
        "INSERT INTO Category VALUES (5, 'Cardiologue')"
    },
    executionPhase = BEFORE_TEST_METHOD
)
@Sql(
    statements = {
        "DELETE FROM Category"
    },
    executionPhase = AFTER_TEST_METHOD
)

public @interface SqlCategoryRepository {
}