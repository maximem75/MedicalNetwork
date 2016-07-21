package com.esgi.repositories;

import com.esgi.MedicalNetworkApplication;
import com.esgi.repositories.datasets.SqlCategoryRepository;
import com.esgi.repositories.datasets.SqlUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * Created by Arnaud on 28/04/2016.
 */
@ActiveProfiles("tests")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MedicalNetworkApplication.class)
@WebAppConfiguration
@SqlUserRepository
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_find_all_users() {
        assertThat(userRepository.findAll(), hasSize(10));
    }



}