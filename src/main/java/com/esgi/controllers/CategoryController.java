package com.esgi.controllers;

import com.esgi.model.Category;
import com.esgi.repositories.CategoryRepository;
import com.esgi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by Arnaud on 30/04/2016.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Category getCategory(@RequestParam String token, @RequestParam Long idcategory) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return(categoryRepository.findOne(idcategory));
        }
        return (null);
    }

    @RequestMapping(value = "/research", method = RequestMethod.GET)
    public List<Category> getCategoryByResearch(@RequestParam String token, @RequestParam String research) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return(categoryRepository.getCategoriesByResearch(research));
        }
        return (null);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getCategoriesList() {
        return(categoryRepository.findAll());
    }
}
