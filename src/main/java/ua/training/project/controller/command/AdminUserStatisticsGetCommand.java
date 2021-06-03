package ua.training.project.controller.command;

import ua.training.project.model.dao.UserStatisticsDao;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
        int currentPage = Integer.parseInt(Optional.ofNullable(request.getParameter("currentPage"))
                .orElse("1"));
        List<UserStatisticsDao> statisticsPag = repository.getUserStatistics(currentPage);
        request.setAttribute("user_statistics", statisticsPag);
        int rows = repository.getNumberOfRows();
        int nOfPages = rows / 10;
        if (nOfPages % 10 > 0) {
            nOfPages++;
        }
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", 10);
        return USER_STAT_PAGE;
    }
}
