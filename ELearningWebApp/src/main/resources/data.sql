INSERT INTO User (username, password, email, first_name, last_name) SELECT 'johndoe', 'password123', 'johndoe@example.com', 'John', 'Doe' WHERE NOT EXISTS (SELECT 1 FROM User WHERE username = 'johndoe');
INSERT INTO User (username, password, email, first_name, last_name) SELECT 'janedoe', 'securepassword', 'janedoe@example.com', 'Jane', 'Doe' WHERE NOT EXISTS (SELECT 1 FROM User WHERE username = 'janedoe');
INSERT INTO User (username, password, email, first_name, last_name) SELECT 'alice', 'alicepass', 'alice@example.com', 'Alice', 'Smith' WHERE NOT EXISTS (SELECT 1 FROM User WHERE username = 'alice');
INSERT INTO User (username, password, email, first_name, last_name) SELECT 'bob', 'bobpassword', 'bob@example.com', 'Bob', 'Brown' WHERE NOT EXISTS (SELECT 1 FROM User WHERE username = 'bob');
INSERT INTO User (username, password, email, first_name, last_name) SELECT 'charlie', 'charliepass', 'charlie@example.com', 'Charlie', 'Johnson' WHERE NOT EXISTS (SELECT 1 FROM User WHERE username = 'charlie');

INSERT INTO Author (first_name, last_name) SELECT 'George', 'Orwell' WHERE NOT EXISTS (SELECT 1 FROM Author WHERE first_name = 'George' AND last_name = 'Orwell');
INSERT INTO Author (first_name, last_name) SELECT 'J.K.', 'Rowling' WHERE NOT EXISTS (SELECT 1 FROM Author WHERE first_name = 'J.K.' AND last_name = 'Rowling');
INSERT INTO Author (first_name, last_name) SELECT 'J.R.R.', 'Tolkien' WHERE NOT EXISTS (SELECT 1 FROM Author WHERE first_name = 'J.R.R.' AND last_name = 'Tolkien');
INSERT INTO Author (first_name, last_name) SELECT 'Aldous', 'Huxley' WHERE NOT EXISTS (SELECT 1 FROM Author WHERE first_name = 'Aldous' AND last_name = 'Huxley');

INSERT INTO Category (category_name) SELECT 'Science Fiction' WHERE NOT EXISTS (SELECT 1 FROM Category WHERE category_name = 'Science Fiction');
INSERT INTO Category (category_name) SELECT 'Fantasy' WHERE NOT EXISTS (SELECT 1 FROM Category WHERE category_name = 'Fantasy');
INSERT INTO Category (category_name) SELECT 'Mystery' WHERE NOT EXISTS (SELECT 1 FROM Category WHERE category_name = 'Mystery');
INSERT INTO Category (category_name) SELECT 'Biography' WHERE NOT EXISTS (SELECT 1 FROM Category WHERE category_name = 'Biography');
INSERT INTO Category (category_name) SELECT 'Historical Fiction' WHERE NOT EXISTS (SELECT 1 FROM Category WHERE category_name = 'Historical Fiction');
INSERT INTO Category (category_name) SELECT 'Non-Fiction' WHERE NOT EXISTS (SELECT 1 FROM Category WHERE category_name = 'Non-Fiction');
INSERT INTO Category (category_name) SELECT 'Homework' WHERE NOT EXISTS (SELECT 1 FROM Category WHERE category_name = 'Homework');
INSERT INTO Category (category_name) SELECT 'Fiction' WHERE NOT EXISTS (SELECT 1 FROM Category WHERE category_name = 'Fiction');
INSERT INTO Category (category_name) SELECT 'Romantic Comedy' WHERE NOT EXISTS (SELECT 1 FROM Category WHERE category_name = 'Romantic Comedy');
