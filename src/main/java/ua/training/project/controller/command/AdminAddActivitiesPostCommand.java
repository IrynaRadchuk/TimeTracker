package ua.training.project.controller.command;

import org.apache.commons.lang3.StringUtils;
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
import static ua.training.project.constant.SessionCall.PRG_ADD_ACTIVITY;

/**
 * Command for admin to add new activities
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminAddActivitiesPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminAddActivitiesPostCommand.class);
    private ActivityRepository activityRepository = ActivityRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        String activity = request.getParameter(ADD_ACTIVITY_NAME);
        String category = request.getParameter(CATEGORY_LIST);
        String activityUa = request.getParameter(ADD_ACTIVITY_UA);
        if (StringUtils.isNoneEmpty(activityUa)) {
            byte[] bytes = activityUa.getBytes(StandardCharsets.ISO_8859_1);
            activityUa = new String(bytes, StandardCharsets.UTF_8);
        }
        activityRepository.createActivity(activity, category, activityUa);
        servletUtil.setPRGToSession(request, PRG_ADD_ACTIVITY);
        log.info(LoggerInfo.ACTIVITY_ADD.getMessage());
        return REDIRECT + MANAGE_ACTIVITIES;
    }
}