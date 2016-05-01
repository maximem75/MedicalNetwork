package com.esgi.controllers;

import com.esgi.model.Category;
import com.esgi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Arnaud on 30/04/2016.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public Category getCategory(@RequestParam Long idcategory) {
        return(categoryService.getCategory(idcategory));
    }

    @RequestMapping(value = "/research", method = RequestMethod.GET)
    public List<Category> getCategoryByResearch(@RequestParam String research) {
        return(categoryService.getCategoriesByResearch(research));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getCategoriesList() {
        return(categoryService.getCategoriesList());
    }
}
