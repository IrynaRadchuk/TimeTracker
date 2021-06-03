package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.PermissionDeniedException;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.PRG_REQUEST_ACTIVITY;

/**
 * Command for user to approve new activities
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class ActivityRequestPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(ActivityRequestPostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        Integer id = servletUtil.getSessionID(request);
        String activity = request.getParameter("all_activities");
        try {
            userActivityRepository.requestActivity(id, activity);
            log.info(LoggerInfo.ACTIVITY_REQUESTED.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
            return ERROR_PAGE;
        } catch (PermissionDeniedException e) {
            log.error(e.getMessage());
            servletUtil.setErrorToSession(request, e.getMessage());
            servletUtil.setPRGToSession(request, PRG_REQUEST_ACTIVITY);
            return REDIRECT + ACTIVITY_REQUEST;
        }
        servletUtil.setPRGToSession(request, PRG_REQUEST_ACTIVITY);
        return REDIRECT + ACTIVITY_REQUEST;
    }
}
