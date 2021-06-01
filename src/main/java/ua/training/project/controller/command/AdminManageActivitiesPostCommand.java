package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.ActivityRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_ACTIVITIES;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.PRG_UPDATE_ACTIVITY;

/**
 * Command for admin to change activities
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminManageActivitiesPostCommand implements Command {
    private ServletUtil servletUtil = new ServletUtil();
    private ActivityRepository activityRepository = ActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter("active_id"));
        String activityName = request.getParameter("active_name");
        String category = request.getParameter("category_list");
        activityRepository.updateActivity(activityId, activityName, category);
        servletUtil.setPRGToSession(request, PRG_UPDATE_ACTIVITY);
        return REDIRECT + MANAGE_ACTIVITIES;
    }
}