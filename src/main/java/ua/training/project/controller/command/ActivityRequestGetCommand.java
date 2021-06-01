package ua.training.project.controller.command;

import ua.training.project.model.dao.UserActivityDao;
import ua.training.project.model.entity.Activity;
import ua.training.project.model.repository.ActivityRepository;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static ua.training.project.constant.Path.ACTIVITY_REQUEST_PAGE;
import static ua.training.project.constant.SessionCall.PRG_REQUEST_ACTIVITY;

/**
 * Command for user to see page with list of activities approved and pending
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class ActivityRequestGetCommand extends PRG implements Command {
    private ActivityRepository activityRepository = ActivityRepository.getInstance();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_REQUEST_ACTIVITY)) {
            executePRG(request);
        }
        List<UserActivityDao> userActivities = userActivityRepository.getAllUserActivities(servletUtil.getSessionID(request));
        request.setAttribute("user_activities", userActivities);
        List<Activity> allActivities = activityRepository.getAllActivities();
        List<String> userActivitiesNames = userActivities.stream()
                .map(x -> x.getActivityName()).collect(Collectors.toList());
        List<String> activitiesToRequest = allActivities.stream()
                .map(x -> x.getName()).filter(x -> !userActivitiesNames.contains(x)).collect(Collectors.toList());
        request.setAttribute("all_activities", activitiesToRequest);
        return ACTIVITY_REQUEST_PAGE;
    }
}