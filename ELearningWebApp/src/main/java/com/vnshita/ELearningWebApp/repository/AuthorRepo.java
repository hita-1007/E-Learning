package com.vnshita.ELearningWebApp.repository;

import com.vnshita.ELearningWebApp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> { //Mention the primary key
    Author findByFirstNameAndLastName(String firstName, String lastName);

}
