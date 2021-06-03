package ua.training.project.controller.filter;


import org.apache.commons.lang3.StringUtils;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.ERROR;

/**
 * Filter class to check user role
 *
 * @author Iryna Radchuk
 */
public class AuthorizationFilter implements Filter {
    Map<Role, List<String>> acceptablePages = new HashMap<>();

    /**
     * Set permission paths to users with different roles
     *
     * @param filterConfig Filter configuration object used by a servlet container
     */
    @Override
    public void init(FilterConfig filterConfig) {
        List<String> guestPages = Arrays.asList("/", LOGIN, REGISTRATION);
        acceptablePages.put(Role.GUEST, guestPages);
        List<String> userPages = Arrays.asList(ACTIVITY_TIME_CALENDAR, PROFILE, UPDATE, ACTIVITY_REQUEST,
                LOGOUT, SHOW);
        acceptablePages.put(Role.USER, userPages);
        List<String> adminPages = Arrays.asList(MANAGE_USERS, LOGOUT,
                DELETE_ACTIVITIES, ADD_ACTIVITIES, MANAGE_ACTIVITIES,
                ADD_USERS, DELETE_USERS, MANAGE_REQUESTS, APPROVE_REQUEST,
                DENY_REQUEST, USER_STAT, ACTIVITY_STAT);
        acceptablePages.put(Role.ADMIN, adminPages);
    }

    /**
     * Check user permission to visit path
     *
     * @param request  Http request to server
     * @param response Http response from server
     * @param chain    Chain of a filtered request for a resource
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        ServletUtil servletUtil = new ServletUtil();
        Role role = servletUtil.getSessionRole(req);
        String reqUri = req.getRequestURI().replace("/tracker", StringUtils.EMPTY);
        if (session != null) {
            if (role == null) {
                role = Role.GUEST;
            }
            if (acceptablePages.get(role).contains(reqUri)) {
                chain.doFilter(req, resp);
            } else {
                request.setAttribute(ERROR, ExceptionMessage.PERMISSION_DENIED);
                resp.sendRedirect("/tracker" + acceptablePages.get(role).get(0));
            }
        } else {
            resp.sendRedirect("/tracker");
        }
    }

    @Override
    public void destroy() {
    }
}
