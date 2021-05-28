package ua.training.project.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.CredentialValidationRegex;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.PermissionDeniedException;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.training.project.constant.Path.USER_PERSONAL_ACCOUNT;

public class ActivityTimePostCommand implements Command {
    private static final Logger log = LogManager.getLogger(ActivityTimePostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String activityName = request.getParameter("name");
        int duration = Integer.parseInt(request.getParameter("count"));
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
        try {
            userActivityRepository.addActivityForUser(id, activityName, localDate, duration);
            log.info(LoggerInfo.ACTIVITY_TIME_ADD);
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (PermissionDeniedException e) {
            log.error(e.getMessage());
            request.setAttribute("error", e.getMessage());
        }
        return USER_PERSONAL_ACCOUNT;
    }
}
