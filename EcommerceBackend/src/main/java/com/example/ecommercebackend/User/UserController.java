package com.example.ecommercebackend.User;

import com.example.ecommercebackend.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;

@RestController
public class UserController {
    private static String salt = "alo";
    @Autowired
    UserService userService;

    @GetMapping("/Users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/User")
    public User getUserByEmail(@RequestParam String email) {

        User user = userService.getUserByEmail(email);
        if (user == null) {
            System.out.println("User not found");
        }
        return user;
    }

    @GetMapping("/User/Get")
    public User getUserByUsername(@RequestParam String username) {

        User user = userService.getUserByEmail(username);
        user.setPassword("");
        if (user == null) {
            System.out.println("User not found");
        }
        return user;
    }

    @PostMapping("/User/Authenticate")
    public User AuthenticateUser(@RequestBody User user) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return null;
        }
        User dbUser = userService.getUserByUsername(user.getUsername());
        if (dbUser != null) {
            String inputPassword = Utils.get_SHA_512_SecurePassword(user.getPassword(), salt);
            String dbPassword = dbUser.getPassword();
            if (inputPassword.equals(dbPassword)) {
                return dbUser;
            }

        }
        return null;
    }

    @PostMapping("/User/Update")
    public User updateUser(@RequestBody User user) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return null;
        }
        String originalPassword = user.getPassword();
        String hashedPassword = Utils.get_SHA_512_SecurePassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        userService.updateUser(user);
        user.setPassword(originalPassword);
        return user;
    }

    @PostMapping("/User/Add")
    public boolean createUser(@RequestBody User user) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        if (userService.getUserByUsername(user.getUsername()) == null) {
            user.setPassword(Utils.get_SHA_512_SecurePassword(user.getPassword(), salt));
            userService.createUser(user);
            return true;
        }
        return false;
    }


}
