package com.esgi.controllers;


import com.esgi.MedicalNetworkApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created by Yohan on 19/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MedicalNetworkApplication.class)
public class WebControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }

    @After
    public  void downUp(){
        this.mockMvc = null;
    }

    @Test
    public void testAccueil() throws Exception {
        this.mockMvc.perform(get("/accueil"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    public void testConnexion() throws Exception {
    this.mockMvc.perform(get("/connexion"))
            .andExpect(status().isOk())
            .andExpect(view().name("connexion"));
    }

    @Test
    public void testInscription() throws Exception {
        this.mockMvc.perform(get("/inscription"))
                .andExpect(status().isOk())
                .andExpect(view().name("inscription"));
    }

    @Test
    public void testMedecin() throws Exception {
        this.mockMvc.perform(get("/medecin"))
                .andExpect(status().isOk())
                .andExpect(view().name("medecin"));
    }

    @Test
    public void testContact() throws Exception {
        this.mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    @Test
    public void testProfil() throws Exception {
        this.mockMvc.perform(get("/profil"))
                .andExpect(status().isOk())
                .andExpect(view().name("profil"));
    }
}
