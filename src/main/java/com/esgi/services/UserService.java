package com.esgi.services;

import com.esgi.model.User;
import com.esgi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getDataUser(Long iduser) {
        return(userRepository.getOne(iduser));
    }

    public List<User> getUsersByCategory(Long idcategory) {
        return(userRepository.findUsersByCategory(idcategory));
    }

    public User login(String login, String password) {
        return (userRepository.findByLoginAndPassword(login, password));
    }

    public List<User> getAllUsers() {
        return(userRepository.findAll());
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public User updateUser(User user) {
        return (userRepository.save(user));
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }
}