package ua.training.project.controller.util;

import org.apache.commons.lang3.StringUtils;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

import static ua.training.project.constant.SessionCall.*;

public class ServletUtil {

    public void setUserToSession(HttpServletRequest req, Role role, Integer id) {
        HttpSession session = req.getSession();
        session.setAttribute(USER_ID, id);
        session.setAttribute(USER_ROLE, role);
        addToContext(req, id);
    }

    public void setErrorToSession(HttpServletRequest req, ExceptionMessage errorMessage) {
        HttpSession session = req.getSession();
        session.setAttribute(ERROR, errorMessage.getMessage());
    }
    public void setErrorMessagesToSession(HttpServletRequest req, List<String> errorMessages) {
        HttpSession session = req.getSession();
        session.setAttribute(ERROR, String.join("; ", errorMessages));
    }

    public Integer getSessionID(HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute(USER_ID);
    }

    public Role getSessionRole(HttpServletRequest req) {
        return (Role) req.getSession().getAttribute(USER_ROLE);
    }

    public String getErrorMessage (HttpServletRequest request) {
        String error = (String) request.getSession().getAttribute(ERROR);
        request.getSession().setAttribute(ERROR, StringUtils.EMPTY);
        return error;
    }


    public void deleteUserFromContextAndSession(HttpServletRequest req) {
        Integer id = (Integer) req.getSession().getAttribute(USER_ID);
        HttpSession session = req.getSession();

        @SuppressWarnings("unchecked")
        HashMap<Integer, HttpSession> logged = (HashMap<Integer, HttpSession>) session.getServletContext()
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

    private void addToContext(HttpServletRequest request, Integer id) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        HashMap<Integer, HttpSession> logged = (HashMap<Integer, HttpSession>) session.getServletContext()
                .getAttribute(USER_ID);
        if (logged.containsKey(id)) {
            deleteUserFromContextAndSession(request);
        }
        logged.put(id, session);
        session.getServletContext().setAttribute(USER_ID, logged);
    }
}
