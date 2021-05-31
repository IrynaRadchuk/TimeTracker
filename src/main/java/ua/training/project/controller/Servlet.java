package ua.training.project.controller;

import ua.training.project.controller.command.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.training.project.constant.Path.HOMEPAGE;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.*;

public class Servlet extends HttpServlet {

    private Map<String, Command> getCommands = new HashMap<>();
    private Map<String, Command> postCommands = new HashMap<>();

    public void init(ServletConfig config) {
        config.getServletContext().setAttribute(USER_ID, new HashMap<Integer, HttpSession>());
        config.getServletContext().setAttribute(ERROR, "");
        config.getServletContext().setAttribute(PRG,"");

        System.out.println("SERVLET");
        getCommands.put("login", new LoginGetCommand());
        getCommands.put("registration", new RegistrationGetCommand());
        getCommands.put("user", new ActivityTimeGetCommand());
        getCommands.put("profile", new UserProfileGetCommand());
        getCommands.put("update", new UserProfileChangeGetCommand());
        getCommands.put("activities", new ActivityRequestGetCommand());
        getCommands.put("logout", new LogoutCommand());
        getCommands.put("manageUsers", new AdminManageUsersGetCommand());
        getCommands.put("manageActivities", new AdminManageActivitiesGetCommand());
        getCommands.put("manageRequests", new AdminManageRequestsGetCommand());
        getCommands.put("activityStat", new AdminActivityStatisticsGetCommand());
        getCommands.put("userStat", new AdminUserStatisticsGetCommand());

        postCommands.put("login", new LoginPostCommand());
        postCommands.put("logout", new LogoutCommand());
        postCommands.put("registration", new RegistrationPostCommand());
        postCommands.put("user", new ActivityTimePostCommand());
        postCommands.put("update", new UserProfileChangePostCommand());
        postCommands.put("activities", new ActivityRequestPostCommand());
        postCommands.put("manageActivities", new AdminManageActivitiesPostCommand());
        postCommands.put("deleteActivities", new AdminDeleteActivitiesPostCommand());
        postCommands.put("addActivities", new AdminAddActivitiesPostCommand());
        postCommands.put("manageUsers", new AdminManageUsersPostCommand());
        postCommands.put("manageRequests", new AdminManageRequestsPostCommand());
        postCommands.put("deleteUsers", new AdminDeleteUsersPostCommand());
        postCommands.put("addUsers", new AdminAddUsersPostCommand());
        postCommands.put("activityStat", new AdminActivityStatisticsPostCommand());
        postCommands.put("userStat", new AdminUserStatisticsPostCommand());
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response, getCommands);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response, postCommands);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, Map<String, Command> commands)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        System.out.println("request uri = " + requestURI);
        String path = requestURI.replaceAll(".*/tracker/", "");
        System.out.println("path = " + path);
        Command command = commands.getOrDefault(path, (r) -> HOMEPAGE);
        String page = command.execute(request);
        if (page.contains(REDIRECT)) {
            System.out.println("Redirect to: "+ page);
            response.sendRedirect(page.replace(REDIRECT, "/tracker"));
        } else {
            System.out.println("forwarding" + page);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
