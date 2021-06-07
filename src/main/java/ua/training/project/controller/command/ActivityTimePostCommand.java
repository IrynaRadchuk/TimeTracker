package ua.training.project.controller.command;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.CredentialValidationRegex;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.PermissionDeniedException;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.training.project.constant.Attributes.*;
import static ua.training.project.constant.CredentialValidationRegex.DATE_PATTERN;
import static ua.training.project.constant.CredentialValidationRegex.KEY;
import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.PRG_ACTIVITY_TIME;

/**
 * Command for user to set activities with date and duration
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class ActivityTimePostCommand implements Command {
    private static final Logger log = LogManager.getLogger(ActivityTimePostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String activityName = request.getParameter(USER_ACTIVITIES);
        String activityNameUa = request.getParameter(USER_ACTIVITIES_UA);
        String duration = request.getParameter(COUNT);
        String date = request.getParameter(DATE);
        String day = request.getParameter(DAY);
        if (StringUtils.isNoneEmpty(activityNameUa)) {
            byte[] bytes = activityNameUa.getBytes(StandardCharsets.ISO_8859_1);
            activityNameUa = new String(bytes, StandardCharsets.UTF_8);
        }
        String dateFormat = "";
        Pattern pattern = Pattern.compile(CredentialValidationRegex.DATE_REGEX);
        Matcher matcher = pattern.matcher(date);
        while (matcher.find()) {
            dateFormat = matcher.group(KEY).replace(FIRST_DAY, day);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.US);
        LocalDate localDate = LocalDate.parse(dateFormat, formatter);
        Integer id = servletUtil.getSessionID(request);
        try {
            userActivityRepository.addActivityForUser(id, activityName, activityNameUa, localDate, duration);
            log.info(LoggerInfo.ACTIVITY_TIME_ADD.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
            return ERROR_PAGE;
        } catch (PermissionDeniedException e) {
            log.error(e.getMessage());
            servletUtil.setErrorToSession(request, e.getExceptionMessage().getMessage());
            servletUtil.setPRGToSession(request, PRG_ACTIVITY_TIME);
            return REDIRECT + ACTIVITY_TIME_CALENDAR;
        }
        servletUtil.setPRGToSession(request, PRG_ACTIVITY_TIME);
        return REDIRECT + ACTIVITY_TIME_CALENDAR;
    }
}
