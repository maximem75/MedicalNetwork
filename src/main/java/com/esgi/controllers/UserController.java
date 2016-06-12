package com.esgi.controllers;

import com.esgi.model.Category;
import com.esgi.model.User;
import com.esgi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public User getUser(@RequestParam String token) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            return (userService.getDataUser(iduser));
        }
        return (null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getUsersByCategory(@RequestParam String token, @RequestParam Long idcategory) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            return (userService.getUsersByCategory(new Category(idcategory)));
        }
        return (null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam String login, @RequestParam String password) {
        return (userService.login(login, password));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(@RequestParam String token) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            User user = userService.getDataUser(iduser);
            user.setToken(null);
            user.setTokenExpirationDate(null);
            userService.updateUser(user);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void registration(@RequestBody User user) {
        userService.register(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateData(@RequestBody User user, @RequestParam String token) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            user.setIduser(iduser);
            userService.updateUser(user);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteUser(@RequestParam String token) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            userService.removeUser(iduser);
        }
    }

    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    public List<User> getInvitations(@RequestParam String token) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            return(userService.getPendingInvitations(iduser));
        }
        return (null);
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public List<User> getContacts(@RequestParam String token) {
        Long iduser = userService.getIdFromToken(token);
        if (iduser != null) {
            return(userService.getContacts(iduser));
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