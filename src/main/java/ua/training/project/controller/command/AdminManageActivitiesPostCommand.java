package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.ActivityRepository;

import javax.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;

import static ua.training.project.constant.Attributes.*;
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
    private static final Logger log = LogManager.getLogger(AdminManageActivitiesPostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    private ActivityRepository activityRepository = ActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter(ACTIVE_ID));
        String activityName = request.getParameter(ACTIVE_NAME);
        String activityNameUa = request.getParameter(ACTIVE_NAME_UA);
        byte[] bytes = activityNameUa.getBytes(StandardCharsets.ISO_8859_1);
        activityNameUa = new String(bytes, StandardCharsets.UTF_8);
        String category = request.getParameter(CATEGORY_LIST);
        activityRepository.updateActivity(activityId, activityName, category, activityNameUa);
        servletUtil.setPRGToSession(request, PRG_UPDATE_ACTIVITY);
        log.info(LoggerInfo.ACTIVITY_UPDATED.getMessage());
        return REDIRECT + MANAGE_ACTIVITIES;
    }
}