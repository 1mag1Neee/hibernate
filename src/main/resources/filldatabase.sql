TRUNCATE company, chat, company_locale, profile, users_chat, users CASCADE;

ALTER SEQUENCE chat_id_seq RESTART;
ALTER SEQUENCE company_id_seq RESTART;
ALTER SEQUENCE users_chat_id_seq RESTART;
ALTER SEQUENCE users_id_seq RESTART;
ALTER SEQUENCE profile_id_seq RESTART;

UPDATE company
SET id = DEFAULT
WHERE TRUE;
UPDATE users
SET id = DEFAULT
WHERE TRUE;
UPDATE users_chat
SET id = DEFAULT
WHERE TRUE;
UPDATE profile
SET id = DEFAULT
WHERE TRUE;
UPDATE chat
SET id = DEFAULT
WHERE TRUE;

-- Добавляем компании
INSERT INTO company (name, created_at, created_by)
VALUES ('Company 1', NOW(), 'Admin'),
       ('Company 2', NOW(), 'Admin'),
       ('Company 3', NOW(), 'Admin'),
       ('Company 4', NOW(), 'Admin'),
       ('Company 5', NOW(), 'Admin');

-- Добавляем профили
INSERT INTO profile (firstname, lastname, birth_date, language)
VALUES ('John', 'Doe', '1990-01-15', 'English'),
       ('Jane', 'Smith', '1985-03-20', 'Spanish'),
       ('Michael', 'Johnson', '1995-07-10', 'French'),
       ('Emily', 'Williams', '1988-09-05', 'German'),
       ('David', 'Brown', '1992-12-30', 'Italian'),
       ('Sophia', 'Lee', '1987-06-18', 'Russian'),
       ('William', 'Kim', '1993-02-25', 'Chinese'),
       ('Olivia', 'Jones', '1983-11-08', 'Japanese'),
       ('James', 'Park', '1998-04-12', 'Korean'),
       ('Emma', 'Miller', '1989-10-03', 'Arabic'),
       ('Daniel', 'Davis', '1991-05-22', 'Dutch'),
       ('Ava', 'Wilson', '1986-08-14', 'Portuguese'),
       ('Alexander', 'Martinez', '1994-03-28', 'Swedish'),
       ('Mia', 'Anderson', '1984-12-01', 'Turkish'),
       ('Ethan', 'Taylor', '1997-07-07', 'Greek'),
       ('Sofia', 'Hernandez', '1982-02-10', 'Hebrew'),
       ('Matthew', 'Garcia', '1996-09-15', 'Polish'),
       ('Amelia', 'Lopez', '1981-04-25', 'Hungarian'),
       ('Benjamin', 'Gonzalez', '1999-01-05', 'Finnish'),
       ('Chloe', 'Perez', '1980-07-20', 'Danish'),
       ('Liam', 'Torres', '1990-10-12', 'Norwegian'),
       ('Ella', 'Flores', '1985-05-30', 'Swahili'),
       ('Jacob', 'Ramirez', '1994-03-15', 'Hindi'),
       ('Grace', 'White', '1989-12-08', 'Urdu'),
       ('Daniel', 'Campbell', '1983-08-23', 'Sanskrit'),
       ('Madison', 'Russell', '1997-02-19', 'Bengali'),
       ('Logan', 'Coleman', '1998-11-02', 'Tamil'),
       ('Logan', 'Coleman', '1998-11-02', 'Tamil'),
       ('Logan', 'Coleman', '1998-11-02', 'Tamil'),
       ('Logan', 'Coleman', '1998-11-02', 'Tamil');

