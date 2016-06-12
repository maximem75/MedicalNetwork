package com.esgi.controllers;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */

import com.esgi.model.Contact;
import com.esgi.repositories.ContactRepository;
import com.esgi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addContact(@RequestParam String token, @RequestBody Contact contact) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            contact.setIduser(iduser);
            contact.setAccepted(false);
            contactRepository.save(contact);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void removeContact(@RequestParam String token, @RequestParam Long idcontact) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            contactRepository.removeContact(iduser, idcontact);
        }
    }
}
