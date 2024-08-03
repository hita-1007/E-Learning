package com.vnshita.ELearningWebApp.service;

import com.vnshita.ELearningWebApp.model.Author;
import com.vnshita.ELearningWebApp.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    AuthorRepo authorRepo;

//    List<Author> authors = new ArrayList<Author>() {{
//        add(new Author("1", "J.K.", "Rowling", "1965-07-31"));
//        add(new Author("2", "George R.R.", "Martin", "1948-09-20"));
//        add(new Author("3", "J.R.R.", "Tolkien", "1892-01-03"));
//        add(new Author("4", "Stephen", "King", "1947-09-21"));
//    }};

    public List<Author> getAuthors() {
        return authorRepo.findAll();
    }

    public Author getAuthorById(Long authorId) {
        //return authors.stream().filter(author -> author.getAuthorId().equals(authorId)).toList().getFirst();
        return authorRepo.findById(authorId).orElse(new Author());
    }

    public void addAuthor(Author author) {
        authorRepo.save(author);
    }

    public void updateAuthor(Author author) {
        authorRepo.save(author);
    }

    public void deleteAuthorByID(Long authorId) {
        authorRepo.deleteById(authorId);
    }

    public Author getAuthorByAuthorName(String firstName, String lastName) {
        return authorRepo.findByFirstNameAndLastName(firstName, lastName);
    }
}
