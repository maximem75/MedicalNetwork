package com.esgi.controllers;

import com.esgi.model.Message;
import com.esgi.model.User;
import com.esgi.repositories.MessageRepository;
import com.esgi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        when(userRepository.findByToken("toto", new Date())).thenReturn(1L);
        when(messageRepository.getConversation(1L, 2L)).thenReturn(new ArrayList<Message>());
        messageController.getConversation("toto",2L);
        verify(userRepository).findByToken(eq("toto"), any(Date.class));
    }

    @Test
    public void should_addMessage()
    {
        Message message =  new Message();
        message.setSender(new User());
        when(userRepository.findByToken(eq("titi") , any(Date.class))).thenReturn(2L);
        messageController.addMessage("titi" , message);
        verify(userRepository).findByToken(eq("titi"),any(Date.class));
        verify(messageRepository).save(any(Message.class));

    }

    @Test
    public void should_getLastMessages()
    {
        when(userRepository.findByToken(eq("tutu") , any(Date.class))).thenReturn(2L);
        when(messageRepository.getConversation(2L,1L)).thenReturn(new ArrayList<>());
        messageController.getLastMessages("tutu",1L);
        verify(userRepository).findByToken(eq("tutu"),any(Date.class));
        verify(messageRepository).getConversation(2L,1L);
    }

    @Test
    public void should_getEncryptionKey()
    {
        List<String> array = new ArrayList<String>();
        array.add("titi");
        array.add("toto");
        when(userRepository.findByToken(eq("titi") , any(Date.class))).thenReturn(2L);
        when(userRepository.findLoginByIduser(2L,1L)).thenReturn(array);
        messageController.getEncryptionKey("titi",1L);
        verify(userRepository).findLoginByIduser(2L, 1L);
    }

    @Test
    public void should_getLogin()
    {
        when(userRepository.findByToken(eq("titi") , any(Date.class))).thenReturn(2L);
        when(userRepository.findNameByIduser(2L)).thenReturn(new Object());
        messageController.getLogin("titi");
        //verify(userRepository).findNameByIduser(2L);

    }
}
