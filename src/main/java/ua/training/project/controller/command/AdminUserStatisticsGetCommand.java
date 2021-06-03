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
//        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
//        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
//
//        List<UserStatisticsDao> statisticsPag = repository.getUserStatistics(currentPage,recordsPerPage);
//        request.setAttribute("statisticsPag", statisticsPag);
//        int rows = repository.getNumberOfRows();
//        int nOfPages = rows / recordsPerPage;
//        if (nOfPages % recordsPerPage > 0) {
//            nOfPages++;
//        }
//        request.setAttribute("noOfPages", nOfPages);
//        request.setAttribute("currentPage", currentPage);
//        request.setAttribute("recordsPerPage", recordsPerPage);
        return USER_STAT_PAGE;
    }
}
