package ua.training.project.controller.command;

import ua.training.project.model.dao.ActivityStatisticsDao;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.project.constant.Path.ACTIVITY_STAT_PAGE;

/**
 * Command for admin to see user statistics page
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminActivityStatisticsGetCommand implements Command {
    private UserActivityRepository repository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        List<ActivityStatisticsDao> statistics = repository.getActivityStatistics();
        request.setAttribute("activity_statistics", statistics);
        return ACTIVITY_STAT_PAGE;
    }
}
