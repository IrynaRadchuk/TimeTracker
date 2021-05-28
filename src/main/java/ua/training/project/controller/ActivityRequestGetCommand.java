package ua.training.project.controller;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.entity.Activity;
import ua.training.project.model.entity.User;
import ua.training.project.model.entity.UserActivity;
import ua.training.project.model.repository.ActivityRepository;
import ua.training.project.model.repository.UserActivityRepository;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ua.training.project.constant.Path.ACTIVITY_REQUEST_PAGE;

public class ActivityRequestGetCommand implements Command{
    private UserRepository userRepository = UserRepository.getInstance();
    private ActivityRepository activityRepository = ActivityRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        User user = userRepository.getUserFromDB(servletUtil.getSessionID(request));
        System.out.println(user+"346363");
        List<UserActivity> activities = userActivityRepository.getAllUserActivities(user.getEmail());
        List<String>activityNames = new ArrayList<>();
        for (UserActivity activity:activities) {
            activityNames.add(activity.getActivity().getName());
        }
        request.setAttribute("user_activities", activityNames);
        System.out.println(activityNames);
        List<Activity> allActivities = activityRepository.getAllActivities();
        List<String>allActivityNames = new ArrayList<>();
        for (Activity activity:allActivities) {
            allActivityNames.add(activity.getName());
        }
        request.setAttribute("all_activities", allActivityNames);
        return ACTIVITY_REQUEST_PAGE;
    }
}