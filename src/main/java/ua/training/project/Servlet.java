package ua.training.project;

import ua.training.project.controller.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.training.project.constant.Path.HOMEPAGE;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.USER_EMAIL;

public class Servlet extends HttpServlet {

    private Map<String, Command> getCommands = new HashMap<>();
    private Map<String, Command> postCommands = new HashMap<>();

    public void init(ServletConfig config) {

        config.getServletContext()
                .setAttribute(USER_EMAIL, new HashMap<String, HttpSession>());

        System.out.println("SERVLET");
        getCommands.put("login", new LoginGetCommand());
        getCommands.put("registration", new RegistrationGetCommand());
        getCommands.put("user", new PersonalAccountGetCommand());
        getCommands.put("profile", new UserProfileGetCommand());

        postCommands.put("login", new LoginPostCommand());
        postCommands.put("registration", new RegistrationPostCommand());
        postCommands.put("user", new ActivityTimePostCommand());
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response,getCommands);
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
            response.sendRedirect(page.replace(REDIRECT, "/tracker"));
        } else {
            System.out.println("forwarding" + page);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
