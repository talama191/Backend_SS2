package com.example.ecommercebackend.Entities.User;

import com.example.ecommercebackend.Response.ResponseData;
import com.example.ecommercebackend.Security.AuthResponse;
import com.example.ecommercebackend.Security.JwtTokenProvider;
import com.example.ecommercebackend.Utils.Utils;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;
    private static String salt = "test";

    @GetMapping("/users")
    public ResponseData getAllUsers() {

        return new ResponseData(userService.getAllUsers(), 200, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseData getUserByEmail(@RequestParam String email) {

        User user = userService.getUserByEmail(email);
        if (user == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(user, 200, HttpStatus.OK);
    }

    @GetMapping("/user/get")
//    @CrossOrigin("*")
    public ResponseData getUserByUsername(@RequestParam String username) {

        User user = userService.getUserByUsername(username);
        user.setPassword("");
        if (user == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(user, 200, HttpStatus.OK);
    }

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/user-auth/authenticate")
    public ResponseData AuthenticateUser(@RequestBody User user) {
        try {
            User dbUser = userService.getUserByUsername(user.getUsername());
            String dbPassword = dbUser.getPassword();
            String userPassword = Utils.get_SHA_512_SecurePassword(user.getPassword(), salt);
            boolean testPassword = dbPassword.equals(userPassword);
            if (!testPassword) {
                return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
            }
            int user_role = userService.getUserRoleByUserID(dbUser.getUser_id());
            if (dbUser == null) {
                return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
            } else {
                String accessToken = jwtTokenProvider.generateAccessToken(dbUser, user_role);
                AuthResponse response = new AuthResponse(user.getEmail(), accessToken, user_role);

                return new ResponseData(response, 200, HttpStatus.OK);
            }


        } catch (BadCredentialsException ex) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/user/update")
    public ResponseData updateUser(@RequestBody User user) {
        if (user.getEmail().isEmpty()) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        String originalPassword = user.getPassword();
        String hashedPassword = Utils.get_SHA_512_SecurePassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        userService.updateUser(user);
        user.setPassword("");
        return new ResponseData(user, 200, HttpStatus.OK);
    }

    @PostMapping("/user/update_password")
    public ResponseData updateUserPassword(@RequestParam Integer user_id, @RequestParam String password) {
        if (password.isEmpty() || password == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
//        String originalPassword = user.getPassword();
        User user = userService.getUserByUserID(user_id);
        if (user == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        String hashedPassword = Utils.get_SHA_512_SecurePassword(password, salt);
        user.setPassword(hashedPassword);
        userService.updateUser(user);
        user.setPassword("");
        return new ResponseData(user, 200, HttpStatus.OK);
    }


    @PostMapping("/user-register/register")
    public ResponseData createUser(@RequestBody User user) {
        if (user.getPassword() == null || user.getUsername() == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        if (userService.getUserByUsername(user.getUsername()) == null) {
            user.setPassword(Utils.get_SHA_512_SecurePassword(user.getPassword(), salt));
            user.setRole_id(2);
            userService.createUser(user);
            userService.setUserRole(user.getUser_id(), 2);
            user.setPassword("");
            return new ResponseData(user, 201, HttpStatus.CREATED);
        }
        return new ResponseData(null, 409, HttpStatus.CONFLICT);
    }

    @PostMapping("/user-register/register-admin")
    public ResponseData createAdmin(@RequestBody User user) {
        if (user.getPassword() == null || user.getUsername() == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        if (userService.getUserByUsername(user.getUsername()) == null) {
            user.setPassword(Utils.get_SHA_512_SecurePassword(user.getPassword(), salt));
            userService.createUser(user);
            user.setRole_id(1);
            userService.setUserRole(user.getUser_id(), 1);
            user.setPassword("");
            return new ResponseData(user, 201, HttpStatus.CREATED);
        }
        return new ResponseData(null, 409, HttpStatus.CONFLICT);
    }

}
