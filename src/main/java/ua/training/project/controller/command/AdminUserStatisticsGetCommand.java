package ua.training.project.controller.command;

import ua.training.project.model.dao.UserStatisticsDao;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.project.constant.Path.USER_STAT_PAGE;

/**
 * Command for admin to see user activity statistics by date
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminUserStatisticsGetCommand implements Command {
    private UserActivityRepository repository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        List<UserStatisticsDao> statistics = repository.getUserStatistics();
        request.setAttribute("user_statistics", statistics);
        return USER_STAT_PAGE;
    }
}
