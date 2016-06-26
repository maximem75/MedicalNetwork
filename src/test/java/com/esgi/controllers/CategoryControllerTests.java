package com.esgi.controllers;

import com.esgi.repositories.CategoryRepository;
import com.esgi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Yohan on 22/06/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTests {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void should_getCategory()
    {
        when(userRepository.findByToken(eq("toto"), any(Date.class))).thenReturn(2L);
        categoryController.getCategory("toto",1L);
        verify(userRepository).findByToken(eq("toto"),any(Date.class));
        verify(categoryRepository).findOne(1L);
    }

    @Test
    public void should_getCategoryByResearch()
    {
        when(userRepository.findByToken(eq("titi"), any(Date.class))).thenReturn(2L);
        categoryController.getCategoryByResearch("titi","research");
        verify(userRepository).findByToken(eq("titi"),any(Date.class));
        verify(categoryRepository).getCategoriesByResearch(eq("research"));
    }

    @Test
    public void should_getCategoriesList()
    {
        when(userRepository.findByToken(eq("tutu"), any(Date.class))).thenReturn(2L);
        categoryController.getCategoriesList("tutu");
        verify(userRepository).findByToken(eq("tutu"),any(Date.class));
        verify(categoryRepository).findAll();
    }
}
