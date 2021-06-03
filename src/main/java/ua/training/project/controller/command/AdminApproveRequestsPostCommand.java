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
import static ua.training.project.constant.SessionCall.PRG_APPROVE_REQUEST;

/**
 * Command for admin to approve pending requests
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminApproveRequestsPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminApproveRequestsPostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("pending_id"));
        String activity = request.getParameter("pending_activity_name");
        try {
            userActivityRepository.approveActivity(id, activity);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return ERROR_PAGE;
        } catch (PermissionDeniedException e) {
            log.error(e.getMessage());
            servletUtil.setErrorToSession(request, e.getExceptionMessage().getMessage());
            servletUtil.setPRGToSession(request, PRG_APPROVE_REQUEST);
        }
        servletUtil.setPRGToSession(request, PRG_APPROVE_REQUEST);
        log.info(LoggerInfo.ACTIVITY_APPROVED);
        return REDIRECT + MANAGE_REQUESTS;
    }
}