-- Добавляем пользователей и связи
INSERT INTO users (role, info, username, company_id, profile_id)
VALUES ('Admin', '{"email": "admin@example.com"}', 'admin', 1, 1),
       ('User', '{"email": "user1@example.com"}', 'user1', 1, 2),
       ('User', '{"email": "user2@example.com"}', 'user2', 1, 3),
       ('User', '{"email": "user3@example.com"}', 'user3', 2, 4),
       ('User', '{"email": "user4@example.com"}', 'user4', 2, 5),
       ('User', '{"email": "user5@example.com"}', 'user5', 3, 6),
       ('User', '{"email": "user6@example.com"}', 'user6', 3, 7),
       ('User', '{"email": "user7@example.com"}', 'user7', 3, 8),
       ('User', '{"email": "user8@example.com"}', 'user8', 4, 9),
       ('User', '{"email": "user9@example.com"}', 'user9', 4, 10),
       ('User', '{"email": "user10@example.com"}', 'user10', 5, 11),
       ('User', '{"email": "user11@example.com"}', 'user11', 5, 12),
       ('User', '{"email": "user12@example.com"}', 'user12', 5, 13),
       ('User', '{"email": "user13@example.com"}', 'user13', 5, 14),
       ('User', '{"email": "user14@example.com"}', 'user14', 5, 15),
       ('User', '{"email": "user15@example.com"}', 'user15', 1, 16),
       ('User', '{"email": "user16@example.com"}', 'user16', 2, 17),
       ('User', '{"email": "user17@example.com"}', 'user17', 3, 18),
       ('User', '{"email": "user18@example.com"}', 'user18', 4, 19),
       ('User', '{"email": "user19@example.com"}', 'user19', 5, 20),
       ('User', '{"email": "user20@example.com"}', 'user20', 1, 21),
       ('User', '{"email": "user21@example.com"}', 'user21', 2, 22),
       ('User', '{"email": "user22@example.com"}', 'user22', 3, 23),
       ('User', '{"email": "user23@example.com"}', 'user23', 4, 24),
       ('User', '{"email": "user24@example.com"}', 'user24', 5, 25),
       ('User', '{"email": "user25@example.com"}', 'user25', 1, 26),
       ('User', '{"email": "user26@example.com"}', 'user26', 2, 27),
       ('User', '{"email": "user27@example.com"}', 'user27', 3, 28),
       ('User', '{"email": "user28@example.com"}', 'user28', 4, 29),
       ('User', '{"email": "user29@example.com"}', 'user29', 5, 30);

-- Добавляем чаты
INSERT INTO chat (name)
VALUES ('Chat 1'),
       ('Chat 2'),
       ('Chat 3'),
       ('Chat 4'),
       ('Chat 5');

-- Добавляем связи между пользователями и чатами
INSERT INTO users_chat (user_id, chat_id, created_at, created_by)
VALUES (1, 1, NOW(), 'Admin'),
       (2, 1, NOW(), 'Admin'),
       (3, 1, NOW(), 'Admin'),
       (4, 2, NOW(), 'Admin'),
       (5, 2, NOW(), 'Admin'),
       (6, 2, NOW(), 'Admin'),
       (7, 3, NOW(), 'Admin'),
       (8, 3, NOW(), 'Admin'),
       (9, 3, NOW(), 'Admin'),
       (10, 4, NOW(), 'Admin'),
       (11, 4, NOW(), 'Admin'),
       (12, 4, NOW(), 'Admin'),
       (13, 5, NOW(), 'Admin'),
       (14, 5, NOW(), 'Admin'),
       (15, 5, NOW(), 'Admin');

-- Добавляем локализацию компаний
INSERT INTO company_locale (company_id, lang, description)
VALUES (1, 'en', 'Company 1 Description in English'),
       (1, 'es', 'Company 1 Description in Spanish'),
       (2, 'en', 'Company 2 Description in English'),
       (2, 'fr', 'Company 2 Description in French'),
       (3, 'en', 'Company 3 Description in English'),
       (3, 'de', 'Company 3 Description in German'),
       (4, 'en', 'Company 4 Description in English'),
       (4, 'it', 'Company 4 Description in Italian'),
       (5, 'en', 'Company 5 Description in English'),
       (5, 'ru', 'Company 5 Description in Russian');
