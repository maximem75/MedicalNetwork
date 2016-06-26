package com.esgi.controllers;

import com.esgi.model.Category;
import com.esgi.model.User;
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
import static org.mockito.Mockito.verify;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Test
    public void should_getUser()
    {
        Mockito.when(userRepository.findByToken(eq("toto") , any(Date.class))).thenReturn(2L);
        userController.getUser("toto");
        verify(userRepository).findByToken(eq("toto"),any(Date.class));
        verify(userRepository).findOne(2L);
    }

    @Test
    public void should_getUsersByCategory()
    {
        Mockito.when(userRepository.findByToken(eq("toto") , any(Date.class))).thenReturn(2L);
        userController.getUsersByCategory("toto",1L);
        verify(userRepository).findByToken(eq("toto"),any(Date.class));
        verify(userRepository).findUsersByCategory(any(Category.class));
    }

    @Test
    public void should_login()
    {
        Mockito.when(userRepository.findByLoginAndPassword(eq("login"),eq("password"))).thenReturn(new User());
        userController.login("login","password");
        verify(userRepository).findByLoginAndPassword(eq("login"),eq("password"));
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void should_logout() {

        Mockito.when(userRepository.findByToken(eq("titi"),any(Date.class))).thenReturn(1L);
        Mockito.when(userRepository.findOne(1L)).thenReturn(new User());
        userController.logout("titi");
        verify(userRepository).findByToken(eq("titi"),any(Date.class));
        verify(userRepository).findOne(1L);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void should_registration()
    {
        userController.registration(new User());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void should_updateData()
    {
        Mockito.when(userRepository.findByToken(eq("token"),any(Date.class))).thenReturn(1L);
        userController.updateData(new User(),"token");
        verify(userRepository).findByToken(eq("token"),any(Date.class));
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void should_deleteUser()
    {
        Mockito.when(userRepository.findByToken(eq("token"),any(Date.class))).thenReturn(1L);
        userController.deleteUser("token");
        verify(userRepository).findByToken(eq("token"),any(Date.class));
        verify(userRepository).delete(1L);
    }

    @Test
    public void getInvitations()
    {
        Mockito.when(userRepository.findByToken(eq("token"),any(Date.class))).thenReturn(1L);
        userController.getInvitations("token");
        verify(userRepository).findByToken(eq("token"),any(Date.class));
        verify(userRepository).findPendingInvitations(1l, false);
    }

    @Test
    public void should_getContacts()
    {
        Mockito.when(userRepository.findByToken(eq("token"),any(Date.class))).thenReturn(1L);
        userController.getContacts("token");
        verify(userRepository).findByToken(eq("token"),any(Date.class));
        verify(userRepository).findContacts(1L,true);
    }
}
