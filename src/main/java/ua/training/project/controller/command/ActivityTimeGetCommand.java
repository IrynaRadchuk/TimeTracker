package ua.training.project.controller.command;

import ua.training.project.model.dao.UserActivityDao;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ua.training.project.constant.Path.USER_PAGE;

public class ActivityTimeGetCommand extends PRG implements Command {
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
//        List<UserActivityDao> userActivities = userActivityRepository.getAllUserActivities(servletUtil.getSessionID(request));
//        request.setAttribute("user_activities", userActivities);
        return USER_PAGE;
    }
}
