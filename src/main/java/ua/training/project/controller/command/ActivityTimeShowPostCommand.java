package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.CredentialValidationRegex;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.dao.DateActivityDao;
import ua.training.project.model.entity.UserActivity;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.PRG_ACTIVITY_TIME;
import static ua.training.project.constant.SessionCall.PRG_ACTIVITY_TIME_SHOW;

/**
 * Command for user to set activities with date and duration
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class ActivityTimeShowPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(ActivityTimeShowPostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String date = request.getParameter("date");
        String day = request.getParameter("day");
        String dateFormat = "";
        Pattern pattern = Pattern.compile(CredentialValidationRegex.DATE_REGEX);
        Matcher matcher = pattern.matcher(date);
        while (matcher.find()) {
            dateFormat = matcher.group("key").replace("01", day);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US);
        LocalDate localDate = LocalDate.parse(dateFormat, formatter);
        Integer id = servletUtil.getSessionID(request);
        List<DateActivityDao> userActivityList = userActivityRepository.getAllUserActivitiesByDate(id, localDate);
        request.setAttribute("userActivityList", userActivityList);
        servletUtil.setPRGToSession(request, PRG_ACTIVITY_TIME_SHOW);
        return REDIRECT + USER;
    }
}
