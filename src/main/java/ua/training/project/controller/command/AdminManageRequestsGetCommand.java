package ua.training.project.controller.command;

import ua.training.project.model.dao.PendingActivity;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.project.constant.Attributes.PENDING;
import static ua.training.project.constant.Path.MANAGE_REQUESTS_PAGE;
import static ua.training.project.constant.SessionCall.PRG_APPROVE_REQUEST;
import static ua.training.project.constant.SessionCall.PRG_DENY_REQUEST;

/**
 * Command for admin to see new activity requests from users
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminManageRequestsGetCommand extends PRG implements Command {
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_APPROVE_REQUEST) || checkPRG(request, PRG_DENY_REQUEST)) {
            executePRG(request);
        }
        List<PendingActivity> activities = userActivityRepository.getAllPendingActivities();
        request.setAttribute(PENDING, activities);
        return MANAGE_REQUESTS_PAGE;
    }
}
