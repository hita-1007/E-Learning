package com.vnshita.ELearningWebApp.controller;

import com.vnshita.ELearningWebApp.model.User;
import com.vnshita.ELearningWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User savedUser = userService.addUser(user);
            if (savedUser != null) {
                // Successful signup
                return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
            } else {
                // If user is null or signup failed
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signup failed. Please try again.");
            }
        } catch (Exception e) {
            // Handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred. Please try again later.");
        }
    }

}
