package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.TimeTrackerException;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.training.project.constant.Path.*;

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
            request.setAttribute("success", LoggerInfo.ACTIVITY_REQUESTED.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
            return ERROR_PAGE;
        } catch (TimeTrackerException e){
            log.error(e.getMessage());
            request.setAttribute("error", e.getMessage());
            return ACTIVITY_REQUEST_PAGE;
        }
        return REDIRECT + ACTIVITY_REQUEST;
    }
}
