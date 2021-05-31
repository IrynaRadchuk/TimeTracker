package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.ActivityRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_ACTIVITIES;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.PRG_ADD_ACTIVITY;

public class AdminAddActivitiesPostCommand implements Command {
    private ActivityRepository activityRepository = ActivityRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        String activity = request.getParameter("add_activity_name");
        String category = request.getParameter("category_list");
        activityRepository.createActivity(activity, category);
        servletUtil.setPRGToSession(request, PRG_ADD_ACTIVITY);
        return REDIRECT + MANAGE_ACTIVITIES;
    }
}