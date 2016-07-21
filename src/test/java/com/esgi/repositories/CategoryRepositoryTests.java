package com.esgi.repositories;

import com.esgi.MedicalNetworkApplication;
import com.esgi.model.Category;
import com.esgi.repositories.datasets.SqlCategoryRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;

/**
 * Created by Arnaud on 30/04/2016.
 */
@ActiveProfiles("tests")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MedicalNetworkApplication.class)
@WebAppConfiguration
@SqlCategoryRepository
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void should_get_one_category() {
        assertEquals(categoryRepository.findOne(1L).getNameCategory(), "Ophtalmologiste");
    }

    @Test
    public void should_not_find_one_category() {
        assertEquals(categoryRepository.findOne(6L), null);
    }

    @Test
    public void should_get_all_categories() {
        assertThat(categoryRepository.findAll(), hasSize(5));
    }

    @Test
    public void should_find_category_by_research() {
        assertThat(categoryRepository.getCategoriesByResearch("Kinési"), hasItem(Matchers.<Category>hasProperty("nameCategory", equalTo("Kinésithérapeute"))));
    }

    @Test
    public void should_not_find_anything() {
        assertThat(categoryRepository.getCategoriesByResearch("INVALID_RESEARCH").isEmpty(), is(true));
    }
}
