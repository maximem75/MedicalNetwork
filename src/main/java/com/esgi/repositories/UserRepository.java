package com.esgi.repositories;

import com.esgi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
