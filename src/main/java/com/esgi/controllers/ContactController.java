package com.esgi.controllers;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */

import com.esgi.model.Contact;
import com.esgi.services.ContactService;
import com.esgi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addContact(@RequestParam String token, @RequestBody Contact contact) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            contact.setIduser(iduser);
            contact.setAccepted(false);
            contactService.addContact(contact);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void removeContact(@RequestParam String token, @RequestParam Long idcontact) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            contactService.removeContact(iduser, idcontact);
        }
    }
}
