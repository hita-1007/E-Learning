package com.vnshita.ELearningWebApp.service;

import com.amazonaws.services.s3.model.S3Object;
import com.vnshita.ELearningWebApp.model.Book;
import com.vnshita.ELearningWebApp.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private S3Service s3Service;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElse(null);  // Handle optional case
    }

    public void addBook(String title, String authorFirstName, String authorLastName, String categoryName,
                        String publicationYear,Long userId,String fileUrl,String imageUrl) throws IOException {

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(authorService.getAuthorByAuthorName(authorFirstName, authorLastName));
        book.setCategory(categoryService.getCategoryByName(categoryName));
        book.setPublicationYear(publicationYear);
        book.setUploadedByUser(userService.findUserByUserId(userId));
        book.setUploadedDate(new Date());  // Set the uploaded date to the current date
        book.setFileUrl(fileUrl);
        book.setImageUrl(imageUrl);
        bookRepo.save(book);
    }


}
