package com.esgi.controllers;

import com.esgi.model.Message;
import com.esgi.repositories.MessageRepository;
import com.esgi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

/**
 * Created by Yohan on 24/06/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageControllerTests {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MessageController messageController;

    @Test
    public void should_getConversation()
    {
        Mockito.when(userRepository.findByToken("toto", new Date())).thenReturn(1L);
        Mockito.when(messageRepository.getConversation(1L, 2L)).thenReturn(new ArrayList<Message>());
        messageController.getConversation("toto",2L);
        Mockito.verify(userRepository).findByToken(eq("toto"), any(Date.class));
    }

    @Test
    public void should_addMessage()
    {
        messageController.addMessage("titi" , new Message());
        Mockito.verify(userRepository).findByToken(eq("titi"),any(Date.class));
        Mockito.verify(messageRepository).save(any(Message.class));

    }

    @Test
    public void should_getLastMessages()
    {
        Mockito.when(userRepository.findByToken(eq("tutu") , any(Date.class))).thenReturn(2L);
        Mockito.when(messageRepository.getConversation(2L,1L)).thenReturn(new ArrayList<>());
        messageController.getLastMessages("tutu",1L);
        Mockito.verify(userRepository).findByToken(eq("tutu"),any(Date.class));
        Mockito.verify(messageRepository).getConversation(2L,1L);
    }
}
