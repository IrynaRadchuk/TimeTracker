package ua.training.project.controller.command;

import ua.training.project.model.dao.ActivityDao;
import ua.training.project.model.repository.ActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.project.constant.Path.MANAGE_ACTIVITIES_PAGE;
import static ua.training.project.constant.SessionCall.*;

public class AdminManageActivitiesGetCommand extends PRG implements Command {
    ActivityRepository activityRepository = ActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_ADD_ACTIVITY)||checkPRG(request, PRG_DELETE_ACTIVITY)||checkPRG(request, PRG_UPDATE_ACTIVITY)) {
            executePRG(request);
        }
        List<ActivityDao> activities = activityRepository.getActivitiesWithCategories();
        request.setAttribute("activities_list", activities);
        List<String> categories = activityRepository.getAllCategories();
        request.setAttribute("category_list", categories);
        return MANAGE_ACTIVITIES_PAGE;
    }
}