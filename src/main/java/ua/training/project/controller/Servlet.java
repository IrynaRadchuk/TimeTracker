package ua.training.project.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.controller.command.*;
import ua.training.project.exception.TimeTrackerException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.*;

/**
 * Main servlet to handle requests and provide get and post commands
 *
 * @author Iryna Radchuk
 * @see HttpServlet
 */
public class Servlet extends HttpServlet {
    private Map<String, Command> getCommands = new HashMap<>();
    private Map<String, Command> postCommands = new HashMap<>();
    private static final Logger log = LogManager.getLogger(Servlet.class);

    /**
     * Set post and get commands to be executed
     *
     * @param config A servlet configuration object used by a servlet container
     *               to pass information to a servlet during initialization.
     */
    public void init(ServletConfig config) {
        config.getServletContext().setAttribute(USER_ID, new HashMap<Integer, HttpSession>());
        config.getServletContext().setAttribute(ERROR, "");
        config.getServletContext().setAttribute(PRG, "");

        getCommands.put("login", new LoginGetCommand());
        getCommands.put("registration", new RegistrationGetCommand());
        getCommands.put("activityTimeCalendar", new ActivityTimeGetCommand());
        getCommands.put("profile", new UserProfileGetCommand());
        getCommands.put("update", new UserProfileChangeGetCommand());
        getCommands.put("activities", new ActivityRequestGetCommand());
        getCommands.put("logout", new LogoutCommand());
        getCommands.put("manageUsers", new AdminManageUsersGetCommand());
        getCommands.put("manageActivities", new AdminManageActivitiesGetCommand());
        getCommands.put("manageRequests", new AdminManageRequestsGetCommand());
        getCommands.put("activityStat", new AdminActivityStatisticsGetCommand());
        getCommands.put("userStat", new AdminUserStatisticsGetCommand());
        getCommands.put("showActivities", new ActivityTimeShowGetCommand());

        postCommands.put("login", new LoginPostCommand());
        postCommands.put("logout", new LogoutCommand());
        postCommands.put("registration", new RegistrationPostCommand());
        postCommands.put("activityTimeCalendar", new ActivityTimePostCommand());
        postCommands.put("update", new UserProfileChangePostCommand());
        postCommands.put("activities", new ActivityRequestPostCommand());
        postCommands.put("manageActivities", new AdminManageActivitiesPostCommand());
        postCommands.put("deleteActivities", new AdminDeleteActivitiesPostCommand());
        postCommands.put("addActivities", new AdminAddActivitiesPostCommand());
        postCommands.put("manageUsers", new AdminManageUsersPostCommand());
        postCommands.put("approveRequest", new AdminApproveRequestsPostCommand());
        postCommands.put("denyRequest", new AdminDenyRequestsPostCommand());
        postCommands.put("deleteUsers", new AdminDeleteUsersPostCommand());
        postCommands.put("addUsers", new AdminAddUsersPostCommand());
        postCommands.put("showActivities", new ActivityTimeShowPostCommand());
    }

    /**
     * Handle get requests to server
     *
     * @param request  Http request to server
     * @param response Http response from server
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response, getCommands);
    }

    /**
     * Handle post requests to server
     *
     * @param request  Http request to server
     * @param response Http response from server
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response, postCommands);
    }

    /**
     * Choose command to execute user request
     *
     * @param request  Http request to server
     * @param response Http response from server
     * @param commands Commands container to execute using command manager
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response, Map<String, Command> commands)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String path = requestURI.replaceAll(".*/tracker/", "");
        Command command = commands.getOrDefault(path, (r) -> HOMEPAGE);
        String page;
        try {
            page = command.execute(request);
        }catch (TimeTrackerException e) {
            log.error(e.getUrl() + " message " + e.getMessage());
            request.setAttribute(ERROR, e.getMessage());
            page = e.getUrl();
        }catch (Exception e){
            log.error(e.getMessage());
            page = ERROR_PAGE;
        }
        if (page.contains(REDIRECT)) {
            response.sendRedirect(page.replace(REDIRECT, TRACKER));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
