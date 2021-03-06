package com.esgi.repositories;

import com.esgi.model.Contact;
import com.esgi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT idrelation FROM Contact WHERE (iduser.iduser = :iduser AND idcontact.iduser = :idcontact) OR (idcontact.iduser = :iduser AND iduser.iduser = :idcontact)")
    Long isNewDemand(@Param("iduser") Long iduser, @Param("idcontact") Long idcontact);

	@Query("SELECT idrelation, accepted, message, iduser.iduser, iduser.name, iduser.firstname, idcontact.iduser FROM Contact WHERE idcontact= :iduser AND accepted = :accepted")
    List<Contact> findPendingInvitations(@Param("iduser") User iduser, @Param("accepted") boolean accepted);

    @Modifying
    @Transactional
    @Query("DELETE FROM Contact WHERE iduser.iduser = :iduser OR idcontact.iduser = :iduser")
    void removeUser(@Param("iduser") Long iduser);

    @Modifying
    @Transactional
    @Query("DELETE FROM Contact WHERE iduser = :iduser AND idcontact = :idcontact OR iduser = :idcontact AND idcontact = :iduser")
    void removeContact(@Param("iduser") User iduser, @Param("idcontact") User contact);
}
