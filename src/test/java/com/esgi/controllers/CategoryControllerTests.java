package com.esgi.controllers;

import com.esgi.model.Category;
import com.esgi.repositories.CategoryRepository;
import com.esgi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Yohan on 22/06/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTests {

    @Mock
    private Category category;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void should_getCategory()
    {
        category.setIdcategory(1L);
        category.setNameCategory("Doctor");
        categoryController.getCategory("Doctor",1L);
        verify(categoryRepository).findOne(1L);
    }
}
