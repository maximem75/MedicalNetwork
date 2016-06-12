package com.esgi.services;

import com.esgi.model.Category;
import com.esgi.model.User;
import com.esgi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long getIdFromToken(String token) {
        return (userRepository.findByToken(token, new Date()));
    }

    public User getDataUser(Long iduser) {
        return(userRepository.findOne(iduser));
    }

    public List<User> getUsersByCategory(Category idcategory) {
        return(userRepository.findUsersByCategory(idcategory));
    }

    public List<User> getPendingInvitations(Long iduser) {
        return(userRepository.findPendingInvitations(iduser, false));
    }

    public List<User> getContacts(Long iduser) {
        return(userRepository.findContacts(iduser, true));
    }

    public User login(String login, String password) {
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

    public void register(User user) {
        userRepository.save(user);
    }

    public User updateUser(User user) {
        return (userRepository.save(user));
    }

    public void removeUser(Long iduser) {
        userRepository.delete(iduser);
    }
}