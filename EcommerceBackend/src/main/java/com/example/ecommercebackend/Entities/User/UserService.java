package com.example.ecommercebackend.Entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

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

    public int getUserRoleByUserID(int id) {
        return userRepository.getRoleByUserID(id);
    }

    public void setUserRole(int user_id, int role_id) {
         userRepository.setUserRole(user_id, role_id);
    }

    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        return user;
    }
    public User getUserByUserID(int user_id){
        return userRepository.getUserByUserID(user_id);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        return user;
    }
}
