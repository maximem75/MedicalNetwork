package com.esgi.controllers;

import com.esgi.model.User;
import com.esgi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public User login(@RequestParam String login, @RequestParam String password) {
        return (userService.login(login, password));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void registration(@RequestBody User user) {
        userService.register(user);
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