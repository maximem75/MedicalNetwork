package com.esgi.controllers;

import com.esgi.MedicalNetworkApplication;
import com.esgi.exceptions.TokenException;
import com.esgi.model.Category;
import com.esgi.repositories.datasets.SqlCategoryRepository;
import com.esgi.repositories.datasets.SqlUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by Arnaud on 21/07/2016.
 */
@ActiveProfiles("tests")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MedicalNetworkApplication.class)
@WebAppConfiguration
@SqlUserRepository
public class CategoryControllerJUnitTests {

    @Autowired
    private CategoryController categoryController;

    @Test
    public void should_get_all_categories() {
        assertThat(categoryController.getCategoriesList(), hasSize(5));
    }

    @Test
    public void should_get_one_category() {
        assertEquals(categoryController.getCategory("token", 1L).getNameCategory(), "Ophtalmologiste");
    }
}