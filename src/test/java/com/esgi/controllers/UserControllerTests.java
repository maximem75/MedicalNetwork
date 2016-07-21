package com.esgi.controllers;

import com.esgi.model.Category;
import com.esgi.model.User;
import com.esgi.repositories.ContactRepository;
import com.esgi.repositories.MessageRepository;
import com.esgi.repositories.UserRepository;
import com.esgi.utils.UserUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {


    @Mock
    private UserRepository userRepository;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void should_getUser()
    {
        when(userRepository.findByToken(eq("toto") , any(Date.class))).thenReturn(2L);
        userController.getUser("toto");
        verify(userRepository).findByToken(eq("toto"),any(Date.class));
        verify(userRepository).getDataUser(2L);
    }

    @Test
    public void should_getUsersByCategory()
    {
        when(userRepository.findByToken(eq("toto") , any(Date.class))).thenReturn(2L);
        userController.getUsersByCategory("toto",1L);
        verify(userRepository).findByToken(eq("toto"),any(Date.class));
        verify(userRepository).findUsersByCategory(any(Category.class));
    }

    @Test
    public void should_login()
    {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        when(userRepository.findByLoginAndPassword(eq("login"),eq(UserUtils.encryptPassword("password")))).thenReturn(user);
        userController.login(user.getLogin(),user.getPassword());
        verify(userRepository).findByLoginAndPassword(eq(user.getLogin()),eq(UserUtils.encryptPassword(user.getPassword())));
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void should_logout() {

        when(userRepository.findByToken(eq("titi"),any(Date.class))).thenReturn(1L);
        when(userRepository.findOne(1L)).thenReturn(new User());
        userController.logout("titi");
        verify(userRepository).findByToken(eq("titi"),any(Date.class));
        verify(userRepository).findOne(1L);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void should_registration()
    {
        User user = new User();
        user.setEmail("toto@gmail.com");
        user.setLogin("toto94zou");
        user.setPassword("proutprout");
        when(userRepository.findByEmailOrLogin(user.getEmail(),user.getLogin())).thenReturn(new ArrayList<>());
        userController.registration(user);
        verify(userRepository).save(user);
    }

    @Test
    public void should_deleteUser()
    {
        when(userRepository.findByToken(eq("token"),any(Date.class))).thenReturn(1L);
        userController.deleteUser("token");
        verify(userRepository).findByToken(eq("token"),any(Date.class));
        verify(userRepository).delete(1L);
    }

    @Test
    public void should_getContacts()
    {
        when(userRepository.findByToken(eq("token"),any(Date.class))).thenReturn(1L);
        userController.getContacts("token");
        verify(userRepository).findByToken(eq("token"),any(Date.class));
        verify(userRepository).findContacts(any(User.class),eq(true));

    }
}
