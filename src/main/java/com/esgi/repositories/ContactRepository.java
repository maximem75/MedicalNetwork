package com.esgi.repositories;

import com.esgi.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("DELETE FROM Contact WHERE (iduser = :iduser AND idcontact = :idcontact) AND (iduser = :idcontact AND idcontact = :iduser)")
    void removeContact(@Param("iduser") Long iduser, @Param("idcontact") Long idcontact);
}
