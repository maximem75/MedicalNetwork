package com.esgi.services;

import com.esgi.model.Message;
import com.esgi.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getLastMessages(Long iduser, Long idcontact) {
        return(messageRepository.getLastMessages(iduser, idcontact).subList(0, 3));
    }

    /*public List<Message> getLastConversations(Long iduser) {
        return(messageRepository.getLastConversations(iduser).get(5));
    }*/

    public List<Message> getConversation(Long iduser, Long idcontact) {
        return(messageRepository.getConversation(iduser, idcontact));
    }
}