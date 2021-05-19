package ua.training.project;

import ua.training.project.controller.Command;
import ua.training.project.controller.LoginCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.training.project.Path.PAGE_HOMEPAGE;
import static ua.training.project.Path.REDIRECT;

@WebServlet("/app")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    public void init() {
        commands.put("login", new LoginCommand());
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().replaceAll(".*/app/", "");
        Command command = commands.getOrDefault(path,
                (r) -> PAGE_HOMEPAGE);
        String page = command.execute(request);
        if (page.contains(REDIRECT)) {
            response.sendRedirect(page.replace(REDIRECT, "/api"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
