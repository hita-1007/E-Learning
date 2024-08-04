package com.vnshita.ELearningWebApp.repository;

import com.vnshita.ELearningWebApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByCategory_CategoryId(Long categoryId);
}
