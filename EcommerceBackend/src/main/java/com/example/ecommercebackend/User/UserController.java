package com.example.ecommercebackend.User;

import com.example.ecommercebackend.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/User")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {

        User user = userService.getUserByEmail(email);
        if (user == null) {

        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/User/Get")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {

        User user = userService.getUserByEmail(username);
        user.setPassword("");
        if (user == null) {
            System.out.println("User not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/User/Authenticate")
    public ResponseEntity<User> AuthenticateUser(@RequestBody User user) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return null;
        }
        User dbUser = userService.getUserByUsername(user.getUsername());
        if (dbUser != null) {
            String inputPassword = Utils.get_SHA_512_SecurePassword(user.getPassword(), salt);
            String dbPassword = dbUser.getPassword();
            if (inputPassword.equals(dbPassword)) {
                dbUser.setPassword("");
                return new ResponseEntity<>(dbUser, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(dbUser, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/User/Update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        String originalPassword = user.getPassword();
        String hashedPassword = Utils.get_SHA_512_SecurePassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        userService.updateUser(user);
        user.setPassword(originalPassword);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/User/Add")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        if (userService.getUserByUsername(user.getUsername()) == null) {
            user.setPassword(Utils.get_SHA_512_SecurePassword(user.getPassword(), salt));
            userService.createUser(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.CONFLICT);
    }


}
