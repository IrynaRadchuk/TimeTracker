package ua.training.project;

import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

public class TestClass {
    public static void main(String[] args) {
        UserRepository userRepository = UserRepository.getInstance();
        User t = userRepository.getUserFromDB("one@");
        System.out.println(t);
        System.out.println(userRepository.countUsers());
    }
}
