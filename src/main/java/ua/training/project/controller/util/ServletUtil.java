package ua.training.project.controller.util;

import ua.training.project.model.entity.Role;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

import static ua.training.project.constant.SessionCall.USER_EMAIL;
import static ua.training.project.constant.SessionCall.USER_ROLE;

public class ServletUtil {

    public void addToContext(HttpServletRequest request, String email) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        HashMap<String, HttpSession> logged = (HashMap<String, HttpSession>) session.getServletContext()
                .getAttribute(USER_EMAIL);
        if (logged.containsKey(email)) {
            deleteUserFromContextAndSession(request);
        }
        logged.put(email, session);
        request.getSession().getServletContext().setAttribute(USER_EMAIL, logged);
    }

    public void setUserEmailRoleToSession(HttpServletRequest req, Role role, String email) {
        HttpSession session = req.getSession();
        session.setAttribute(USER_EMAIL, email);
        session.setAttribute(USER_ROLE, role);
    }

    public String getSessionEmail(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(USER_EMAIL);
    }


    public Role getSessionRole(HttpServletRequest req) {
        return (Role) req.getSession().getAttribute(USER_ROLE);
    }


    public void deleteUserFromContextAndSession(HttpServletRequest req) {


        String email = (String) req.getSession().getAttribute(USER_EMAIL);
        HttpSession session = req.getSession();

        @SuppressWarnings("unchecked")
        HashMap<String, HttpSession> logged = (HashMap<String, HttpSession>) session.getServletContext()
                .getAttribute(USER_EMAIL);
        if (logged.containsKey(email)) {
            try {
                logged.get(email).invalidate();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            logged.remove(email);
            session.getServletContext().setAttribute(USER_EMAIL, logged);
        }
    }

    public int getLoggedUserId(HttpServletRequest req) {
        UserRepository userRepository = UserRepository.getInstance();
        return userRepository.getUserFromDB(getSessionEmail(req)).getId();
    }
}
