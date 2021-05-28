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

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestClass {
    private static UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();
    private static UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();
    public static void main(String[] args) throws SQLException {
        User user = userRepository.getUserFromDB(29);
        List<UserActivity> activities = userActivityRepository.getAllUserActivities(user.getEmail());
        List<String>activityNames = new ArrayList<>();
        for (UserActivity activity:activities) {
            activityNames.add(activity.getActivity().getName());
        }
        List<Activity> act = new ArrayList<>();
        for (UserActivity activity:activities) {
            act.add(activity.getActivity());
        }
        System.out.println(activityNames);
        System.out.println(act);
        ActivityRepository activityRepository = ActivityRepository.getInstance();
        List<Activity> allActivities = activityRepository.getAllActivities();
        List<String>allActivityNames = new ArrayList<>();
        for (Activity activity:allActivities) {
            allActivityNames.add(activity.getName());
        }
        System.out.println(allActivityNames);
    }
}
