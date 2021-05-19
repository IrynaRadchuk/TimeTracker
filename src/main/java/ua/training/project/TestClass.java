package ua.training.project;

import ua.training.project.dbRepository.UserRepository;
import ua.training.project.model.entity.User;

public class TestClass {
    public static void main(String[] args) {
        UserRepository userRepository = UserRepository.getInstance();
        User t = userRepository.getUserFromDB("ivan@i.ua");
        System.out.println(t);
        System.out.println(userRepository.countUsers());
    }
}
