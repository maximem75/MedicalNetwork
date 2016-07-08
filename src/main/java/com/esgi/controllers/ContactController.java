package com.esgi.controllers;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */

import com.esgi.model.Contact;
import com.esgi.model.User;
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
    public String addContact(@RequestParam String token, @RequestBody Contact contact) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            if (contactRepository.isNewDemand(iduser, contact.getIdcontact().getIduser()) == null) {
                contact.setIduser(new User(iduser));
                contact.setAccepted(false);
                contactRepository.save(contact);
                return (null);
            }
            else {
                return("Contact déjà ajouté !");
            }
        }
        else {
            return (null);
        }
    }
	
	@RequestMapping(value = "/pending", method = RequestMethod.GET)
    public List<Contact> getInvitations(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return(contactRepository.findPendingInvitations(new User(iduser), false));
        }
        return (null);
    }
	
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
    public void acceptRequest(@RequestParam String token, @RequestParam Contact contact) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
			contact.setIduser(new User(iduser));
			contact.setAccepted(true);
            contactRepository.save(iduser, idcontact);
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
