package ua.training.project.controller.command;

import ua.training.project.model.dao.DateActivityDao;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.project.constant.Attributes.USER_ACTIVITY_LIST;
import static ua.training.project.constant.Path.SHOW_PAGE;
import static ua.training.project.constant.SessionCall.PRG_ACTIVITY_TIME_SHOW;

/**
 * Command for user to see activities schedule
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class ActivityTimeShowGetCommand extends PRG implements Command {
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_ACTIVITY_TIME_SHOW)) {
            executePRG(request);
        }
        Integer id = servletUtil.getSessionID(request);
        List<DateActivityDao> userActivityList = userActivityRepository.getAllUserActivitiesByDate(id);
        request.setAttribute(USER_ACTIVITY_LIST, userActivityList);
        return SHOW_PAGE;
    }
}
