package com.esgi.repositories;

import com.esgi.model.Message;
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
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Message WHERE sender.iduser = :iduser OR receiver.iduser = :iduser")
    void removeMessagesFromUser(@Param("iduser") Long iduser);

    @Query("SELECT M.date, M.content FROM Message M WHERE sender.iduser = :iduser AND receiver.iduser = :idcontact OR sender.iduser = :idcontact AND receiver.iduser = :iduser ORDER BY date DESC")
    List<Message> getConversation(@Param("iduser") Long iduser, @Param("idcontact") Long idcontact);
}