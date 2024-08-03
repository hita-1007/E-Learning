package com.vnshita.ELearningWebApp.controller;
import com.vnshita.ELearningWebApp.model.Author;
import com.vnshita.ELearningWebApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthors() {
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK);
    }

    @GetMapping("/authors/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping("/authors")
    public void addAuthor(@RequestBody Author author) {
        System.out.println(author);
        authorService.addAuthor(author);
    }

    @PutMapping("/authors")
    public void updateAuthor(@RequestBody Author author) {
        authorService.updateAuthor(author);
    }

    @DeleteMapping("/authors/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthorByID(authorId);
    }


}
