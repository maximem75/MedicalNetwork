package com.esgi.controllers;

import com.esgi.model.Message;
import com.esgi.repositories.MessageRepository;
import com.esgi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> getConversation(@RequestParam String token, @RequestParam Long idcontact) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return (messageRepository.getConversation(iduser, idcontact));
        }
        return (null);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void addMessage(@RequestParam String token, @RequestBody Message message) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            messageRepository.save(message);
        }
    }

    @RequestMapping(value = "/lastMessages", method = RequestMethod.GET)
    public List<Message> getLastMessages(@RequestParam String token, @RequestParam Long idcontact) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            List<Message> conversation = messageRepository.getConversation(iduser, idcontact);
            if(conversation.size() > 3) {
                conversation = conversation.subList(0,3);
            }
            return (conversation);
        }
        return (null);
    }
}
