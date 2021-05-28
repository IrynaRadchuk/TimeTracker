package ua.training.project.controller;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.entity.User;
import ua.training.project.model.entity.UserActivity;
import ua.training.project.model.repository.UserActivityRepository;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static ua.training.project.constant.Path.PROFILE_PAGE;

public class UserProfileGetCommand implements Command {
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        User user = userRepository.getUserFromDB(servletUtil.getSessionEmail(request));
        List<UserActivity> activities = userActivityRepository.getAllUserActivities(user.getEmail());
        List<String>activityNames = new ArrayList<>();
        for (UserActivity activity:activities) {
            activityNames.add(activity.getActivity().getName());
        }
        request.setAttribute("user_email", user.getEmail());
        request.setAttribute("user_password", user.getPassword());
        request.setAttribute("user_first_name", user.getFirstName());
        request.setAttribute("user_last_name", user.getLastName());
        request.setAttribute("user_activities", activityNames);
        return PROFILE_PAGE;
    }
}
