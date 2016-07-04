package com.esgi.controllers;

import com.esgi.model.Category;
import com.esgi.model.User;
import com.esgi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.Console;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //Affiche données d'un utilisateur
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public User getUser(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return (userRepository.findOne(iduser));
        }
        return (null);
    }

    //recherche lsite utilisateur par categorie
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getUsersByCategory(@RequestParam String token, @RequestParam Long idcategory) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return (userRepository.findUsersByCategory(new Category(idcategory)));
        }
        return (null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam String login, @RequestParam String password) {
        User user = userRepository.findByLoginAndPassword(login, password);
        if (user != null) {
            user.setToken(UUID.randomUUID().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);
            user.setTokenExpirationDate(calendar.getTime());
            userRepository.save(user);
            return(user);
        }
        return (null);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            User user = userRepository.findOne(iduser);
            user.setToken(null);
            user.setTokenExpirationDate(null);
            userRepository.save(user);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody User registration(@RequestBody User user) {

        user.setBirthday(new Date());
        userRepository.save(user);
        return user;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateData(@RequestBody User user, @RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            user.setIduser(iduser);
            userRepository.save(user);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteUser(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            userRepository.delete(iduser);
        }
    }

    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    public List<User> getInvitations(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return(userRepository.findPendingInvitations(iduser, false));
        }
        return (null);
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public List<User> getContacts(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return(userRepository.findContacts(iduser, true));
        }
        return (null);
    }

    /**
     * Vérifie si les paramètres saisis lors de l'inscription sont bien valides (mot de passe assez long, champs obligatoires renseignés, etc.)
     * @param user
     * @return Devrait sans doute renvoyer une Exception
     */
    private boolean checkRegistration(User user) {
        if (user.getLogin() == null ||
            user.getPassword() == null ||
            user.getName() == null ||
            user.getFirstname() == null ||
            user.getBirthday() == null ||
            user.getPhone() == null ||
            user.getEmail() == null) {
            return (false);
        }
        return(true);
    }
}