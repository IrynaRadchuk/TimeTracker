package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.model.repository.ActivityRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_ACTIVITIES_PAGE;

public class AdminDeleteActivitiesPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminDeleteActivitiesPostCommand.class);
    ActivityRepository activityRepository = ActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter("active_id"));
        activityRepository.deleteActivity(activityId);
        return MANAGE_ACTIVITIES_PAGE;
    }
}