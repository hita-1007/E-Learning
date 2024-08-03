DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Author;
DROP TABLE IF EXISTS Category;

CREATE TABLE User (
                      userId INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      password VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      firstName VARCHAR(50) NOT NULL,
                      lastName VARCHAR(50) NOT NULL
);

CREATE TABLE Author (
                        author_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        first_name VARCHAR(255) NOT NULL,
                        last_name VARCHAR(255) NOT NULL,
                        date_of_birth DATE
);

CREATE TABLE Category (
                          category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          category_name VARCHAR(255) NOT NULL
);

CREATE TABLE Book (
                      book_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      author_id BIGINT,
                      category_id BIGINT NOT NULL,
                      publication_year VARCHAR(4),
                      uploaded_by_user_id BIGINT,
                      uploaded_date TIMESTAMP,
                      file_url VARCHAR(2048),
                      image_url VARCHAR(2048),
--                       file_psurl VARCHAR(2048),
--                       image_psurl VARCHAR(2048),
                      FOREIGN KEY (author_id) REFERENCES Author(author_id),
                      FOREIGN KEY (category_id) REFERENCES Category(category_id),
                      FOREIGN KEY (uploaded_by_user_id) REFERENCES User(user_id),
);

