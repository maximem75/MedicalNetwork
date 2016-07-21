package com.esgi.repositories.datasets;

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
        "INSERT INTO User VALUES (1, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud1', 'login', 'Flaesch', 'root1', '0645517021', 'token', '2017-10-04', 1)",
        "INSERT INTO User VALUES (2, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud2', 'login2', 'Flaesch', 'root2', '0645517021', 'token2', '2017-10-04', 1)",
        "INSERT INTO User VALUES (3, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud3', 'login3', 'Flaesch', 'root3', '0645517021', 'token3', '2017-10-04', 1)",
        "INSERT INTO User VALUES (4, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud4', 'login4', 'Flaesch', 'root4', '0645517021', 'token4', '2017-10-04', 1)",
        "INSERT INTO User VALUES (5, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud5', 'login5', 'Flaesch', 'root5', '0645517021', 'token5', '2017-10-04', 1)",
        "INSERT INTO User VALUES (6, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud6', 'login5', 'Flaesch', 'root6', '0645517021', 'token6', '2017-10-04', 1)",
        "INSERT INTO User VALUES (7, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud7', 'login6', 'Flaesch', 'root7', '0645517021', 'token7', '2017-10-04', 1)",
        "INSERT INTO User VALUES (8, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud8', 'login7', 'Flaesch', 'root8', '0645517021', 'token8', '2017-10-04', 1)",
        "INSERT INTO User VALUES (9, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud9', 'login8', 'Flaesch', 'root9', '0645517021', 'token9', '2017-10-04', 1)",
        "INSERT INTO User VALUES (10, '1993-06-01', 'arnaudflaeschhotmail.com', 'Arnaud10', 'login9', 'Flaesch', 'root10', '0645517021', 'token10', '2017-10-04', 1)"
    },
    executionPhase = BEFORE_TEST_METHOD
)
@Sql(
    statements = {
        "DELETE FROM User",
        "DELETE FROM Category"
    },
    executionPhase = AFTER_TEST_METHOD
)

public @interface SqlUserRepository {
}