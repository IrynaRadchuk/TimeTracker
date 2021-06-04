package ua.training.project.controller.command;

import ua.training.project.model.dao.UserStatisticsDao;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static ua.training.project.constant.Attributes.*;
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
        int currentPage = Integer.parseInt(Optional.ofNullable(request.getParameter(CURRENT_PAGE))
                .orElse("1"));
        List<UserStatisticsDao> statisticsPag = repository.getUserStatistics(currentPage);
        request.setAttribute(USERS_STATS, statisticsPag);
        int rows = repository.getNumberOfRows();
        int nOfPages = rows / TEN;
        if (nOfPages % TEN > 0) {
            nOfPages++;
        }
        request.setAttribute(PAGES_NO, nOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(RECORDS, TEN);
        return USER_STAT_PAGE;
    }
}
