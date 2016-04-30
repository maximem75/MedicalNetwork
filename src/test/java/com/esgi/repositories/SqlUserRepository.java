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
        "INSERT INTO Category VALUES (5, 'Cardiologue')",
        "INSERT INTO User VALUES (1, 'login', 'root1', 'Flaesch', 'Arnaud1', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com', 1)",
        "INSERT INTO User VALUES (2, 'login2', 'root2', 'Flaesch', 'Arnaud2', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com', 2)",
        "INSERT INTO User VALUES (3, 'login3', 'root3', 'Flaesch', 'Arnaud3', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com', 3)",
        "INSERT INTO User VALUES (4, 'login4', 'root4', 'Flaesch', 'Arnaud4', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com', 4)",
        "INSERT INTO User VALUES (5, 'login5', 'root5', 'Flaesch', 'Arnaud5', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com', 5)",
        "INSERT INTO User VALUES (6, 'login6', 'root6', 'Flaesch', 'Arnaud6', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com', 1)",
        "INSERT INTO User VALUES (7, 'login7', 'root7', 'Flaesch', 'Arnaud7', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com', 2)",
        "INSERT INTO User VALUES (8, 'login8', 'root8', 'Flaesch', 'Arnaud8', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com',3)",
        "INSERT INTO User VALUES (9, 'login9', 'root9', 'Flaesch', 'Arnaud9', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com', 4)",
        "INSERT INTO User VALUES (10, 'login10', 'root10', 'Flaesch', 'Arnaud10', '1993-06-01', '0645517021', 'arnaudflaeschhotmail.com', 5)"
    },
    executionPhase = BEFORE_TEST_METHOD
)
@Sql(
    statements = {
        "DELETE FROM USER",
        "DELETE FROM Category"
    },
    executionPhase = AFTER_TEST_METHOD
)

public @interface SqlUserRepository {
}