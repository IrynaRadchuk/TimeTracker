package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;

import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.Path.SHOW;
import static ua.training.project.constant.SessionCall.PRG_ACTIVITY_TIME_SHOW;

/**
 * Command for user to set activities with date and duration
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class ActivityTimeShowPostCommand implements Command {
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("userActivity.date"));
        servletUtil.setPRGToSession(request, PRG_ACTIVITY_TIME_SHOW);
        Integer id = servletUtil.getSessionID(request);
        userActivityRepository.deleteActivityTime(id,date);
        return REDIRECT + SHOW;
    }
}
