package com.vnshita.ELearningWebApp.service;

import com.vnshita.ELearningWebApp.model.Category;
import com.vnshita.ELearningWebApp.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public Category getCategoryByName(String categoryName) {
        return categoryRepo.findIdByCategoryName(categoryName);
    }
}
