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


    @Query("SELECT M.date, M.content FROM Message M WHERE sender.iduser = :iduser AND receiver.iduser = :idcontact OR sender.iduser = :idcontact AND receiver.iduser = :iduser ORDER BY date DESC")
    List<Message> getLastMessages(@Param("iduser") Long iduser, @Param("idcontact") Long idcontact);

    /*@Query("FROM Message M, User U WHERE sender = :iduser OR sender = :iduser ORDER BY date DESC")
    List<Message> getLastConversations(@Param("iduser") Long iduser);*/

    @Query("SELECT M.date, M.content FROM Message M WHERE receiver.iduser = :iduser AND sender.iduser  = :idcontact OR sender.iduser  = :iduser AND receiver.iduser  = :idcontact ORDER BY date DESC")
    List<Message> getConversation(@Param("iduser") Long iduser, @Param("idcontact") Long idcontact);
}