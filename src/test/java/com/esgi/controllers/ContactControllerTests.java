package com.esgi.controllers;

import com.esgi.model.Contact;
import com.esgi.repositories.ContactRepository;
import com.esgi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

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
        Mockito.when(userRepository.findByToken(eq("toto") , any(Date.class))).thenReturn(2L);
        contactController.addContact("toto",new Contact());
        Mockito.verify(userRepository).findByToken(eq("toto"),any(Date.class));
    }

    @Test
    public void should_removeContact()
    {
        Mockito.when(userRepository.findByToken(eq("titi") , any(Date.class))).thenReturn(2L);
        contactController.removeContact("titi",1L);
        Mockito.verify(userRepository).findByToken(eq("titi"), any(Date.class));
       // Mockito.verify(contactRepository).removeContact(2L, 1L);
    }
}
