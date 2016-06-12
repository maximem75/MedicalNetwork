package com.esgi.controllers;


import com.esgi.model.Message;
import com.esgi.services.MessageService;
import com.esgi.services.UserService;
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

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> getConversation(@RequestParam String token, @RequestParam Long idcontact) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            return (messageService.getConversation(iduser, idcontact));
        }
        return (null);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void addMessage(@RequestParam String token, @RequestBody Message message) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            messageService.addMessage(message);
        }
    }

    @RequestMapping(value = "/lastMessages", method = RequestMethod.GET)
    public List<Message> getLastMessages(@RequestParam String token, @RequestParam Long idcontact) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            return (messageService.getLastMessages(iduser, idcontact));
        }
        return (null);
    }

    @RequestMapping(value = "/lastConversations", method = RequestMethod.GET)
    public Message getLastConversations(@RequestParam String token) {
        Long iduser = userService.getIdFromToken(token);
        /*if (iduser != null) {
            return (messageService.getLastConversations(iduser));
        }*/
        return (null);
    }
}
