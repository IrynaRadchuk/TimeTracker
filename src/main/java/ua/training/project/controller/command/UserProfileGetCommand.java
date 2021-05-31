package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.PROFILE_PAGE;

public class UserProfileGetCommand implements Command {
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        User user = userRepository.getUserFromDB(servletUtil.getSessionID(request));
/*        List<UserActivity> activities = userActivityRepository.getAllUserActivities(user.getEmail());
        List<String> activityNames = new ArrayList<>();
        for (UserActivity activity : activities) {
            activityNames.add(activity.getActivity().getName());
        }*/
        request.setAttribute("user_email", user.getEmail());
        request.setAttribute("user_password", user.getPassword());
        request.setAttribute("user_first_name", user.getFirstName());
        request.setAttribute("user_last_name", user.getLastName());
//        request.setAttribute("user_activities", activityNames);
        return PROFILE_PAGE;
    }
}
