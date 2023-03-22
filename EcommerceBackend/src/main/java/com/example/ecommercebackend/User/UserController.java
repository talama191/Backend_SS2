package com.example.ecommercebackend.User;

import com.example.ecommercebackend.Security.AuthResponse;
import com.example.ecommercebackend.Security.JwtTokenProvider;
import com.example.ecommercebackend.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @PostMapping("/UserAuth/Authenticate")
    public ResponseEntity<?> AuthenticateUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(), user.getPassword())
            );

//            User newUser = (User) authentication.getPrincipal();
            String accessToken = jwtTokenProvider.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

//        String userPassword = Utils.get_SHA_512_SecurePassword(user.getPassword(), salt);
//        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//        Authentication authentication = authenticationManager.authenticate(token);
//
//        if (authentication.isAuthenticated()) {
//            return new ResponseEntity<>(token, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(token, HttpStatus.BAD_REQUEST);

//        if (user.getPassword()==null) {
//            return null;
//        }
//        User dbUser = userService.getUserByUsername(user.getUsername());
//        if (dbUser != null) {
//            String inputPassword = Utils.get_SHA_512_SecurePassword(user.getPassword(), salt);
//            String dbPassword = dbUser.getPassword();
//            if (inputPassword.equals(dbPassword)) {
//                dbUser.setPassword("");
//                return new ResponseEntity<>(dbUser, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/User/Add")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if ( user.getPassword()==null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (userService.getUserByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.createUser(user);
            user.setPassword("");
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }


}
