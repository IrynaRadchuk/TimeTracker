package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.ActivityRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_ACTIVITIES;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.PRG_DELETE_ACTIVITY;

public class AdminDeleteActivitiesPostCommand implements Command {
    private ActivityRepository activityRepository = ActivityRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter("active_id"));
        activityRepository.deleteActivity(activityId);
        servletUtil.setPRGToSession(request, PRG_DELETE_ACTIVITY);
        return REDIRECT + MANAGE_ACTIVITIES;
    }
}