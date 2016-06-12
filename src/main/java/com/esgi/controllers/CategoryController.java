package com.esgi.controllers;

import com.esgi.model.Category;
import com.esgi.services.CategoryService;
import com.esgi.services.UserService;
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

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Category getCategory(@RequestParam String token, @RequestParam Long idcategory) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            return(categoryService.getCategory(idcategory));
        }
        return (null);
    }

    @RequestMapping(value = "/research", method = RequestMethod.GET)
    public List<Category> getCategoryByResearch(@RequestParam String token, @RequestParam String research) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            return(categoryService.getCategoriesByResearch(research));
        }
        return (null);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getCategoriesList(@RequestParam String token) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            return(categoryService.getCategoriesList());
        }
        return (null);
    }
}
