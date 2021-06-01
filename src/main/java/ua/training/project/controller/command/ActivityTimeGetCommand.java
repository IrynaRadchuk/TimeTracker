package ua.training.project.controller.command;

import ua.training.project.constant.CredentialValidationRegex;
import ua.training.project.model.dao.UserActivityDao;
import ua.training.project.model.entity.UserActivity;
import ua.training.project.model.repository.ActivityRepository;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ua.training.project.constant.Path.USER_PAGE;
import static ua.training.project.constant.SessionCall.*;

/**
 * Command for user to see activity schedule page
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class ActivityTimeGetCommand extends PRG implements Command {
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_ACTIVITY_TIME)||checkPRG(request, PRG_ACTIVITY_TIME_SHOW)) {
            executePRG(request);
        }
        List<UserActivityDao> userActivities = userActivityRepository.getAllUserActivities(servletUtil.getSessionID(request));
        List<String> activities = userActivities.stream().map(x->x.getActivityName()).collect(Collectors.toList());
        request.setAttribute("user_activities", activities);
        return USER_PAGE;
    }
}