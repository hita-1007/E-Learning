package com.vnshita.ELearningWebApp.service;

import com.amazonaws.services.s3.model.S3Object;
import com.vnshita.ELearningWebApp.model.Author;
import com.vnshita.ELearningWebApp.model.Book;
import com.vnshita.ELearningWebApp.model.Category;
import com.vnshita.ELearningWebApp.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
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
                        String publicationYear, Long userId, String fileUrl, String imageUrl) throws IOException {

        Author author = authorService.getAuthorByAuthorName(authorFirstName, authorLastName);
        if (author == null) {
            author = new Author();
            author.setFirstName(authorFirstName);
            author.setLastName(authorLastName);
            authorService.addAuthor(author);  // Save the new author
        }

        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setCategoryName(categoryName);
            categoryService.saveCategory(category);  // Save the new category
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setPublicationYear(publicationYear);
        book.setUploadedByUser(userService.findUserByUserId(userId));

        // Set the current date directly
        book.setUploadedDate(new Date());

        book.setFileKey(fileUrl);
        book.setImageKey(imageUrl);
        bookRepo.save(book);
    }


    public List<Book> getBooksByCategoryId(Long categoryId) {
        return bookRepo.findByCategory_CategoryId(categoryId);
    }


}
