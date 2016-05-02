package com.esgi.repositories;

import com.esgi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT date FROM Message WHERE (sender = :iduser AND receiver = :idcontact) OR (sender = :idcontact AND receiver = :iduser) ORDER BY DATE DESC")
    List<Message> getLastMessageSent(@Param("iduser") Long iduser, @Param("idcontact") Long idcontact);

    @Query("FROM Message WHERE (receiver = :iduser AND sender = :idcontact) OR (sender = :iduser AND receiver = :idcontact)")
    List<Message> getConversation(@Param("iduser") Long iduser, @Param("idcontact") Long idcontact);
}