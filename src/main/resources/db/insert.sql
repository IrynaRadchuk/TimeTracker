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

INSERT INTO activity (activity_name, category_id) values ('Lecture', 1);
INSERT INTO activity (activity_name, category_id) values ('Practice', 1);
INSERT INTO activity (activity_name, category_id) values ('Homework', 2);
INSERT INTO activity (activity_name, category_id) values ('Grading', 2);
INSERT INTO activity (activity_name, category_id) values ('Exam', 2);
INSERT INTO activity (activity_name, category_id) values ('Vacation', 3);
INSERT INTO activity (activity_name, category_id) values ('Illness', 3);
INSERT INTO activity (activity_name, category_id) values ('Journal Fill', 4);
INSERT INTO activity (activity_name, category_id) values ('Meeting', 4);
INSERT INTO activity (activity_name, category_id) values ('Journal', 4);

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

