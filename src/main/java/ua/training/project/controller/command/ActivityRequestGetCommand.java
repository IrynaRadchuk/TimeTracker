package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.dao.UserActivityDao;
import ua.training.project.model.entity.*;
import ua.training.project.model.repository.ActivityRepository;
import ua.training.project.model.repository.UserActivityRepository;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static ua.training.project.constant.Path.ACTIVITY_REQUEST_PAGE;

public class ActivityRequestGetCommand implements Command {
    private UserRepository userRepository = UserRepository.getInstance();
    private ActivityRepository activityRepository = ActivityRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
//        User user = userRepository.getUserFromDB(servletUtil.getSessionID(request));
        List<UserActivityDao> userActivities = userActivityRepository.getAllUserActivities(servletUtil.getSessionID(request));

     /*   for (UserActivity activity : activities) {
            activityNames.add(activity.getActivity().getName());
        }*/

        request.setAttribute("user_activities", userActivities);
        List<Activity> allActivities = activityRepository.getAllActivities();
/*        List<String> allActivityNames = new ArrayList<>();
        for (Activity activity : allActivities) {
            allActivityNames.add(activity.getName());
        }*/
        List<String> userActivitiesNames = userActivities.stream().map(x -> x.getActivityName()).collect(Collectors.toList());
        List<String> activitiesToRequest = allActivities.stream().map(x -> x.getName()).filter(x -> !userActivitiesNames.contains(x)).collect(Collectors.toList());
        request.setAttribute("all_activities", activitiesToRequest);
        return ACTIVITY_REQUEST_PAGE;
    }
}