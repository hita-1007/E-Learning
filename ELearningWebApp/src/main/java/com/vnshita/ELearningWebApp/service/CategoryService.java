package com.vnshita.ELearningWebApp.service;

import com.vnshita.ELearningWebApp.model.Category;
import com.vnshita.ELearningWebApp.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;


    public Category getCategoryByName(String categoryName) {
        return categoryRepo.findIdByCategoryName(categoryName);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }
}
