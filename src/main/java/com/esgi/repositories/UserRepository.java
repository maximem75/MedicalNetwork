package com.esgi.repositories;

import com.esgi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    List<User> findUsersByCategory(@Param("idcategory") Long idcategory);

    @Query("SELECT U.iduser, U.name, U.firstname, C.message FROM User U, Contact C WHERE U.iduser IN (SELECT iduser FROM Contact WHERE idcontact = :iduser AND accepted = :accepted)")
    List<User> findPendingInvitations(@Param("iduser") Long iduser, @Param("accepted") boolean accepted);

    @Query("SELECT U.iduser, U.name, U.firstname FROM User U WHERE iduser IN (SELECT idcontact FROM Contact WHERE iduser = :iduser AND accepted = :accepted) OR iduser IN (SELECT iduser FROM Contact WHERE idcontact = :iduser AND accepted = :accepted)")
    List<User> findContacts(@Param("iduser") Long iduser, @Param("accepted") boolean accepted);
}