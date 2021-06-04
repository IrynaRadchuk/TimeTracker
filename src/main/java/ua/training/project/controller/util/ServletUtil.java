package ua.training.project.controller.util;

import org.apache.commons.lang3.StringUtils;
import ua.training.project.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

import static ua.training.project.constant.SessionCall.*;

/**
 * Utility class to manage sessions
 *
 * @author Iryna Radchuk
 */
public class ServletUtil {

    /**
     * Set user id and role to session
     */
    public void setUserToSession(HttpServletRequest req, Role role, Integer id) {
        HttpSession session = req.getSession();
        session.setAttribute(USER_ID, id);
        session.setAttribute(USER_ROLE, role);
        addToContext(req, id);
    }

    /**
     * Set PRG ULR to session
     */
    public void setPRGToSession(HttpServletRequest req, String url) {
        HttpSession session = req.getSession();
        session.setAttribute(PRG, url);
    }

    /**
     * Set error message to session
     */
    public void setErrorToSession(HttpServletRequest req, String errorMessage) {
        HttpSession session = req.getSession();
        session.setAttribute(ERROR, errorMessage);
    }

    /**
     * Set error messages list to session
     */
    public void setErrorMessagesToSession(HttpServletRequest req, List<String> errorMessages) {
        HttpSession session = req.getSession();
        session.setAttribute(ERROR, String.join("; ", errorMessages));
    }

    /**
     * Get user id from session
     */
    public Integer getSessionID(HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute(USER_ID);
    }

    /**
     * Get user role from session
     */
    public Role getSessionRole(HttpServletRequest req) {
        return (Role) req.getSession().getAttribute(USER_ROLE);
    }

    /**
     * Get error message from session
     */
    public String getErrorMessage(HttpServletRequest request) {
        String error = (String) request.getSession().getAttribute(ERROR);
        request.getSession().setAttribute(ERROR, StringUtils.EMPTY);
        return error;
    }

    /**
     * Get PRG ULR from session
     */
    public String getPrgUrl(HttpServletRequest request) {
        String prg = (String) request.getSession().getAttribute(PRG);
        request.getSession().setAttribute(PRG, StringUtils.EMPTY);
        return prg;
    }

    /**
     * Delete user id from session and context
     */
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

    /**
     * Add user id to context
     */
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
