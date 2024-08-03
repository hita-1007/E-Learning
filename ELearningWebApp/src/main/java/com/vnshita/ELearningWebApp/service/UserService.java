package com.vnshita.ELearningWebApp.service;

import com.vnshita.ELearningWebApp.model.User;
import com.vnshita.ELearningWebApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public User addUser(User user) {
        return this.userRepo.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User findUserByUserId(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }


    public boolean validateUser(String username, String password) {
        User user = userRepo.findByUsername(username);
        return user != null && password.equals(user.getPassword());
    }
}
