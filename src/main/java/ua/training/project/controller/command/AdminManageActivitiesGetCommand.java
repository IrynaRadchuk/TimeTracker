package ua.training.project.controller.command;

import ua.training.project.model.dao.ActivityDao;
import ua.training.project.model.repository.ActivityRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.project.constant.Attributes.ACTIVITY_LIST;
import static ua.training.project.constant.Attributes.CATEGORY_LIST;
import static ua.training.project.constant.Path.MANAGE_ACTIVITIES_PAGE;
import static ua.training.project.constant.SessionCall.*;

/**
 * Command for admin see list of activities
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminManageActivitiesGetCommand extends PRG implements Command {
    private ActivityRepository activityRepository = ActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_ADD_ACTIVITY)
                || checkPRG(request, PRG_DELETE_ACTIVITY)
                || checkPRG(request, PRG_UPDATE_ACTIVITY)) {
            executePRG(request);
        }
        List<ActivityDao> activities = activityRepository.getActivitiesWithCategories();
        request.setAttribute(ACTIVITY_LIST, activities);
        List<String> categories = activityRepository.getAllCategories();
        request.setAttribute(CATEGORY_LIST, categories);
        return MANAGE_ACTIVITIES_PAGE;
    }
}