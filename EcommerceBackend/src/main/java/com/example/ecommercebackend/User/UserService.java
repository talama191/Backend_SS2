package com.example.ecommercebackend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(u -> users.add(u));
        return users;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        return user;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        return user;
    }

    public User updateUser(User user) {
        if (userRepository.existsById(user.getUser_id())) {
            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    public User createUser(User user) {

        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
