INSERT INTO user_role (role_id, role_name) values (1, 'USER');
INSERT INTO user_role (role_id, role_name) values (2, 'ADMIN');

INSERT INTO user (user_email, user_password, user_first_name, user_last_name, role_id)
values ('admin@gmail.com', '2af9b1ba42dc5eb01743e6b3759b6e4b', 'Petr', 'Petrov', 2);
INSERT INTO user (user_email, user_password, user_first_name, user_last_name, role_id)
values ('user@gmail.com', '2af9b1ba42dc5eb01743e6b3759b6e4b', 'Ivan', 'Ivanov', 1);

INSERT INTO activity_category (category_id, category_name) values (1, 'Lesson');
INSERT INTO activity_category (category_id, category_name) values (2, 'Result check');
INSERT INTO activity_category (category_id, category_name) values (3, 'Day off');
INSERT INTO activity_category (category_id, category_name) values (4, 'Organization');

INSERT INTO activity (activity_name, category_id, activity_ua) values ('Lecture', 1, 'Лекція');
INSERT INTO activity (activity_name, category_id, activity_ua) values ('Practice', 1, 'Практика');
INSERT INTO activity (activity_name, category_id, activity_ua) values ('Homework', 2, 'Перевірка ДЗ');
INSERT INTO activity (activity_name, category_id, activity_ua) values ('Grading', 2, 'Оцінювання');
INSERT INTO activity (activity_name, category_id, activity_ua) values ('Exam', 2, 'Екзамен');
INSERT INTO activity (activity_name, category_id, activity_ua) values ('Vacation', 3, 'Відпустка');
INSERT INTO activity (activity_name, category_id, activity_ua) values ('Illness', 3, 'Хвороба');
INSERT INTO activity (activity_name, category_id, activity_ua) values ('Journal Fill', 4, 'Ведення журналу');
INSERT INTO activity (activity_name, category_id, activity_ua) values ('Meeting', 4, 'Збори');
INSERT INTO activity (activity_name, category_id, activity_ua) values ('Holiday', 3, 'Свято');

INSERT INTO user_allowed_activity (user_id, activity_id, status) values (1, 1, 'APPROVED');
INSERT INTO user_allowed_activity (user_id, activity_id, status) values (1, 2, 'APPROVED');
INSERT INTO user_allowed_activity (user_id, activity_id, status) values (1, 3, 'APPROVED');
INSERT INTO user_allowed_activity (user_id, activity_id, status) values (1, 6, 'PENDING');
INSERT INTO user_allowed_activity (user_id, activity_id, status) values (1, 7, 'PENDING');

INSERT INTO user_activity (user_id, activity_id, activity_date, activity_duration) values (1, 1, '2021-06-01', 3);
INSERT INTO user_activity (user_id, activity_id, activity_date, activity_duration) values (1, 2, '2021-06-01', 5);
INSERT INTO user_activity (user_id, activity_id, activity_date, activity_duration) values (1, 1, '2021-06-02', 8);
INSERT INTO user_activity (user_id, activity_id, activity_date, activity_duration) values (1, 2, '2021-06-03', 8);
INSERT INTO user_activity (user_id, activity_id, activity_date, activity_duration) values (1, 1, '2021-06-04', 3);
INSERT INTO user_activity (user_id, activity_id, activity_date, activity_duration) values (1, 2, '2021-06-04', 3);
INSERT INTO user_activity (user_id, activity_id, activity_date, activity_duration) values (1, 3, '2021-06-04', 2);

