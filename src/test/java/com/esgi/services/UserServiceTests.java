package com.esgi.services;

import com.esgi.MedicalNetworkApplication;
import com.esgi.model.User;
import com.esgi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import static org.mockito.Mockito.verify;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = MedicalNetworkApplication.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private User user;

    @Mock
    private UserRepository userRepository;

    @Test
    public void should_register_user() {
        user.setName("FlaeschTest");
        user.setFirstname("ArnaudTest");
        userService.register(user);
        verify(userRepository).save(user);
    }
}