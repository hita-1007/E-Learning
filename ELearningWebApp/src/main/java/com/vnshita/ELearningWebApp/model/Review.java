package com.vnshita.ELearningWebApp.model;

import lombok.Data;

import java.util.Date;

@Data
public class Review {
    private String reviewId;
    private User user;
    private Book book;
    private int rating;
    private String comment;
    private Date reviewDate;
}
