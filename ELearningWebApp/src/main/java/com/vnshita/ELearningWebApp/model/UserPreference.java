package com.vnshita.ELearningWebApp.model;

import lombok.Data;

@Data
public class UserPreference {
    private String preferenceId;
    private User userId;
    private Category categoryId;
    private Author authorId;
}
