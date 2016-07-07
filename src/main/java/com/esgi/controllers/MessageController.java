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

    @RequestMapping(value = "/getEncryptionKey",method = RequestMethod.GET)
    public String getEncryptionKey(@RequestParam String token, @RequestParam Long idcontact) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            List<String> login = userRepository.findLoginByIduser(iduser, idcontact);
            String logins = login.get(0)+login.get(1);
            return (scramble(logins));
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
            return (messageRepository.getConversation(iduser, idcontact).subList(0,3));
        }
        return (null);
    }

    public String scramble(String logins) {
        char loginsArray[] = logins.toCharArray();
        for(int i = 0; i<loginsArray.length-1; i++) {
            int j = ((i+26)*50)% (loginsArray.length-1);
            char temp = loginsArray[i]; loginsArray[i] = loginsArray[j];  loginsArray[j] = temp;
        }
        return (new String(loginsArray));
    }
}
