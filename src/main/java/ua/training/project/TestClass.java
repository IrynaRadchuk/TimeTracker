package ua.training.project;

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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ua.training.project.constant.SessionCall.USER_ID;

public class TestClass {
    private static ServletUtil servletUtil = new ServletUtil();
    private static UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    public static void main(String[] args) throws SQLException {
        Integer id = 29;
            userActivityRepository.requestActivity(id,"q&a");
    }

}
