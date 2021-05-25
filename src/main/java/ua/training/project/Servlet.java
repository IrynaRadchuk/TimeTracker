package ua.training.project;

import ua.training.project.controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.training.project.constant.Path.HOMEPAGE;
import static ua.training.project.constant.Path.REDIRECT;

@WebServlet("/TimeTracker")
public class Servlet extends HttpServlet {

    private Map<String, Command> getCommands = new HashMap<>();
    private Map<String, Command> postCommands = new HashMap<>();

    public void init() {
        System.out.println("SERVLET");
        getCommands.put("login", new LoginGetCommand());
        postCommands.put("login", new LoginPostCommand());
        getCommands.put("registration", new RegistrationGetCommand());
        postCommands.put("registration", new RegistrationPostCommand());
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
        String path = request.getRequestURI().replaceAll(".*/TimeTracker/", "");
        Command command = commands.getOrDefault(path,
                (r) -> HOMEPAGE);
        String page = command.execute(request);
        if (page.contains(REDIRECT)) {
            response.sendRedirect(page.replace(REDIRECT, "/api"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
