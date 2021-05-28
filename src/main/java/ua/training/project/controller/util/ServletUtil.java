package ua.training.project.controller.util;

import ua.training.project.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

import static ua.training.project.constant.SessionCall.USER_ID;
import static ua.training.project.constant.SessionCall.USER_ROLE;

public class ServletUtil {

    public void addToContext(HttpServletRequest request, Integer id) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        HashMap<Integer, HttpSession> logged = (HashMap<Integer, HttpSession>) session.getServletContext()
                .getAttribute(USER_ID);
        if (logged.containsKey(id)) {
            deleteUserFromContextAndSession(request);
        }
        logged.put(id, session);
        request.getSession().getServletContext().setAttribute(USER_ID, logged);
    }

    public void setUserEmailRoleToSession(HttpServletRequest req, Role role, Integer id) {
        HttpSession session = req.getSession();
        session.setAttribute(USER_ID, id);
        session.setAttribute(USER_ROLE, role);
    }

    public Integer getSessionID(HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute(USER_ID);
    }


    public Role getSessionRole(HttpServletRequest req) {
        return (Role) req.getSession().getAttribute(USER_ROLE);
    }


    public void deleteUserFromContextAndSession(HttpServletRequest req) {


        Integer id = (Integer) req.getSession().getAttribute(USER_ID);
        HttpSession session = req.getSession();

        @SuppressWarnings("unchecked")
        HashMap<String, HttpSession> logged = (HashMap<String, HttpSession>) session.getServletContext()
                .getAttribute(USER_ID);
        if (logged.containsKey(id)) {
            try {
                logged.get(id).invalidate();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            logged.remove(id);
            session.getServletContext().setAttribute(USER_ID, logged);
        }
    }
}
