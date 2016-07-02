package com.esgi.controllers;

import com.esgi.model.Category;
import com.esgi.model.Message;
import com.esgi.model.User;
import com.esgi.repositories.ContactRepository;
import com.esgi.repositories.MessageRepository;
import com.esgi.repositories.UserRepository;
import com.esgi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Object getUser(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return (userRepository.getDataUser(iduser));
        }
        return (null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getUsersByCategory(@RequestParam String token, @RequestParam Long idcategory) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return (userRepository.findUsersByCategory(new Category(idcategory)));
        }
        return (null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        password = UserUtils.encryptPassword(password);
        User user = userRepository.findByLoginAndPassword(login, password);
        if (user != null) {
            user.setToken(UUID.randomUUID().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);
            user.setTokenExpirationDate(calendar.getTime());
            userRepository.save(user);
            return(user.getToken());
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

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void registration(@RequestBody User user) {
        if (userRepository.findByEmailOrLogin(user.getEmail(), user.getLogin()).isEmpty()) {
            user.setPassword(UserUtils.encryptPassword(user.getPassword()));
            userRepository.save(user);
        }
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
            messageRepository.removeMessagesFromUser(iduser);
            contactRepository.removeUser(iduser);
            userRepository.delete(iduser);
        }
    }

    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    public List<User> getInvitations(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return(userRepository.findPendingInvitations(new User(iduser), false));
        }
        return (null);
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public List<User> getContacts(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            return(userRepository.findContacts(new User(iduser), true));
        }
        return (null);
    }

    @RequestMapping(value = "/lastConversations", method = RequestMethod.GET)
    public List<Message> getLastConversations(@RequestParam String token) {
        Long iduser = userRepository.findByToken(token, new Date());
        if (iduser != null) {
            ArrayList<Message> lastConversations = new ArrayList<>();
            for (User contact : userRepository.findContacts(new User(iduser), true)) {
                lastConversations.add(messageRepository.getConversation(iduser, contact.getIduser()).get(0));
            }
            return (lastConversations);
        }
        return (null);
    }

    /**
     * Vérifie si les paramètres saisis lors de l'inscription sont bien valides (mot de passe assez long, champs obligatoires renseignés, etc.)
     * @param user
     * @return Devrait sans doute renvoyer une Exception
     */
    private boolean checkDataUser(User user) {
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