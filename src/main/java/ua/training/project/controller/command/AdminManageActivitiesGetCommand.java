package ua.training.project.controller.command;

import ua.training.project.model.dao.ActivityDao;
import ua.training.project.model.entity.Activity;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.ActivityRepository;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.project.constant.Path.MANAGE_ACTIVITIES_PAGE;

public class AdminManageActivitiesGetCommand implements Command {
    ActivityRepository activityRepository = ActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        List<ActivityDao> activities = activityRepository.getActivitiesWithCategories();
        request.setAttribute("activities_list", activities);
        List <String> categories = activityRepository.getAllCategories();
        request.setAttribute("category_list", categories);
        return MANAGE_ACTIVITIES_PAGE;
    }
}