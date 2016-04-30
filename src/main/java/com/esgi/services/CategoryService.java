package com.esgi.services;

import com.esgi.model.Category;
import com.esgi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Arnaud on 30/04/2016.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategoriesList() {
        return (categoryRepository.findAll());
    }

    public Category updateCategory(Category category) {
        return(categoryRepository.save(category));
    }

    public void removeCategory(Category category) {
        categoryRepository.delete(category);
    }
}