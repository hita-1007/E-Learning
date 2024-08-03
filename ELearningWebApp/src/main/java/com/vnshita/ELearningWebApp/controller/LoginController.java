package com.vnshita.ELearningWebApp.controller;

import com.vnshita.ELearningWebApp.model.LoginCred;
import com.vnshita.ELearningWebApp.model.User;
import com.vnshita.ELearningWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginCred loginCred) {
        String username = loginCred.getUsername();
        String password = loginCred.getPassword();
        if (userService.validateUser(username, password)) {
            return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK); // Redirect to home page upon successful login
        }
        return null;
    }



}
