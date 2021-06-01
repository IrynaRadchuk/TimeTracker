package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.training.project.constant.Path.MANAGE_REQUESTS;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.PRG_DENY_REQUEST;

public class AdminDenyRequestsPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminDenyRequestsPostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("pending_id"));
        String activity = request.getParameter("pending_activity_name");
        try {
            userActivityRepository.denyActivity(id, activity);
        } catch (SQLException e) {
            log.error(e.getMessage());
            servletUtil.setErrorToSession(request, e.getMessage());
            servletUtil.setPRGToSession(request, PRG_DENY_REQUEST);
        }
        return REDIRECT + MANAGE_REQUESTS;
    }
}
