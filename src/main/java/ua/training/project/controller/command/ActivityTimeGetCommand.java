package ua.training.project.controller.command;

import ua.training.project.model.dao.UserActivityDao;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static ua.training.project.constant.Attributes.USER_ACTIVITIES;
import static ua.training.project.constant.Attributes.USER_ACTIVITIES_UA;
import static ua.training.project.constant.Path.ACTIVITY_TIME_CALENDAR_PAGE;
import static ua.training.project.constant.SessionCall.PRG_ACTIVITY_TIME;
import static ua.training.project.model.entity.ActivityStatus.APPROVED;

/**
 * Command for user to see activity calendar page
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class ActivityTimeGetCommand extends PRG implements Command {
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_ACTIVITY_TIME)) {
            executePRG(request);
        }
        List<UserActivityDao> userActivities = userActivityRepository
                .getAllUserActivities(servletUtil.getSessionID(request));
        List<String> activities = userActivities.stream().filter(x -> x.getActivityStatus().equals(APPROVED))
                .map(x -> x.getActivityName()).collect(Collectors.toList());
        request.setAttribute(USER_ACTIVITIES, activities);
        List<String> activitiesUa = userActivities.stream().filter(x -> x.getActivityStatus().equals(APPROVED))
                .map(x -> x.getActivityUa()).collect(Collectors.toList());
        request.setAttribute(USER_ACTIVITIES_UA, activitiesUa);
        return ACTIVITY_TIME_CALENDAR_PAGE;
    }
}