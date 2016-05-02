package com.esgi.services;

import com.esgi.model.Message;
import com.esgi.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Message getLastMessage(Long iduser, Long idcontact) {
        return(messageRepository.getLastMessageSent(iduser, idcontact).get(0));
    }

    public List<Message> getConversation(Long iduser, Long idcontact) {
        return(messageRepository.getConversation(iduser, idcontact));
    }
}