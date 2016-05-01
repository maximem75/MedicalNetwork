package com.esgi.repositories;

import com.esgi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Arnaud on 30/04/2016.
 */
public interface MessageRepository extends JpaRepository<Message, Long>{

}