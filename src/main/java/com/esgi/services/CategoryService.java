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

    public Category getCategory(Long idcategory) {
        return(categoryRepository.getOne(idcategory));
    }

    public List<Category> getCategoriesByResearch(String research) {
        return(categoryRepository.getCategoriesByResearch(research));
    }

    public List<Category> getCategoriesList() {
        return(categoryRepository.findAll());
    }
}