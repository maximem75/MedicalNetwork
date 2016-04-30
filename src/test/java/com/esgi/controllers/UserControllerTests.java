package com.esgi.controllers;

import com.esgi.model.User;
import com.esgi.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private User user;

    @Mock
    private UserService userService;

    @Test
    public void should_register_user() {
        userController.registration(user);
        verify(userService).register(user);
    }
}
