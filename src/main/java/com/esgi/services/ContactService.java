package com.esgi.services;

import com.esgi.model.Contact;
import com.esgi.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void removeContact(Long iduser, Long idcontact) {
        contactRepository.removeContact(iduser, idcontact);
    }
}