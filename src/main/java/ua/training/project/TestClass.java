package ua.training.project;

import org.apache.commons.codec.digest.DigestUtils;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.entity.Activity;
import ua.training.project.model.entity.User;
import ua.training.project.model.entity.UserActivity;
import ua.training.project.model.repository.ActivityRepository;
import ua.training.project.model.repository.UserActivityRepository;
import ua.training.project.model.repository.UserRepository;
import ua.training.project.model.services.UserUpdateService;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ua.training.project.constant.SessionCall.USER_ID;

public class TestClass {
    private static UserRepository userRepository = UserRepository.getInstance();
    private static ActivityRepository activityRepository = ActivityRepository.getInstance();
    private static ServletUtil servletUtil = new ServletUtil();
    private static UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();


    public static void main(String[] args) {
        System.out.println(userRepository.getUserFromDB(64));
        String qwerty123 = DigestUtils.md5Hex("Qwerty123");
        System.out.println(qwerty123);
        System.out.println(DigestUtils.md5Hex("Qwerty123"));
        System.out.println(DigestUtils.md5Hex("Asdfgh12345"));
    }
}
