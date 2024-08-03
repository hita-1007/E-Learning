package com.vnshita.ELearningWebApp.repository;

import com.vnshita.ELearningWebApp.model.Author;
import com.vnshita.ELearningWebApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
