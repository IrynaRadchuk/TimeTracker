package ua.training.project;

import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserActivityRepository;
import ua.training.project.model.repository.UserRepository;

import java.sql.SQLException;
import java.time.LocalDate;

public class TestClass {
    public static void main(String[] args) throws SQLException {
        UserActivityRepository userRepository = UserActivityRepository.getInstance();
        System.out.println(userRepository.getUserID("one@"));
        System.out.println(userRepository.timeValidation(LocalDate.parse("2024-04-21")));
        userRepository.addActivityForUser("ivan@i.ua", "lecture", LocalDate.parse("2024-04-21"),6);

    }
}
