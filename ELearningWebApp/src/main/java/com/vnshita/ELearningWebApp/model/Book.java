package com.vnshita.ELearningWebApp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;  // Updated to @ManyToOne

    @Column(name = "publication_year")
    private String publicationYear;

    @ManyToOne
    @JoinColumn(name = "uploaded_by_user_id")
    private User uploadedByUser;

    @Column(name = "uploaded_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedDate;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name="image_url")
    private String imageUrl;

//    @Column(name="file_psurl")
//    private String filePreSignedUrl;
//
//    @Column(name="image_psurl")
//    private String imagePreSignedUrl;

}