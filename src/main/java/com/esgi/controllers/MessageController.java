package com.esgi.controllers;


import com.esgi.model.Message;
import com.esgi.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> getConversation(@RequestParam Long iduser, @RequestParam Long idcontact) {
        return(messageService.getConversation(iduser, idcontact));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
    }

    @RequestMapping(value = "/last", method = RequestMethod.GET)
    public Message getLastMessageSent(@RequestParam Long iduser, @RequestParam Long idcontact) {
        return(messageService.getLastMessage(iduser, idcontact));
    }

}
