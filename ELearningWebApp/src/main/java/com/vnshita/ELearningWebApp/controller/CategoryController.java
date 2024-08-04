package com.vnshita.ELearningWebApp.controller;

import com.vnshita.ELearningWebApp.model.Book;
import com.vnshita.ELearningWebApp.model.Category;
import com.vnshita.ELearningWebApp.service.BookService;
import com.vnshita.ELearningWebApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);

    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<Book>> getCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(bookService.getBooksByCategoryId(categoryId),HttpStatus.OK);
    }


}
