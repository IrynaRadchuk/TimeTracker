package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.Path.SHOW;
import static ua.training.project.constant.SessionCall.PRG_ACTIVITY_TIME_SHOW;

/**
 * Command for user to delete activity from schedule
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class ActivityTimeShowPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(ActivityTimeShowPostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("userActivity.date"));
        servletUtil.setPRGToSession(request, PRG_ACTIVITY_TIME_SHOW);
        Integer id = servletUtil.getSessionID(request);
        userActivityRepository.deleteActivityTime(id, date);
        log.info(LoggerInfo.ACTIVITY_DELETED.getMessage());
        return REDIRECT + SHOW;
    }
}
