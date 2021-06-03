package ua.training.project;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.ActivityRepository;
import ua.training.project.model.repository.UserActivityRepository;
import ua.training.project.model.repository.UserRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestClass {
    private static UserRepository userRepository = UserRepository.getInstance();
    private static ActivityRepository activityRepository = ActivityRepository.getInstance();
    private static ServletUtil servletUtil = new ServletUtil();
    private static UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();


    public static void main(String[] args) throws IOException {
        InputStream file = new FileInputStream("src/main/resources/db.properties");
        Properties prop = new Properties();
        prop.load(file);
        String property1 = prop.getProperty("db.url");
        System.out.println(property1);
    }
}
