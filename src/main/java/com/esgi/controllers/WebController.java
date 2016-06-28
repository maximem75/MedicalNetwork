package com.esgi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by molla on 09/06/2016.
 */
@Controller
public class WebController {

    @RequestMapping(value = "/accueil")
    public ModelAndView getAccueil() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        String str = " ";
        mav.addObject("message", str);
        return mav;
    }

    @RequestMapping(value = "/connexion")
    public ModelAndView getConnexion() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("connexion");
        String str = " ";
        mav.addObject("message", str);
        return mav;
    }

    @RequestMapping(value = "/inscription")
    public ModelAndView getInscription() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inscription");
        String str = " ";
        mav.addObject("message", str);
        return mav;
    }

    @RequestMapping(value = "/categorie")
    public ModelAndView getCategorie() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("categorie");
        String str = " ";
        mav.addObject("message", str);
        return mav;
    }

    @RequestMapping(value = "/medecin")
    public ModelAndView getMedecin() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("medecin");
        String str = " ";
        mav.addObject("message", str);
        return mav;
    }

    @RequestMapping(value = "/contact")
    public ModelAndView getContact() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("contact");
        String str = " ";
        mav.addObject("message", str);
        return mav;
    }

    @RequestMapping(value = "/profil")
    public ModelAndView getProfil() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("profil");
        String str = " ";
        mav.addObject("message", str);
        return mav;
    }

    @RequestMapping(value = "/userList")
    public ModelAndView getUserList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("userList");
        String str = " ";
        mav.addObject("message", str);
        return mav;
    }

}
