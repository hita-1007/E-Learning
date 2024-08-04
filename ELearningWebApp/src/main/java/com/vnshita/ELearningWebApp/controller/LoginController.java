package com.vnshita.ELearningWebApp.controller;

import com.vnshita.ELearningWebApp.model.LoginCred;
import com.vnshita.ELearningWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCred loginCred) {
        String username = loginCred.getUsername();
        String password = loginCred.getPassword();
        if (userService.validateUser(username, password)) {
            return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK); // Redirect to home page upon successful login
        }
        return new ResponseEntity<String>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCred loginCred) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCred.getUsername(), loginCred.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }*/




}
