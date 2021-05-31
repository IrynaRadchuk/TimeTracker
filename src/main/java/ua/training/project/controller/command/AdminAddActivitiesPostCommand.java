package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.model.repository.ActivityRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_ACTIVITIES_PAGE;

public class AdminAddActivitiesPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminAddActivitiesPostCommand.class);
    ActivityRepository activityRepository = ActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String activity = request.getParameter("add_activity_name");;
        String category = request.getParameter("category_list");
        activityRepository.createActivity(activity, category);
        return MANAGE_ACTIVITIES_PAGE;
    }
}