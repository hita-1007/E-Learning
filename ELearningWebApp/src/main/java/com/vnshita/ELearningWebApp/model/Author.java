package com.vnshita.ELearningWebApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Optional: Use if IDs are auto-generated
    @Column(name = "author_id")
    private Long authorId;  // Change type to Long for auto-generation

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


}
