package com.esgi.controllers;

import com.esgi.model.Contact;
import com.esgi.model.User;
import com.esgi.repositories.ContactRepository;
import com.esgi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Yohan on 26/06/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTests {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ContactController contactController;

    @Test
    public void should_addContact()
    {
        Contact contact = new Contact();
        User user = new User();
        user.setIduser(2L);
        contact.setIdcontact(user);

        when(userRepository.findByToken(eq("toto") , any(Date.class))).thenReturn(2L);
        when(contactRepository.isNewDemand(2L, contact.getIdcontact().getIduser())).thenReturn(2L);
        contactController.addContact("toto",contact);
        verify(userRepository).findByToken(eq("toto"),any(Date.class));
    }

    @Test
    public void should_removeContact()
    {
        when(userRepository.findByToken(eq("titi") , any(Date.class))).thenReturn(2L);
        contactController.removeContact("titi",1L);
        verify(userRepository).findByToken(eq("titi"), any(Date.class));
       // Mockito.verify(contactRepository).removeContact(2L, 1L);
    }

    @Test
    public void should_getInvitations()
    {
        when(userRepository.findByToken(eq("titi") , any(Date.class))).thenReturn(2L);
        contactController.getInvitations("titi");
        verify(contactRepository).findPendingInvitations(any(User.class),eq(false));
    }

    @Test
    public void should_acceptRequest()
    {
        Contact contact = new Contact();
        when(userRepository.findByToken(eq("titi") , any(Date.class))).thenReturn(2L);
        contactController.acceptRequest("titi",contact);
        verify(contactRepository).save(contact);
    }
}
