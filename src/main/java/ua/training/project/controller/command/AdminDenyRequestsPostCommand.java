package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.PermissionDeniedException;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.training.project.constant.Attributes.PENDING_ID;
import static ua.training.project.constant.Attributes.PENDING_NAME;
import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.PRG_DENY_REQUEST;

/**
 * Command for admin to deny pending activities
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminDenyRequestsPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminDenyRequestsPostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(PENDING_ID));
        String activity = request.getParameter(PENDING_NAME);
        try {
            userActivityRepository.denyActivity(id, activity);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return ERROR_PAGE;
        } catch (PermissionDeniedException e) {
            log.error(e.getMessage());
            servletUtil.setErrorToSession(request, e.getMessage());
            servletUtil.setPRGToSession(request, PRG_DENY_REQUEST);
        }
        servletUtil.setPRGToSession(request, PRG_DENY_REQUEST);
        log.info(LoggerInfo.REQUEST_DENY.getMessage());
        return REDIRECT + MANAGE_REQUESTS;
    }
}
