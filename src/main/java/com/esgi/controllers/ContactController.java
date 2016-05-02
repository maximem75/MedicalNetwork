package com.esgi.controllers;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */

import com.esgi.model.Contact;
import com.esgi.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void removeContact(@RequestParam Long iduser, @RequestParam Long idcontact) {
        contactService.removeContact(iduser, idcontact);
    }
}
