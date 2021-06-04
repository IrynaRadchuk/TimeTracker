package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.ActivityRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Attributes.ACTIVE_ID;
import static ua.training.project.constant.Path.MANAGE_ACTIVITIES;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.PRG_DELETE_ACTIVITY;

/**
 * Command for admin to delete activities
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminDeleteActivitiesPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminDeleteActivitiesPostCommand.class);
    private ActivityRepository activityRepository = ActivityRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter(ACTIVE_ID));
        activityRepository.deleteActivity(activityId);
        servletUtil.setPRGToSession(request, PRG_DELETE_ACTIVITY);
        log.info(LoggerInfo.ACTIVITY_DELETE.getMessage());
        return REDIRECT + MANAGE_ACTIVITIES;
    }
}